package com.simple.spring.beans.factory.xml;

import com.simple.spring.beans.BeanDefinition;
import com.simple.spring.beans.ConstructorArgument;
import com.simple.spring.beans.PropertyValue;
import com.simple.spring.beans.factory.BeanDefinitionStoreException;
import com.simple.spring.beans.factory.BeanFactory;
import com.simple.spring.beans.factory.config.RuntimeBeanReference;
import com.simple.spring.beans.factory.config.TypedStringValue;
import com.simple.spring.beans.factory.support.BeanDefinitionRegistry;
import com.simple.spring.beans.factory.support.GenericBeanDefinition;
import com.simple.spring.core.io.Resource;
import com.simple.spring.utils.ClassUtils;
import com.simple.spring.utils.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

    public static final String PROPERTY_ELEMENT = "property";

    public static final String REF_ATTRIBUTE = "ref";

    public static final String VALUE_ATTRIBUTE = "value";

    public static final String NAME_ATTRIBUTE = "name";

    public static final String CONSTRUCTOR_ARG_ELEMENT = "constructor-arg";

    public static final String TYPE_ATTRIBUTE = "type";

    BeanDefinitionRegistry registry;

    protected final Log logger = LogFactory.getLog(getClass());

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
                Element element = (Element) iter.next();
                String id = element.attributeValue(ID_ARRIBUTE);
                String beanClassName = element.attributeValue(CLASS_ATTRIBUTE);
                BeanDefinition beanDefinition = new GenericBeanDefinition(id, beanClassName);
                if (element.attributeValue(SCOPE_ATTRIBUTE) != null) {
                    beanDefinition.setScope(element.attributeValue(SCOPE_ATTRIBUTE));
                }
                parseConstructorArgElements(element, beanDefinition);
                parsePropertyElement(element, beanDefinition);
                this.registry.registerBeanDefinition(id, beanDefinition);
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

    public void parseConstructorArgElements(Element beanEle, BeanDefinition beanDefinition) {
        Iterator iter = beanEle.elementIterator(CONSTRUCTOR_ARG_ELEMENT);
        while(iter.hasNext()){
            Element element = (Element)iter.next();
            parseConstructorArgElement(element, beanDefinition);
        }

    }
    public void parseConstructorArgElement(Element element, BeanDefinition beanDefinition) {

        String typeAttr = element.attributeValue(TYPE_ATTRIBUTE);
        String nameAttr = element.attributeValue(NAME_ATTRIBUTE);
        Object value = parsePropertyValue(element, beanDefinition, null);
        ConstructorArgument.ValueHolder valueHolder = new ConstructorArgument.ValueHolder(value);
        if (StringUtils.hasLength(typeAttr)) {
            valueHolder.setType(typeAttr);
        }
        if (StringUtils.hasLength(nameAttr)) {
            valueHolder.setName(nameAttr);
        }

        beanDefinition.getConstructorArgument().addArgumentValue(valueHolder);
    }

    public void parsePropertyElement(Element beanElem, BeanDefinition beanDefinition) {
        Iterator iter= beanElem.elementIterator(PROPERTY_ELEMENT);
        while(iter.hasNext()){
            Element propertyElement = (Element)iter.next();
            String propertyName = propertyElement.attributeValue(NAME_ATTRIBUTE);
            if (!StringUtils.hasLength(propertyName)) {
                logger.fatal("Tag 'property' must have a 'name' attribute");
                return;
            }


            Object val = parsePropertyValue(propertyElement, beanDefinition, propertyName);
            PropertyValue propertyValue = new PropertyValue(propertyName, val);

            beanDefinition.getPropertyValues().add(propertyValue);
        }

    }

    public Object parsePropertyValue(Element ele, BeanDefinition beanDefinition, String propertyName) {
        String elementName = (propertyName != null) ?
                "<property> element for property '" + propertyName + "'" :
                "<constructor-arg> element";


        boolean hasRefAttribute = (ele.attribute(REF_ATTRIBUTE) != null);
        boolean hasValueAttribute = (ele.attribute(VALUE_ATTRIBUTE) != null);

        if (hasRefAttribute) {
            String refName = ele.attributeValue(REF_ATTRIBUTE);
            if (!StringUtils.hasText(refName)) {
                logger.error(elementName + " contains empty 'ref' attribute");
            }
            RuntimeBeanReference reference = new RuntimeBeanReference(refName);
            return reference;
        }else if (hasValueAttribute) {
            TypedStringValue valueHolder = new TypedStringValue(ele.attributeValue(VALUE_ATTRIBUTE));

            return valueHolder;
        }
        else {
            throw new RuntimeException(elementName + " must specify a ref or value");
        }
    }
}
