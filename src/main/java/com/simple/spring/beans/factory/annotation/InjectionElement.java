package com.simple.spring.beans.factory.annotation;

import com.simple.spring.beans.factory.config.AutowireCapableBeanFactory;

import java.lang.reflect.Member;

/**
 * Created by cjh on 2020/10/31.
 */
public abstract class InjectionElement {
    protected Member member;
    protected AutowireCapableBeanFactory factory;

    InjectionElement(Member member, AutowireCapableBeanFactory factory){
        this.member = member;
        this.factory = factory;
    }

    public abstract void inject(Object target);
}
