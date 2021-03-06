package com.simple.spring.beans.factory.config;

/**
 * Created by cjh on 2020/9/6.
 */
public interface SingletonBeanRegistry {
    void registerSingleton(String beanName, Object sinletonObject);

    Object getSingleton(String beanName);
}
