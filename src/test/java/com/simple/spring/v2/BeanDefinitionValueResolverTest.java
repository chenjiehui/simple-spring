package com.simple.spring.v2;

import com.simple.spring.beans.factory.config.RuntimeBeanReference;
import com.simple.spring.beans.factory.config.TypedStringValue;
import com.simple.spring.beans.factory.support.BeanDefinitionValueResolver;
import com.simple.spring.beans.factory.support.DefaultBeanFactory;
import com.simple.spring.beans.factory.xml.XmlBeanDefinitionReader;
import com.simple.spring.core.io.ClassPathResource;
import com.simple.spring.dao.AccountDao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by cjh on 2020/9/16.
 */
public class BeanDefinitionValueResolverTest {

    DefaultBeanFactory factory = null;
    XmlBeanDefinitionReader reader = null;
    BeanDefinitionValueResolver resolver = null;

    @Before
    public void setUp(){
        factory = new DefaultBeanFactory();
        reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(new ClassPathResource("petstore-v2.xml"));
        resolver = new BeanDefinitionValueResolver(factory);

    }

    @Test
    public void testResolveRuntimeBeanReference() {

        RuntimeBeanReference reference = new RuntimeBeanReference("accountDao");
        Object value = resolver.resolveValueIfNecessary(reference);
        Assert.assertNotNull(value);
        Assert.assertTrue(value instanceof AccountDao);
    }

    @Test
    public void testResolveTypedStringValue() {

        TypedStringValue stringValue = new TypedStringValue("test");
        Object value = resolver.resolveValueIfNecessary(stringValue);
        Assert.assertNotNull(value);
        Assert.assertEquals("test", value);
    }
}
