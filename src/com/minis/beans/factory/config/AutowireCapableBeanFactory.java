/**
 * @projectName SongSpring
 * @package com.minis.beans.factory.config
 * @className com.minis.beans.factory.config.AutowireCapableBeanFactory
 */
package com.minis.beans.factory.config;

import com.minis.beans.BeansException;
import com.minis.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import com.minis.beans.factory.support.AbstractBeanFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * AutowireCapableBeanFactory
 * @description
 * @author SongJian
 * @date 2023/4/1 17:19
 * @version
 */

public class AutowireCapableBeanFactory extends AbstractBeanFactory {
    /**
     * 列表 beanPostProcessors 记录所有的 Bean 处理器
     * 可以按照需求注册若干个不同用途的处理器，然后调用处理器。
     */
    private final List<AutowiredAnnotationBeanPostProcessor> beanPostProcessors = new ArrayList<AutowiredAnnotationBeanPostProcessor>();

    /**
     * 添加 Bean 处理器
     * @param beanPostProcessor
     */
    public void addBeanPostProcessor(AutowiredAnnotationBeanPostProcessor beanPostProcessor) {
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }

    /**
     * 获取 Bean 处理器数量
     * @return
     */
    public int getBeanPostProcessorCount() {
        return this.beanPostProcessors.size();
    }

    /**
     * 获取 Bean 处理器列表
     * @return
     */
    public List<AutowiredAnnotationBeanPostProcessor> getBeanPostProcessors() {
        return this.beanPostProcessors;
    }

    /**
     * Bean 初始化之前调用处理器
     * @param existingBean
     * @param beanName
     * @return
     * @throws BeansException
     */
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName)
            throws BeansException {

        Object result = existingBean;
        for (AutowiredAnnotationBeanPostProcessor beanProcessor : getBeanPostProcessors()) {
            // 对每个 Bean 处理器，调用 postProcessBeforeInitialization
            beanProcessor.setBeanFactory(this);
            result = beanProcessor.postProcessBeforeInitialization(result, beanName);
            if (result == null) {
                return result;
            }
        }
        return result;
    }

    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName)
            throws BeansException {

        Object result = existingBean;
        for (BeanPostProcessor beanProcessor : getBeanPostProcessors()) {
            result = beanProcessor.postProcessAfterInitialization(result, beanName);
            if (result == null) {
                return result;
            }
        }
        return result;
    }
}
