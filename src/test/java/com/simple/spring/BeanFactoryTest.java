package com.simple.spring;

import com.simple.spring.beans.BeanDefinition;
import com.simple.spring.beans.factory.BeanCreationException;
import com.simple.spring.beans.factory.BeanDefinitionStoreException;
import com.simple.spring.beans.factory.support.DefaultBeanFactory;
import com.simple.spring.beans.factory.xml.XmlBeanDefinitionReader;
import com.simple.spring.service.v1.PetStoreService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by cjh on 2020/7/25.
 */
public class BeanFactoryTest  {

    DefaultBeanFactory factory = null;
    XmlBeanDefinitionReader reader = null;

    @Before
    public void setUp() {
        factory = new DefaultBeanFactory();
        reader = new XmlBeanDefinitionReader(factory);
    }

    @Test
    public void testGetBean(){
        reader.loadBeanDefinitions("petstore-v1.xml");
        BeanDefinition bd = factory.getBeanDefinition("petStore");

        assertEquals("com.simple.spring.service.v1.PetStoreService", bd.getBeanClassName());

        PetStoreService petStore = (PetStoreService) factory.getBean("petStore");

        assertNotNull(petStore);
    }

    @Test
    public void testInvalidBean() {
        reader.loadBeanDefinitions("petstore-v1.xml");
        try{
            factory.getBean("invalidBean");
        } catch (BeanCreationException e) {
            return;
        }

        Assert.fail("except BeanCreationException");
    }

    @Test
    public void testInvalidXML() {
         try {
             reader.loadBeanDefinitions("xxx-v1.xml");
         } catch (BeanDefinitionStoreException e) {
             return;
         }

         Assert.fail("except BeanDefinitionStoreException");
    }
}
