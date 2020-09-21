package com.simple.spring.v3;

import com.simple.spring.context.ApplicationContext;
import com.simple.spring.context.support.ClassPathXmlApplicationContext;
import com.simple.spring.service.v3.PetStoreService;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by cjh on 2020/9/21.
 */
public class ApplicationContextTest {
    @Test
    public void testGetBeanProperty() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("petstore-v3.xml");
        PetStoreService petStore = (PetStoreService)ctx.getBean("petStore");

        Assert.assertNotNull(petStore.getAccountDao());
        Assert.assertNotNull(petStore.getItemDao());
        Assert.assertEquals(1, petStore.getVersion());

    }
}
