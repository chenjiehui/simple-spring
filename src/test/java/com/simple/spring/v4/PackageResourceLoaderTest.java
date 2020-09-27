package com.simple.spring.v4;

import com.simple.spring.core.io.Resource;
import com.simple.spring.core.io.support.PackageResourceLoader;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by cjh on 2020/9/27.
 */
public class PackageResourceLoaderTest {

    @Test
    public void testGetResources() throws IOException {
        PackageResourceLoader loader = new PackageResourceLoader();
        Resource[] resources = loader.getResources("com.simple.spring.dao.v4");
        Assert.assertEquals(2, resources.length);
    }
}
