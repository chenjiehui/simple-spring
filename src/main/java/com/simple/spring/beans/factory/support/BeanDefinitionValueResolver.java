package com.simple.spring.beans.factory.support;

import com.simple.spring.beans.factory.config.RuntimeBeanReference;
import com.simple.spring.beans.factory.config.TypedStringValue;

/**
 * Created by cjh on 2020/9/16.
 */
public class BeanDefinitionValueResolver {

    private final DefaultBeanFactory beanFactory;

    public BeanDefinitionValueResolver(DefaultBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public Object resolveValueIfNecessary(Object value) {
        if (value instanceof RuntimeBeanReference) {
            RuntimeBeanReference reference = (RuntimeBeanReference) value;
            String referenceName = reference.getBeanName();
            return this.beanFactory.getBean(referenceName);
        } else if (value instanceof TypedStringValue){
            return ((TypedStringValue) value).getValue();
        } else {
            throw new RuntimeException("the value " + value + "has not implemented");
        }
    }
}
