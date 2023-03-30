/**
 * @projectName miniSpring
 * @package minis.beans
 * @className minis.beans.BeansException
 */
package com.minis.beans;

/**
 * BeansException
 * @description 异常处理类
 * @author SongJian
 * @date 2023/3/29 13:35
 * @version
 */
public class BeansException extends Exception {
    public BeansException(String msg) {
        // 调用父类处理，并抛出
        super(msg);
    }
}

