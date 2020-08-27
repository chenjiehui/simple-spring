package com.simple.spring;

import com.simple.spring.beans.BeanDefinition;
import com.simple.spring.beans.factory.BeanFactory;
import com.simple.spring.beans.factory.support.DefaultBeanFactory;
import com.simple.spring.service.v1.PetStoreService;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by cjh on 2020/7/25.
 */
public class BeanFactoryTest  {

    @Test
    public void testGetBean(){
        BeanFactory factory = new DefaultBeanFactory("petstore-v1.xml");
        BeanDefinition bd = factory.getBeanDefinition("petStore");

        assertEquals("com.simple.spring.service.v1.PetStoreService", bd.getBeanClassName());

        PetStoreService petStore = (PetStoreService) factory.getBean("petStore");

        assertNotNull(petStore);
    }
}
