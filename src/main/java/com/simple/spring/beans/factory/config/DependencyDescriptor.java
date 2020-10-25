package com.simple.spring.beans.factory.config;

import com.simple.spring.utils.Assert;

import java.lang.reflect.Field;

/**
 * Created by cjh on 2020/10/25.
 */
public class DependencyDescriptor {
    private Field field;
    private boolean required;

    public DependencyDescriptor(Field field, boolean required){
        Assert.notNull(field, "field must not be null");
        this.field = field;
        this.required = required;
    }

    public Class<?> getDependencyType() {
        if (this.field != null) {
            return field.getType();
        }
        throw new RuntimeException("only support field dependency");
    }

    public boolean isRequired() {
        return this.required;
    }
}
