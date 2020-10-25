package com.simple.spring.beans.factory.config;

import com.simple.spring.beans.factory.BeanFactory;

/**
 * Created by cjh on 2020/9/3.
 */
public interface ConfigurableBeanFactory extends AutowireCapableBeanFactory {
    void setBeanClassLoader(ClassLoader classLoader);

    ClassLoader getBeanClassLoader();
}
