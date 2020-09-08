package com.simple.spring.context.support;

import com.simple.spring.core.io.ClassPathResource;
import com.simple.spring.core.io.Resource;

/**
 * Created by cjh on 2020/8/31.
 */
public class ClassPathXmlApplicationContext extends AbstractApplicationContext {

    public ClassPathXmlApplicationContext(String configFile) {
        super(configFile);
    }

    protected Resource getResourceByPath(String path) {
        return new ClassPathResource(path, this.getBeanClassLoader());
    }
}
