/**
 * @projectName miniSpring
 * @package minis.beans
 * @className minis.beans.XmlBeanDefinitionReader
 */
package com.minis.beans;

import com.minis.BeanDefinition;
import com.minis.core.Resource;

import org.dom4j.Element;

/**
 * XmlBeanDefinitionReader
 * @description 通过解析好的 resouce 转化成 beandefinition
 * @author SongJian
 * @date 2023/3/29 13:45
 * @version
 */
public class XmlBeanDefinitionReader {
    com.minis.beans.BeanFactory beanFactory;
    public XmlBeanDefinitionReader(com.minis.beans.BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
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
            // 使用 beanFactory 提供的抽象方法注册
            this.beanFactory.registerBeanDefinition(beanDefinition);
        }
    }
}
