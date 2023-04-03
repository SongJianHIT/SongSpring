/**
 * @projectName SongSpring
 * @package com.minis.beans.factory
 * @className com.minis.beans.factory.ListableBeanFactory
 */
package com.minis.beans.factory;

import com.minis.beans.BeansException;

import java.util.Map;

/**
 * ListableBeanFactory
 * @description 将 Factory 内部管理的 Bean 作为一个集合来对待
 * @author SongJian
 * @date 2023/4/3 14:28
 * @version
 */
public interface ListableBeanFactory extends BeanFactory {

    /**
     * 判断是否包含了某个 BeanDefinition
     * @param beanName
     * @return
     */
    boolean containsBeanDefinition(String beanName);

    /**
     * 获取 BeanFactory 内包含的 BeanDefinition 的数量
     * @return
     */
    int getBeanDefinitionCount();

    /**
     * 获取所有 Bean 的名称
     * @return
     */
    String[] getBeanDefinitionNames();

    /**
     * 按照某个类型获取 Bean 名称列表
     * @param type
     * @return
     */
    String[] getBeanNamesForType(Class<?> type);

    /**
     * 按照某个类型获取 Bean 的映射
     * @param type
     * @return
     * @param <T>
     * @throws BeansException
     */
    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;

}
