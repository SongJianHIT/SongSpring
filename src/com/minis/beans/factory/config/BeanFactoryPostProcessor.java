/**
 * @projectName SongSpring
 * @package com.minis.beans.factory.config
 * @className com.minis.beans.factory.config.BeanFactoryPostProcessor
 */
package com.minis.beans.factory.config;

import com.minis.beans.BeansException;
import com.minis.beans.factory.BeanFactory;

/**
 * BeanFactoryPostProcessor
 * @description
 * @author SongJian
 * @date 2023/4/1 17:32
 * @version
 */
public interface BeanFactoryPostProcessor {
    void postProcessBeanFactory(BeanFactory beanFactory) throws BeansException;
}
