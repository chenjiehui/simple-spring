package com.simple.spring.v4;

import com.simple.spring.core.annotation.AnnotationAttributes;
import com.simple.spring.core.io.ClassPathResource;
import com.simple.spring.core.type.AnnotationMetadata;
import com.simple.spring.core.type.classreading.ClassMetadataReadingVisitor;
import com.simple.spring.core.type.classreading.MetadataReader;
import com.simple.spring.core.type.classreading.SimpleMetadataReader;
import com.simple.spring.stereotype.Component;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.asm.ClassReader;

import java.io.IOException;

/**
 * Created by cjh on 2020/10/17.
 */
public class MetadataReaderTest {

    @Test
    public void testGetClasMetaData() throws IOException {
        ClassPathResource resource = new ClassPathResource("com/simple/spring/service/v4/PetStoreService.class");
        MetadataReader reader = new SimpleMetadataReader(resource);

        //不需要单独使用ClassMetadata
        //ClassMetadata clzMetadata = reader.getClassMetadata();
        AnnotationMetadata amd = reader.getAnnotationMetadata();

        String annotation = Component.class.getName();

        Assert.assertTrue(amd.hasAnnotation(annotation));
        AnnotationAttributes attributes = amd.getAnnotationAttributes(annotation);
        Assert.assertEquals("petStore", attributes.get("value"));

        //注：下面对class metadata的测试并不充分
        Assert.assertFalse(amd.isAbstract());
        Assert.assertFalse(amd.isFinal());
        Assert.assertEquals("com.simple.spring.service.v4.PetStoreService", amd.getClassName());

    }
}
