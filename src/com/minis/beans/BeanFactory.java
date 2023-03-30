/**
 * @projectName miniSpring
 * @package minis.beans
 * @className minis.beans.BeanFactory
 */
package com.minis.beans;

import com.minis.BeanDefinition;

/**
 * BeanFactory
 * @description Bean基础容器，提供：
 *                  - 从容器中获取 bean
 *                  - 向容器中注册一个 bean
 * @author SongJian
 * @date 2023/3/29 13:37
 * @version
 */
public interface BeanFactory {
    /**
     * 根据 bean 名称获取对应的 bean 对象
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object getBean(String beanName) throws com.minis.beans.BeansException;

    /**
     * 注册一个 Bean
     * @param
     */
    void registerBean(String beanName, Object obj);
}
