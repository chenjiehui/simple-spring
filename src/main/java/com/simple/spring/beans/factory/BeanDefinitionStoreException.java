package com.simple.spring.beans.factory;

import com.simple.spring.beans.BeansException;

/**
 * Created by cjh on 2020/8/29.
 */
public class BeanDefinitionStoreException extends BeansException {
    public BeanDefinitionStoreException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
