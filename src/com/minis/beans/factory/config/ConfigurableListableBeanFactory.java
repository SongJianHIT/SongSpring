/**
 * @projectName SongSpring
 * @package com.minis.beans.factory.config
 * @className com.minis.beans.factory.config.ConfigurableListableBeanFactory
 */
package com.minis.beans.factory.config;

import com.minis.beans.factory.ListableBeanFactory;

/**
 * ConfigurableListableBeanFactory
 * @description 集成了 AutowireCapableBeanFactory 、 ListableBeanFactory 和 ConfigurableBeanFactory
 * @author SongJian
 * @date 2023/4/3 14:34
 * @version
 */
public interface ConfigurableListableBeanFactory
        extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

}
