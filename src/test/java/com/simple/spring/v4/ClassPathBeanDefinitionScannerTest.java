package com.simple.spring.v4;

import com.simple.spring.beans.BeanDefinition;
import com.simple.spring.beans.factory.support.DefaultBeanFactory;
import com.simple.spring.context.annotation.ClassPathBeanDefinitionScanner;
import com.simple.spring.context.annotation.ScannedGenericBeanDefinition;
import com.simple.spring.core.annotation.AnnotationAttributes;
import com.simple.spring.core.type.AnnotationMetadata;
import com.simple.spring.stereotype.Component;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by cjh on 2020/10/11.
 */
public class ClassPathBeanDefinitionScannerTest {

    @Test
    public void  testParseScanedBean() {
        DefaultBeanFactory factory = new DefaultBeanFactory();

        String basePackages = "com.simple.spring.service.v4, com.simple.spring.dao.v4";

        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(factory);
        scanner.doScan(basePackages);


        String annotation = Component.class.getName();

        {
            BeanDefinition bd = factory.getBeanDefinition("petStore");
            Assert.assertTrue(bd instanceof ScannedGenericBeanDefinition);
            ScannedGenericBeanDefinition sbd = (ScannedGenericBeanDefinition)bd;
            AnnotationMetadata amd = sbd.getMetadata();


            Assert.assertTrue(amd.hasAnnotation(annotation));
            AnnotationAttributes attributes = amd.getAnnotationAttributes(annotation);
            Assert.assertEquals("petStore", attributes.get("value"));
        }
        {
            BeanDefinition bd = factory.getBeanDefinition("accountDao");
            Assert.assertTrue(bd instanceof ScannedGenericBeanDefinition);
            ScannedGenericBeanDefinition sbd = (ScannedGenericBeanDefinition)bd;
            AnnotationMetadata amd = sbd.getMetadata();
            Assert.assertTrue(amd.hasAnnotation(annotation));
        }
        {
            BeanDefinition bd = factory.getBeanDefinition("itemDao");
            Assert.assertTrue(bd instanceof ScannedGenericBeanDefinition);
            ScannedGenericBeanDefinition sbd = (ScannedGenericBeanDefinition)bd;
            AnnotationMetadata amd = sbd.getMetadata();
            Assert.assertTrue(amd.hasAnnotation(annotation));
        }
    }
}
