package com.simple.spring.v4;

import com.simple.spring.beans.factory.config.DependencyDescriptor;
import com.simple.spring.beans.factory.support.DefaultBeanFactory;
import com.simple.spring.beans.factory.xml.XmlBeanDefinitionReader;
import com.simple.spring.core.io.ClassPathResource;
import com.simple.spring.core.io.Resource;
import com.simple.spring.dao.v4.AccountDao;
import com.simple.spring.service.v4.PetStoreService;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;

/**
 * Created by cjh on 2020/10/25.
 */
public class DependencyDescriptorTest {

    @Test
    public void testResolveDependency() throws Exception {
        DefaultBeanFactory factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        Resource resource = new ClassPathResource("petstore-v4.xml");
        reader.loadBeanDefinitions(resource);

        Field field = PetStoreService.class.getDeclaredField("accountDao");
        DependencyDescriptor descriptor = new DependencyDescriptor(field,true);
        Object object = factory.resolveDependency(descriptor);
        Assert.assertTrue(object instanceof AccountDao);
    }
}
