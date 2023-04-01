/**
 * @projectName SongSpring
 * @package com.minis.beans.factory.config
 * @className com.minis.beans.factory.config.BeanPostProcessor
 */
package com.minis.beans.factory.config;

import com.minis.beans.BeansException;

/**
 * BeanPostProcessor
 * @description Bean 处理器 Processor
 * @author SongJian
 * @date 2023/4/1 16:38
 * @version
 */
public interface BeanPostProcessor {

    /**
     * Bean 初始化之前执行
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;

    /**
     * Bean 初始化之后执行
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException;
}

