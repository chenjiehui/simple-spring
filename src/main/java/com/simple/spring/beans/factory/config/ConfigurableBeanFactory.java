package com.simple.spring.beans.factory.config;

import com.simple.spring.beans.factory.BeanFactory;

import java.util.List;

/**
 * Created by cjh on 2020/9/3.
 */
public interface ConfigurableBeanFactory extends AutowireCapableBeanFactory {
    void setBeanClassLoader(ClassLoader classLoader);

    ClassLoader getBeanClassLoader();

    void addBeanPostProcessor(BeanPostProcessor postProcessor);
    List<BeanPostProcessor> getBeanPostProcessors();
}
