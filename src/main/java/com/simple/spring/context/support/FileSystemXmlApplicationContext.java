package com.simple.spring.context.support;

import com.simple.spring.core.io.FileSystemResource;
import com.simple.spring.core.io.Resource;

/**
 * Created by cjh on 2020/9/2.
 */
public class FileSystemXmlApplicationContext extends AbstractApplicationContext {


    public FileSystemXmlApplicationContext(String configFile) {
        super(configFile);
    }

    protected Resource getResourceByPath(String path) {
        return new FileSystemResource(path);
    }
}
