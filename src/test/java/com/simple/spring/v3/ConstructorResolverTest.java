package com.simple.spring.v3;

import com.simple.spring.beans.BeanDefinition;
import com.simple.spring.beans.factory.support.ConstructorResolver;
import com.simple.spring.beans.factory.support.DefaultBeanFactory;
import com.simple.spring.beans.factory.xml.XmlBeanDefinitionReader;
import com.simple.spring.core.io.ClassPathResource;
import com.simple.spring.core.io.Resource;
import com.simple.spring.service.v3.PetStoreService;
import org.junit.Assert;
import org.junit.Test;

public class ConstructorResolverTest {

	@Test
	public void testAutowireConstructor() {
		
		DefaultBeanFactory factory = new DefaultBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
		Resource resource = new ClassPathResource("petstore-v3.xml");
		reader.loadBeanDefinitions(resource);

		BeanDefinition beanDefinition = factory.getBeanDefinition("petStore");

		Assert.assertEquals("com.simple.spring.service.v3.PetStoreService", beanDefinition.getBeanClassName());

		ConstructorResolver resolver = new ConstructorResolver(factory);
		
		PetStoreService petStore = (PetStoreService)resolver.autowireConstructor(beanDefinition);
		
		// 验证参数version 正确地通过此构造函数做了初始化
		// PetStoreService(AccountDao accountDao, ItemDao itemDao,int version)
		Assert.assertEquals(1, petStore.getVersion());
		
		Assert.assertNotNull(petStore.getAccountDao());
		Assert.assertNotNull(petStore.getItemDao());
		
		
	}
}
