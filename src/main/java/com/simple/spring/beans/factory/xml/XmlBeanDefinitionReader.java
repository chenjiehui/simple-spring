package com.simple.spring.beans.factory.xml;

import com.simple.spring.beans.BeanDefinition;
import com.simple.spring.beans.factory.BeanDefinitionStoreException;
import com.simple.spring.beans.factory.BeanFactory;
import com.simple.spring.beans.factory.support.BeanDefinitionRegistry;
import com.simple.spring.beans.factory.support.GenericBeanDefinition;
import com.simple.spring.core.io.Resource;
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
    public static final String SCOPE_ATTRIBUTE = "scope";

    BeanDefinitionRegistry registry;

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    public void loadBeanDefinitions(Resource resource) {
        InputStream is = null;
        try {
            is = resource.getInputStream();
            SAXReader reader = new SAXReader();
            Document doc = reader.read(is);

            Element root = doc.getRootElement();
            Iterator<Element> iter = root.elementIterator();
            while (iter.hasNext()) {
                Element ele = (Element) iter.next();
                String id = ele.attributeValue(ID_ARRIBUTE);
                String beanClassName = ele.attributeValue(CLASS_ATTRIBUTE);
                BeanDefinition bd = new GenericBeanDefinition(id, beanClassName);
                if (ele.attributeValue(SCOPE_ATTRIBUTE) != null) {
                    bd.setScope(ele.attributeValue(SCOPE_ATTRIBUTE));
                }
                this.registry.registerBeanDefinition(id, bd);
            }
        } catch (Exception e) {
            throw new BeanDefinitionStoreException("IOException parsing XML document failed" + resource.getDescription(), e);
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
