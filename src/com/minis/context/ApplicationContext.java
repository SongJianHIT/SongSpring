/**
 * @projectName SongSpring
 * @package com.minis.context
 * @className com.minis.context.ApplicationContext
 */
package com.minis.context;

import com.minis.beans.BeansException;
import com.minis.beans.factory.ListableBeanFactory;
import com.minis.beans.factory.config.BeanFactoryPostProcessor;
import com.minis.beans.factory.config.ConfigurableBeanFactory;
import com.minis.beans.factory.config.ConfigurableListableBeanFactory;
import com.minis.core.env.Environment;
import com.minis.core.env.EnvironmentCapable;

/**
 * ApplicationContext
 * @description 所有的上下文都实现自 ApplicationContext，支持上下文环境和事件的发布
 * @author SongJian
 * @date 2023/4/3 15:13
 * @version
 */
public interface ApplicationContext extends EnvironmentCapable,
        ListableBeanFactory, ConfigurableBeanFactory, ApplicationEventPublisher{

    String getApplicationName();

    long getStartupDate();

    ConfigurableListableBeanFactory getBeanFactory() throws IllegalStateException;

    void setEnvironment(Environment environment);

    Environment getEnvironment();

    void addBeanFactoryPostProcessor(BeanFactoryPostProcessor postProcessor);

    void refresh() throws BeansException, IllegalStateException;

    void close();

    boolean isActive();

}
