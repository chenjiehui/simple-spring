package com.simple.spring.beans.factory.config;

/**
 * Created by cjh on 2020/9/15.
 */
public class RuntimeBeanReference {
    private final String beanName;

    public RuntimeBeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return this.beanName;
    }

}
