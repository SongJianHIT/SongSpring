/**
 * @projectName SongSpring
 * @package com.minis.beans.factory.config
 * @className com.minis.beans.factory.config.ConfigurableBeanFactory
 */
package com.minis.beans.factory.config;

import com.minis.beans.factory.BeanFactory;

/**
 * ConfigurableBeanFactory
 * @description 维护 Bean 之间的依赖关系以及支持 Bean 处理器也看作一个独立的特性
 * @author SongJian
 * @date 2023/4/3 14:31
 * @version
 */
public interface ConfigurableBeanFactory extends BeanFactory,SingletonBeanRegistry {

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    /**
     * 添加 Bean 处理器
     * @param beanPostProcessor
     */
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    /**
     * 获取 Bean 处理器数量
     * @return
     */
    int getBeanPostProcessorCount();

    /**
     * 维护 Bean 之间的依赖
     * @param beanName
     * @param dependentBeanName
     */
    void registerDependentBean(String beanName, String dependentBeanName);

    /**
     * 根据 Bean 名称获取所有依赖的 Bean
     * @param beanName
     * @return
     */
    String[] getDependentBeans(String beanName);

    String[] getDependenciesForBean(String beanName);

}
