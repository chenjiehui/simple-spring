package com.simple.spring.v1;

import com.simple.spring.context.ApplicationContext;
import com.simple.spring.context.support.ClassPathXmlApplicationContext;
import com.simple.spring.context.support.FileSystemXmlApplicationContext;
import com.simple.spring.service.v1.PetStoreService;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by cjh on 2020/8/31.
 */
public class ApplicationContextTest {

    @Test
    public void testGetBean() {
        ApplicationContext context = new ClassPathXmlApplicationContext("petstore-v1.xml");
        PetStoreService petStore = (PetStoreService) context.getBean("petStore");
        Assert.assertNotNull(petStore);
    }

    @Test
    public void testGetBeanFromFileSystemContext() {
        ApplicationContext context = new FileSystemXmlApplicationContext("/Users/jiehuichen/code/personal/simple-spring/src/test/resources/petstore-v1.xml");
        PetStoreService petStore = (PetStoreService) context.getBean("petStore");
        Assert.assertNotNull(petStore);
    }
}
