/**
 * @projectName SongSpring
 * @package com.minis.context
 * @className com.minis.context.SimpleApplicationEventPublisher
 */
package com.minis.context;

import java.util.ArrayList;
import java.util.List;

/**
 * SimpleApplicationEventPublisher
 * @description 实现最简单的事件发布者
 * @author SongJian
 * @date 2023/4/3 15:09
 * @version
 */
public class SimpleApplicationEventPublisher implements ApplicationEventPublisher{
    /**
     * 事件监听器
     */
    List<ApplicationListener> listeners = new ArrayList<>();

    /**
     * 发布事件
     * @param event
     */
    @Override
    public void publishEvent(ApplicationEvent event) {
        // 给每一个监听器发送事件
        for (ApplicationListener listener : listeners) {
            listener.onApplicationEvent(event);
        }
    }

    /**
     * 添加事件监听
     * @param listener
     */
    @Override
    public void addApplicationListener(ApplicationListener listener) {
        this.listeners.add(listener);
    }


}

