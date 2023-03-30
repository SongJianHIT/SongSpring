/**
 * @projectName miniSpring
 * @package minis.beans
 * @className minis.beans.SimpleBeanFactory
 */
package com.minis.beans;

import com.minis.BeanDefinition;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * SimpleBeanFactory
 * @description BeanFactory 接口的一个简单实现
 * @author SongJian
 * @date 2023/3/29 13:49
 * @version
 */

public class SimpleBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory{
    private Map<String, BeanDefinition> beanDefinitions = new ConcurrentHashMap<>(256);
    public SimpleBeanFactory() {
    }

    /**
     * 获取 bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    public Object getBean(String beanName) throws BeansException {
        // 先尝试直接拿bean实例
        Object singleton = this.getSingleton(beanName);
        // 如果此时还没有这个bean的实例，则获取它的定义来创建实例
        if (singleton == null) {
            // 获取bean的定义
            BeanDefinition beanDefinition = beanDefinitions.get(beanName);
            if (beanDefinition == null) {
                throw new BeansException("No bean.");
            }
            try {
                singleton = Class.forName(beanDefinition.getClassName()).newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            //新注册这个bean实例
            this.registerSingleton(beanName, singleton);
        }
        return singleton;
    }

    /**
     * 注册 bean
     * @param beanDefinition
     */
    public void registerBeanDefinition(BeanDefinition beanDefinition) {
        this.beanDefinitions.put(beanDefinition.getId(), beanDefinition);
    }

    /**
     * 判断 bean 是否存在
     * @param name
     * @return
     */
    public Boolean containsBean(String name) {
        return containsSingleton(name);
    }

    /**
     * 注册 bean
     * @param beanName
     * @param obj
     */
    public void registerBean(String beanName, Object obj) {
        this.registerSingleton(beanName, obj);
    }
}


