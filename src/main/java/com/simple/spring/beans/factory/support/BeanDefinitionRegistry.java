package com.simple.spring.beans.factory.support;

import com.simple.spring.beans.BeanDefinition;

/**
 * Created by cjh on 2020/8/30.
 */
public interface BeanDefinitionRegistry {
    BeanDefinition getBeanDefinition(String beanId);

    void registerBeanDefinition(String beanId, BeanDefinition bd);
}
