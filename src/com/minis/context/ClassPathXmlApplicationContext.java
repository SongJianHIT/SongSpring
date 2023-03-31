package com.minis.context; /**
 * @projectName miniSpring
 * @package PACKAGE_NAME
 * @className PACKAGE_NAME.minis.ClassPathXmlApplicationContext
 */


import com.minis.beans.*;
import com.minis.context.ApplicationEvent;
import com.minis.context.ApplicationEventPublisher;
import com.minis.core.ClassPathXmlResource;
import com.minis.core.Resource;

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

public class ClassPathXmlApplicationContext implements BeanFactory, ApplicationEventPublisher {
    /**
     * 维护一个 beanFactory
     */
    SimpleBeanFactory beanFactory;

    /**
     * context 负责整合容器的启动过程，读外部配置，解析 Bean 定义，创建 BeanFactory
     * @param fileName
     */
    public ClassPathXmlApplicationContext(String fileName) {
        this(fileName, false);
    }

    /**
     * 构造方法
     * @param fileName
     * @param isRefresh 是否要通过 refresh 激活整个 IOC 容器
     */
    public ClassPathXmlApplicationContext(String fileName, boolean isRefresh){
        Resource res = new ClassPathXmlResource(fileName);
        SimpleBeanFactory bf = new SimpleBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(bf);
        reader.loadBeanDefinitions(res);
        this.beanFactory = bf;

        if (isRefresh) {
            this.beanFactory.refresh();
        }
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


    @Override
    public boolean isSingleton(String name) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isPrototype(String name) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Class<?> getType(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    public Boolean containsBean(String name) {
        return this.beanFactory.containsBean(name);
    }

    public void registerBean(String beanName, Object obj) {
        this.beanFactory.registerBean(beanName, obj);
    }

    @Override
    public void publishEvent(ApplicationEvent event) {
        // TODO 发布事件
    }
}

