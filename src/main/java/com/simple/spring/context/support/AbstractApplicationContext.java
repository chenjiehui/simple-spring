package com.simple.spring.context.support;

import com.simple.spring.beans.factory.support.DefaultBeanFactory;
import com.simple.spring.beans.factory.xml.XmlBeanDefinitionReader;
import com.simple.spring.context.ApplicationContext;
import com.simple.spring.core.io.Resource;
import com.simple.spring.utils.ClassUtils;

/**
 * Created by cjh on 2020/9/2.
 */
public abstract class AbstractApplicationContext implements ApplicationContext {

    private DefaultBeanFactory factory;
    private ClassLoader beanClassLoader;

    public AbstractApplicationContext(String configFile) {
        factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        Resource resource = this.getResourceByPath(configFile);
        reader.loadBeanDefinitions(resource);
        factory.setBeanClassLoader(this.getBeanClassLoader());
    }

    public Object getBean(String beanId) {
        return factory.getBean(beanId);
    }

    protected abstract Resource getResourceByPath(String path);

    public void setBeanClassLoader(ClassLoader beanClassLoader) {
        this.beanClassLoader = beanClassLoader;
    }

    public ClassLoader getBeanClassLoader() {
        return (this.beanClassLoader != null ? this.beanClassLoader : ClassUtils.getDefaultClassLoader());
    }

}
