package com.simple.spring.beans.factory.support;

import com.simple.spring.beans.BeanDefinition;

/**
 * Created by cjh on 2020/8/27.
 */
public class GenericBeanDefinition implements BeanDefinition {
    private String id;
    private String beanClassName;

    public GenericBeanDefinition(String id, String beanClassName) {
        this.id = id;
        this.beanClassName = beanClassName;
    }

    public String getBeanClassName(){
        return this.beanClassName;
    }
}
