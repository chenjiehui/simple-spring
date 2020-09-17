package com.simple.spring.v2;

import com.simple.spring.context.ApplicationContext;
import com.simple.spring.context.support.ClassPathXmlApplicationContext;
import com.simple.spring.dao.AccountDao;
import com.simple.spring.dao.ItemDao;
import com.simple.spring.service.v2.PetStoreService;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by cjh on 2020/9/17.
 */
public class ApplicationContextTest {
    @Test
    public void testGetBeanProperty() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("petstore-v2.xml");
        PetStoreService petStore = (PetStoreService)ctx.getBean("petStore");

        assertNotNull(petStore.getAccountDao());
        assertNotNull(petStore.getItemDao());

        assertTrue(petStore.getAccountDao() instanceof AccountDao);
        assertTrue(petStore.getItemDao() instanceof ItemDao);

        assertEquals("cjh", petStore.getOwner());
        assertEquals(3, petStore.getVersion());

    }
}
