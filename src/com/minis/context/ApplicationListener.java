/**
 * @projectName SongSpring
 * @package com.minis.context
 * @className com.minis.context.ApplicationListener
 */
package com.minis.context;

import java.util.EventListener;

/**
 * ApplicationListener
 * @description 事件监听
 * @author SongJian
 * @date 2023/4/3 15:06
 * @version
 */
public class ApplicationListener implements EventListener {
    void onApplicationEvent(ApplicationEvent event) {
        System.out.println(event.toString());
    }
}

