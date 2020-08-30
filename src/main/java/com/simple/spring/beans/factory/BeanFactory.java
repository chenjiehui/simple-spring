package com.simple.spring.beans.factory;

import com.simple.spring.beans.BeanDefinition;

/**
 * Created by cjh on 2020/7/25.
 */
public interface BeanFactory {
    Object getBean(String beanId);
}
