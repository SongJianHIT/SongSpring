/**
 * @projectName miniSpring
 * @package minis.beans
 * @className minis.beans.BeanFactory
 */
package com.minis.beans;

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
     * 获取 bean
     * @param name
     * @return
     * @throws BeansException
     */
    Object getBean(String name) throws BeansException;

    /**
     * 是否是单例
     * @param name
     * @return
     */
    boolean isSingleton(String name);

    /**
     * 是否是原型
     * @param name
     * @return
     */
    boolean isPrototype(String name);

    /**
     * 获取 Bean 的类型
     * @param name
     * @return
     */
    Class<?> getType(String name);

    /**
     * 判断 bean 是否存在
     * @param name
     * @return
     */
    Boolean containsBean(String name);
}
