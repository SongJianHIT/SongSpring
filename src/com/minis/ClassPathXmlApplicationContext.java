package com.minis; /**
 * @projectName miniSpring
 * @package PACKAGE_NAME
 * @className PACKAGE_NAME.minis.ClassPathXmlApplicationContext
 */


import com.minis.beans.BeanFactory;
import com.minis.beans.BeansException;
import com.minis.beans.SimpleBeanFactory;
import com.minis.beans.XmlBeanDefinitionReader;
import com.minis.core.ClassPathXmlResource;
import com.minis.core.Resource;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * minis.ClassPathXmlApplicationContext
 * @description 功能集成，集成了 Resouce 和 Reader
 *              1、按照一定的规则解析XML文件
 *              2、提取Bean的配置信息 BeanDefinition
 *              3、读取 BeanDefinition ，实例化 bean，注册到 BeanFactory 容器中
 *
 * @author SongJian
 * @date 2023/3/29 12:54
 * @version
 */

public class ClassPathXmlApplicationContext implements BeanFactory {
    /**
     * 维护一个 beanFactory
     */
    BeanFactory beanFactory;

    /**
     * context 负责整合容器的启动过程，读外部配置，解析 Bean 定义，创建 BeanFactory
     * @param fileName
     */
    public ClassPathXmlApplicationContext(String fileName) {
        Resource resource = new ClassPathXmlResource(fileName);
        BeanFactory beanFactory = new SimpleBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions(resource);
        this.beanFactory = beanFactory;
    }

    /**
     * context 再对外提供一个 getBean，底下就是调用的 BeanFactory 对应的方法
     * @param beanName
     * @return
     * @throws BeansException
     */
    public Object getBean(String beanName) throws BeansException {
        return this.beanFactory.getBean(beanName);
    }

    /**
     * context 再对外提供一个 registerBeanDefinition，底下就是调用的 BeanFactory 对应的方法
     * @param beanDefinition
     */
    public void registerBeanDefinition(BeanDefinition beanDefinition) {
        this.beanFactory.registerBeanDefinition(beanDefinition);
    }


    public Boolean containsBean(String name) {
        return this.beanFactory.containsBean(name);
    }

    public void registerBean(String beanName, Object obj) {
        this.beanFactory.registerBean(beanName, obj);
    }
}

