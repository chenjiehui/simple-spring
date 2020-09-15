package com.simple.spring.v2;

import com.simple.spring.beans.BeanDefinition;
import com.simple.spring.beans.PropertyValue;
import com.simple.spring.beans.factory.config.RuntimeBeanReference;
import com.simple.spring.beans.factory.support.DefaultBeanFactory;
import com.simple.spring.beans.factory.xml.XmlBeanDefinitionReader;
import com.simple.spring.core.io.ClassPathResource;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by cjh on 2020/9/15.
 */
public class BeanDefinitionTest {

    @Test
    public void testGetBeanDefinition() {

        DefaultBeanFactory factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);

        reader.loadBeanDefinitions(new ClassPathResource("petstore-v2.xml"));

        BeanDefinition beanDefinition = factory.getBeanDefinition("petStore");

        List<PropertyValue> propertyValueList = beanDefinition.getPropertyValues();

        Assert.assertTrue(propertyValueList.size() == 4);

        {
            PropertyValue propertyValue = this.getPropertyValue("accountDao", propertyValueList);

            Assert.assertNotNull(propertyValue);

            Assert.assertTrue(propertyValue.getValue() instanceof RuntimeBeanReference);

        }

        {
            PropertyValue propertyValue = this.getPropertyValue("itemDao", propertyValueList);

            Assert.assertNotNull(propertyValue);

            Assert.assertTrue(propertyValue.getValue() instanceof RuntimeBeanReference);
        }

    }


    private PropertyValue getPropertyValue(String name, List<PropertyValue> pvs){
        for(PropertyValue pv : pvs){
            if(pv.getName().equals(name)){
                return pv;
            }
        }
        return null;
    }

}
