package com.simple.spring.beans;

import java.util.List;

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

    List<PropertyValue> getPropertyValues();

    ConstructorArgument getConstructorArgument();

    String getId();

    boolean hasConstructorArgumentValues();

    public Class<?> resolveBeanClass(ClassLoader classLoader) throws ClassNotFoundException;
    public Class<?> getBeanClass() throws IllegalStateException ;
    public boolean hasBeanClass();
}
