package com.simple.spring.context.support;

import com.simple.spring.beans.factory.support.DefaultBeanFactory;
import com.simple.spring.beans.factory.xml.XmlBeanDefinitionReader;
import com.simple.spring.context.ApplicationContext;

/**
 * Created by cjh on 2020/8/31.
 */
public class ClassPathXmlApplicationContext implements ApplicationContext {

    private DefaultBeanFactory factory;

    public ClassPathXmlApplicationContext(String configFile) {
        factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(configFile);
    }

    public Object getBean(String beanId) {
        return factory.getBean(beanId);
    }
}
