package com.simple.spring.core.type;

import com.simple.spring.core.annotation.AnnotationAttributes;

import java.util.Set;

/**
 * Created by cjh on 2020/10/10.
 */
public interface AnnotationMetadata extends ClassMetadata{
	
	Set<String> getAnnotationTypes();


	boolean hasAnnotation(String annotationType);
	
	public AnnotationAttributes getAnnotationAttributes(String annotationType);
}
