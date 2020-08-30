package com.simple.spring.beans.factory.xml;

import com.simple.spring.beans.BeanDefinition;
import com.simple.spring.beans.factory.BeanDefinitionStoreException;
import com.simple.spring.beans.factory.BeanFactory;
import com.simple.spring.beans.factory.support.BeanDefinitionRegistry;
import com.simple.spring.beans.factory.support.GenericBeanDefinition;
import com.simple.spring.utils.ClassUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

/**
 * Created by cjh on 2020/8/30.
 */
public class XmlBeanDefinitionReader {

    public static final String ID_ARRIBUTE = "id";
    public static final String CLASS_ATTRIBUTE = "class";

    BeanDefinitionRegistry registry;

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    public void loadBeanDefinitions(String configFile) {
        InputStream is = null;
        try {
            ClassLoader cl = ClassUtils.getDefaultClassLoader();
            is = cl.getResourceAsStream(configFile);
            SAXReader reader = new SAXReader();
            Document doc = reader.read(is);

            Element root = doc.getRootElement();
            Iterator<Element> iter = root.elementIterator();
            while (iter.hasNext()) {
                Element ele = (Element) iter.next();
                String id = ele.attributeValue(ID_ARRIBUTE);
                String beanClassName = ele.attributeValue(CLASS_ATTRIBUTE);
                BeanDefinition bd = new GenericBeanDefinition(id, beanClassName);
                this.registry.registerBeanDefinition(id, bd);
            }
        } catch (DocumentException e) {
            throw new BeanDefinitionStoreException("IOException parsing XML document failed", e);
        } finally {
            if ( is != null ) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
