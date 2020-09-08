package com.simple.spring.beans;

/**
 * Created by cjh on 2020/7/25.
 */
public interface BeanDefinition {

    String SCOPE_SINGLETON = "singleton";
    String SCOPE_PROTOTYPE = "prototype";
    String SCOPE_DEFAULT = "";

    boolean isSingleton();
    boolean isPrototype();

    String getScope();
    void setScope(String attributeValue);

    String getBeanClassName();
}
