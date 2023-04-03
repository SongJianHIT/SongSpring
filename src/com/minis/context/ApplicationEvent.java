/**
 * @projectName SongSpring
 * @package com.minis.context
 * @className com.minis.context.ApplicationEvent
 */
package com.minis.context;

import java.util.EventObject;

/**
 * ApplicationEvent
 * @description 事件
 * @author SongJian
 * @date 2023/3/30 16:38
 * @version
 */
public class ApplicationEvent extends EventObject {

    private static final long serialVersionUID = 1L;
    protected String msg = null;

    public ApplicationEvent(Object arg0) {
        super(arg0);
        this.msg = arg0.toString();
    }

}
