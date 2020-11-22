package com.simple.spring.v4;

import com.simple.spring.context.ApplicationContext;
import com.simple.spring.context.support.ClassPathXmlApplicationContext;
import com.simple.spring.service.v4.PetStoreService;
import org.junit.Assert;
import org.junit.Test;

public class ApplicationContextTest4 {

	@Test
	public void testGetBeanProperty() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("petstore-v4.xml");
		PetStoreService petStore = (PetStoreService)ctx.getBean("petStore");
		
		Assert.assertNotNull(petStore.getAccountDao());
		Assert.assertNotNull(petStore.getItemDao());
		
	}	
}
