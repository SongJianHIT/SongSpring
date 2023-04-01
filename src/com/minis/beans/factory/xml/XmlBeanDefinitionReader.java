/**
 * @projectName miniSpring
 * @package minis.beans
 * @className minis.beans.XmlBeanDefinitionReader
 */
package com.minis.beans.factory.xml;

import com.minis.beans.*;
import com.minis.beans.factory.config.BeanDefinition;
import com.minis.beans.factory.config.ConstructorArgumentValue;
import com.minis.beans.factory.config.ConstructorArgumentValues;
import com.minis.beans.factory.support.AbstractBeanFactory;
import com.minis.core.Resource;

import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * XmlBeanDefinitionReader
 * @description 通过解析好的 resouce 转化成 beandefinition
 * @author SongJian
 * @date 2023/3/29 13:45
 * @version
 */
public class XmlBeanDefinitionReader {
    AbstractBeanFactory beanFactory;
    public XmlBeanDefinitionReader(AbstractBeanFactory simpleBeanFactory) {
        this.beanFactory = simpleBeanFactory;
    }

    /**
     * 根据 Resource 加载 beandefinition
     * @param resource
     */
    public void loadBeanDefinitions(Resource resource) {
        while (resource.hasNext()) {
            Element element = (Element) resource.next();
            String beanID = element.attributeValue("id");
            String beanClassName = element.attributeValue("class");
            // String -> Object 的过程
            BeanDefinition beanDefinition = new BeanDefinition(beanID, beanClassName);

            // 处理构造器参数
            List<Element> constructorElements = element.elements("constructor-arg");
            ConstructorArgumentValues AVS = new ConstructorArgumentValues();
            for (Element e : constructorElements) {
                String pType = e.attributeValue("type");
                String pName = e.attributeValue("name");
                String pValue = e.attributeValue("value");
                // 注入的配置读入内存
                AVS.addArgumentValue(new ConstructorArgumentValue(pValue, pType, pName));
            }
            beanDefinition.setConstructorArgumentValues(AVS);

            // 处理属性
            List<Element> propertyElements = element.elements("property");
            PropertyValues PVS = new PropertyValues();
            List<String> refs = new ArrayList<>();
            for (Element e : propertyElements) {
                String pType = e.attributeValue("type");
                String pName = e.attributeValue("name");
                String pValue = e.attributeValue("value");
                String pRef = e.attributeValue("ref");
                String pV = "";
                boolean isRef = false;
                if (pValue != null && !pValue.equals("")) {
                    isRef = false;
                    pV = pValue;
                } else if (pRef != null && !pRef.equals("")) {
                    isRef = true;
                    pV = pRef;
                    refs.add(pRef);
                }
                // 注入的配置读入内存
                PVS.addPropertyValue(new PropertyValue(pType, pName, pV, isRef));
            }
            beanDefinition.setPropertyValues(PVS);
            String[] refArray = refs.toArray(new String[0]);
            beanDefinition.setDependsOn(refArray);
            //end of handle properties
            // 使用 beanFactory 提供的抽象方法注册
            this.beanFactory.registerBeanDefinition(beanID, beanDefinition);
        }
    }
}
