/**
 * @projectName SongSpring
 * @package com.minis.beans
 * @className com.minis.beans.SingletonBeanRegistry
 */
package com.minis.beans;

/**
 * SingletonBeanRegistry
 * @description 单例Bean容器
 * @author SongJian
 * @date 2023/3/30 15:38
 * @version
 */
public interface SingletonBeanRegistry {
    /**
     * 向容器中注册单例bean
     * @param beanName
     * @param singletonObject
     */
    void registerSingleton(String beanName, Object singletonObject);

    /**
     * 根据 bean 名称获取对应的 bean 对象
     * @param beanName
     * @return
     */
    Object getSingleton(String beanName);

    /**
     * 判断容器中是否存在对应的 bean
     * @param beanName
     * @return
     */
    boolean containsSingleton(String beanName);

    /**
     * 获取所有的 bean 对象
     * @return
     */
    String[] getSingletonNames();
}
