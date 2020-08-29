package com.simple.spring.beans;

/**
 * Created by cjh on 2020/8/29.
 */
public class BeansException extends RuntimeException {
    public BeansException(String msg) {
        super(msg);
    }

    public BeansException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
