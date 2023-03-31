package com.minis.beans;
/**
 * @projectName miniSpring
 * @package PACKAGE_NAME
 * @className PACKAGE_NAME.minis.BeanDefinition
 */

import com.minis.beans.ArgumentValues;
import com.minis.beans.PropertyValues;

/**
 * minis.BeanDefinition
 * @description Bean 定义类
 * @author SongJian
 * @date 2023/3/29 12:50
 * @version
 */

public class BeanDefinition {

    String SCOPE_SINGLETON = "singleton";
    String SCOPE_PROTOTYPE = "prototype";

    /**
     * 是否懒加载
     */
    private boolean lazyInit = false;

    /**
     * 记录 bean 之间的依赖关系
     */
    private String[] dependsOn;

    /**
     * 记录构造参数
     */
    private ArgumentValues constructorArgumentValues;

    /**
     * 记录属性列表
     */
    private PropertyValues propertyValues;

    /**
     * 初始化方法
     */
    private String initMethodName;


    private volatile Object beanClass;

    /**
     * 表示 bean 是单例还是原型模式
     */
    private String scope = SCOPE_SINGLETON;

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

    public boolean hasBeanClass() {
        return (this.beanClass instanceof Class);
    }

    public void setBeanClass(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    public Class<?> getBeanClass(){

        return (Class<?>) this.beanClass;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getScope() {
        return this.scope;
    }

    public boolean isSingleton() {
        return SCOPE_SINGLETON.equals(scope);
    }

    public boolean isPrototype() {
        return SCOPE_PROTOTYPE.equals(scope);
    }

    public void setLazyInit(boolean lazyInit) {
        this.lazyInit = lazyInit;
    }

    public boolean isLazyInit() {
        return this.lazyInit;
    }

    public void setDependsOn(String... dependsOn) {
        this.dependsOn = dependsOn;
    }

    public String[] getDependsOn() {
        return this.dependsOn;
    }

    public void setConstructorArgumentValues(ArgumentValues constructorArgumentValues) {
        this.constructorArgumentValues =
                (constructorArgumentValues != null ? constructorArgumentValues : new ArgumentValues());
    }

    public ArgumentValues getConstructorArgumentValues() {
        return this.constructorArgumentValues;
    }

    public boolean hasConstructorArgumentValues() {
        return !this.constructorArgumentValues.isEmpty();
    }
    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = (propertyValues != null ? propertyValues : new PropertyValues());
    }

    public PropertyValues getPropertyValues() {
        return this.propertyValues;
    }
    public void setInitMethodName(String initMethodName) {
        this.initMethodName = initMethodName;
    }

    public String getInitMethodName() {
        return this.initMethodName;
    }
}

