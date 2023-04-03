/**
 * @projectName SongSpring
 * @package com.minis.context
 * @className com.minis.context.ApplicationEventPublisher
 */
package com.minis.context;




/**
 * ApplicationEventPublisher
 * @description 发布事件
 * @author SongJian
 * @date 2023/3/30 16:37
 * @version
 */

public interface ApplicationEventPublisher {
    /**
     * 发布事件
     * @param event
     */
    void publishEvent(ApplicationEvent event);

    /**
     * 添加 Application 事件监听
     * @param listener
     */
    void addApplicationListener(ApplicationListener listener);
}

