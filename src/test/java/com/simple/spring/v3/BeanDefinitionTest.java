package com.simple.spring.v3;

import com.simple.spring.beans.BeanDefinition;
import com.simple.spring.beans.ConstructorArgument;
import com.simple.spring.beans.ConstructorArgument.ValueHolder;
import com.simple.spring.beans.factory.config.RuntimeBeanReference;
import com.simple.spring.beans.factory.config.TypedStringValue;
import com.simple.spring.beans.factory.support.DefaultBeanFactory;
import com.simple.spring.beans.factory.xml.XmlBeanDefinitionReader;
import com.simple.spring.core.io.ClassPathResource;
import com.simple.spring.core.io.Resource;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by cjh on 2020/9/22.
 */
public class BeanDefinitionTest {
    @Test
    public void testConstructorArgument() {

        DefaultBeanFactory factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        Resource resource = new ClassPathResource("petstore-v3.xml");
        reader.loadBeanDefinitions(resource);

        BeanDefinition bd = factory.getBeanDefinition("petStore");
        Assert.assertEquals("com.simple.spring.service.v3.PetStoreService", bd.getBeanClassName());

        ConstructorArgument args = bd.getConstructorArgument();
        List<ValueHolder> valueHolders = args.getArgumentValues();

        Assert.assertEquals(3, valueHolders.size());

        RuntimeBeanReference ref1 = (RuntimeBeanReference)valueHolders.get(0).getValue();
        Assert.assertEquals("accountDao", ref1.getBeanName());
        RuntimeBeanReference ref2 = (RuntimeBeanReference)valueHolders.get(1).getValue();
        Assert.assertEquals("itemDao", ref2.getBeanName());

        TypedStringValue strValue = (TypedStringValue)valueHolders.get(2).getValue();
        Assert.assertEquals( "1", strValue.getValue());
    }

}
