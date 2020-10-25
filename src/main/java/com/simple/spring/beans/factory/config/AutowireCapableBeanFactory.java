package com.simple.spring.beans.factory.config;

import com.simple.spring.beans.factory.BeanFactory;

/**
 * Created by cjh on 2020/10/25.
 */
public interface AutowireCapableBeanFactory extends BeanFactory {
    public Object resolveDependency(DependencyDescriptor dependencyDescriptor);
}
