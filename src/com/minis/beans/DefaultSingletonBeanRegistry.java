/**
 * @projectName SongSpring
 * @package com.minis.beans
 * @className com.minis.beans.DefaultSingletonBeanRegistry
 */
package com.minis.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * DefaultSingletonBeanRegistry
 * @description 默认的单例bean
 * @author SongJian
 * @date 2023/3/30 15:41
 * @version
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
    /**
     * 容器中存放所有bean的名称的列表
     */
    protected List<String> beanNames = new ArrayList<>();

    /**
     * 容器中存放所有 bean 实例的map
     */
    protected Map<String, Object> singletons = new ConcurrentHashMap<>(256);

    /**
     * 向容器中注册单例bean
     * @param beanName
     * @param singletonObject
     */
    public void registerSingleton(String beanName, Object singletonObject) {
        // 注册时维护线程安全，使用 synchronized
        synchronized (this.singletons) {
            this.singletons.put(beanName, singletonObject);
            this.beanNames.add(beanName);
        }
    }

    /**
     * 根据 bean 名称获取对应的 bean 对象
     * @param beanName
     * @return
     */
    public Object getSingleton(String beanName) {
        return this.singletons.get(beanName);
    }

    /**
     * 判断容器中是否存在对应的 bean
     * @param beanName
     * @return
     */
    public boolean containsSingleton(String beanName) {
        return this.singletons.containsKey(beanName);
    }

    /**
     * 获取所有的 bean 对象
     * @return
     */
    public String[] getSingletonNames() {
        return (String[]) this.beanNames.toArray();
    }


    protected void removeSingleton(String beanName) {
        // 删除时维护线程安全，使用 synchronized
        synchronized (this.singletons) {
            this.beanNames.remove(beanName);
            this.singletons.remove(beanName);
        }
    }
}

