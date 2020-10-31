package com.simple.spring.v4;

import com.simple.spring.beans.factory.annotation.AutowiredFieldElement;
import com.simple.spring.beans.factory.annotation.InjectionElement;
import com.simple.spring.beans.factory.annotation.InjectionMetadata;
import com.simple.spring.beans.factory.support.DefaultBeanFactory;
import com.simple.spring.beans.factory.xml.XmlBeanDefinitionReader;
import com.simple.spring.core.io.ClassPathResource;
import com.simple.spring.core.io.Resource;
import com.simple.spring.dao.v4.AccountDao;
import com.simple.spring.dao.v4.ItemDao;
import com.simple.spring.service.v4.PetStoreService;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.LinkedList;

/**
 * Created by cjh on 2020/10/31.
 */
public class InjectionMetadataTest {

    @Test
    public void testInjection() throws Exception {
        DefaultBeanFactory factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        Resource resource = new ClassPathResource("petStore-v4.xml");
        reader.loadBeanDefinitions(resource);

        Class<?> clz = PetStoreService.class;
        LinkedList<InjectionElement> elements = new LinkedList<InjectionElement>();

        {
            Field field = PetStoreService.class.getDeclaredField("accountDao");
            InjectionElement injectionElem = new AutowiredFieldElement(field, true, factory);
            elements.add(injectionElem);
        }
        {
            Field field = PetStoreService.class.getDeclaredField("itemDao");
            InjectionElement injectionElem = new AutowiredFieldElement(field, true, factory);
            elements.add(injectionElem);
        }

        InjectionMetadata metadata = new InjectionMetadata(clz, elements);

        PetStoreService petStore = new PetStoreService();

        metadata.inject(petStore);

        Assert.assertTrue(petStore.getAccountDao() instanceof AccountDao);

        Assert.assertTrue(petStore.getItemDao() instanceof ItemDao);

    }
}
