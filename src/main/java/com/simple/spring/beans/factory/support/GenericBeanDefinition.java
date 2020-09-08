package com.simple.spring.beans.factory.support;

import com.simple.spring.beans.BeanDefinition;

/**
 * Created by cjh on 2020/8/27.
 */
public class GenericBeanDefinition implements BeanDefinition {
    private String id;
    private String beanClassName;
    private String scope = SCOPE_DEFAULT;
    private boolean singleton = true;
    private boolean prototype = false;

    public GenericBeanDefinition(String id, String beanClassName) {
        this.id = id;
        this.beanClassName = beanClassName;
    }

    public String getBeanClassName(){
        return this.beanClassName;
    }

    public boolean isSingleton() {
        return this.singleton;
    }

    public boolean isPrototype() {
        return this.prototype;
    }

    public String getScope() {
        return this.scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
        this.singleton = SCOPE_SINGLETON.equals(scope) || SCOPE_DEFAULT.equals(scope);
        this.prototype = SCOPE_PROTOTYPE.equals(scope);
    }
}
