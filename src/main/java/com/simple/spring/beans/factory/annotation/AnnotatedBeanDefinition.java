package com.simple.spring.beans.factory.annotation;

import com.simple.spring.beans.BeanDefinition;
import com.simple.spring.core.type.AnnotationMetadata;

/**
 * Created by cjh on 2020/10/11.
 */
public interface AnnotatedBeanDefinition extends BeanDefinition {
	AnnotationMetadata getMetadata();
}
