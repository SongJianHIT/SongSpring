package com.minis; 
/**
 * @projectName miniSpring
 * @package PACKAGE_NAME
 * @className PACKAGE_NAME.minis.BeanDefinition
 */

/**
 * minis.BeanDefinition
 * @description Bean 定义类
 * @author SongJian
 * @date 2023/3/29 12:50
 * @version
 */

public class BeanDefinition {
    /**
     * 类的别名
     */
    private String id;

    /**
     * 要注入的类的权限定名
     */
    private String className;

    public BeanDefinition(String id, String className) {
        this.id = id;
        this.className = className;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}

