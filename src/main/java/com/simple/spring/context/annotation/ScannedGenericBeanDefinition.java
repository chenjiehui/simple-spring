package com.simple.spring.context.annotation;

import com.simple.spring.beans.factory.annotation.AnnotatedBeanDefinition;
import com.simple.spring.beans.factory.support.GenericBeanDefinition;
import com.simple.spring.core.type.AnnotationMetadata;

/**
 * Created by cjh on 2020/10/11.
 */
public class ScannedGenericBeanDefinition extends GenericBeanDefinition implements AnnotatedBeanDefinition {

	private final AnnotationMetadata metadata;


	public ScannedGenericBeanDefinition(AnnotationMetadata metadata) {
		super();
		
		this.metadata = metadata;
		
		setBeanClassName(this.metadata.getClassName());
	}


	public final AnnotationMetadata getMetadata() {
		return this.metadata;
	}

}