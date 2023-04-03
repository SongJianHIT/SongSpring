/**
 * @projectName SongSpring
 * @package com.minis.context
 * @className com.minis.context.ContextRefreshEvent
 */
package com.minis.context;

/**
 * ContextRefreshEvent
 * @description 定义一个 ContextRefresh 事件
 * @author SongJian
 * @date 2023/4/3 15:07
 * @version
 */
public class ContextRefreshEvent extends ApplicationEvent {

    private static final long serialVersionUID = 1L;

    public ContextRefreshEvent(Object arg0) {
        super(arg0);
    }

    public String toString() {
        return this.msg;
    }
}
