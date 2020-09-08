package com.simple.spring;

import com.simple.spring.core.io.ClassPathResource;
import com.simple.spring.core.io.Resource;
import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;

public class ResourceTest {

	@Test
	public void testClassPathResource() throws Exception {

		Resource resource = new ClassPathResource("petstore-v1.xml");

		InputStream is = null;

		try {
			is = resource. getInputStream();
			// TODO 此处测试不充分
			Assert.assertNotNull(is);
		} finally {
			if (is != null) {
				is.close();
			}
		}

	}

	@Test
	public void testFileSystemResource() throws Exception {

		/*Resource r = new FileSystemResource("C:\\Users\\liuxin\\git-litespring\\src\\test\\resources\\petstore-v1.xml");

		InputStream is = null;

		try {
			is = r.getInputStream();
			// TODO 此处并不充分
			Assert.assertNotNull(is);
		} finally {
			if (is != null) {
				is.close();
			}
		}
*/
	}

}
