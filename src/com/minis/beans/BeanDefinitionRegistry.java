/**
 * @projectName SongSpring
 * @package com.minis.beans
 * @className com.minis.beans.BeanDefinitionRegistry
 */
package com.minis.beans;

/**
 * BeanDefinitionRegistry
 * @description 集中存放BeanDefinition的仓库
 * @author SongJian
 * @date 2023/3/30 17:12
 * @version
 */
public interface BeanDefinitionRegistry {
    /**
     * 添加 BeanDefinition
     * @param name
     * @param bd
     */
    void registerBeanDefinition(String name, BeanDefinition bd);

    /**
     * 移除 BeanDefinition
     * @param name
     */
    void removeBeanDefinition(String name);

    /**
     * 获取 BeanDefinition
     * @param name
     * @return
     */
    BeanDefinition getBeanDefinition(String name);

    /**
     * 判断是否包含 BeanDefinition
     * @param name
     * @return
     */
    boolean containsBeanDefinition(String name);
}

