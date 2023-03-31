/**
 * @projectName miniSpring
 * @package minis.beans
 * @className minis.beans.SimpleBeanFactory
 */
package com.minis.beans;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * SimpleBeanFactory
 * @description BeanFactory 接口的一个简单实现
 * @author SongJian
 * @date 2023/3/29 13:49
 * @version
 */

public class SimpleBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory, BeanDefinitionRegistry {

    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(256);
    private List<String> beanDefinitionNames = new ArrayList<>();

    /**
     * 毛坯实例
     */
    private final Map<String, Object> earlySingletonObjects = new HashMap<String, Object>(16);

    public SimpleBeanFactory() {
    }

    /**
     * 创建所有 bean 实例
     */
    public void refresh() {
        for (String beanName : beanDefinitionNames) {
            try {
                getBean(beanName);
            } catch (BeansException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取 bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    public Object getBean(String beanName) throws BeansException {
        // 尝试直接获取
        Object singleton = this.getSingleton(beanName);

        if (singleton == null) {

            // 尝试从毛坯实例中获取
            singleton = this.earlySingletonObjects.get(beanName);
            if (singleton == null) {
                System.out.println("获取 bean 为空 -------------- " + beanName);
                BeanDefinition bd = beanDefinitionMap.get(beanName);
                singleton = createBean(bd);
                this.registerBean(beanName, singleton);

                //beanpostprocessor
                //step 1 : postProcessBeforeInitialization
                //step 2 : afterPropertiesSet
                //step 3 : init-method
                //step 4 : postProcessAfterInitialization。
            }

        }
        if (singleton == null) {
            throw new BeansException("bean is null.");
        }
        return singleton;
    }


    @Override
    public boolean isSingleton(String name) {
        return this.beanDefinitionMap.get(name).isSingleton();
    }

    @Override
    public boolean isPrototype(String name) {
        return this.beanDefinitionMap.get(name).isPrototype();
    }

    @Override
    public Class<?> getType(String name) {
        return this.beanDefinitionMap.get(name).getClass();
    }

    /**
     * 注册 bean
     * @param beanDefinition
     */
    public void registerBeanDefinition(BeanDefinition beanDefinition) {
        this.beanDefinitionMap.put(beanDefinition.getId(), beanDefinition);
    }

    /**
     * 判断 bean 是否存在
     * @param name
     * @return
     */
    public Boolean containsBean(String name) {
        return containsSingleton(name);
    }

    /**
     * 注册 bean
     * @param beanName
     * @param obj
     */
    public void registerBean(String beanName, Object obj) {
        this.registerSingleton(beanName, obj);
    }

    @Override
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
        this.beanDefinitionMap.put(name, beanDefinition);
        this.beanDefinitionNames.add(name);
        if (!beanDefinition.isLazyInit()) {
            try {
                getBean(name);
            } catch (BeansException e){
            }
        }
    }

    @Override
    public void removeBeanDefinition(String name) {
        this.beanDefinitionMap.remove(name);
        this.beanDefinitionNames.remove(name);
        this.removeSingleton(name);
    }

    @Override
    public BeanDefinition getBeanDefinition(String name) {
        return this.beanDefinitionMap.get(name);
    }

    @Override
    public boolean containsBeanDefinition(String name) {
        return this.beanDefinitionMap.containsKey(name);
    }

    /**
     * 解耦，专门处理构造器
     * @param bd
     * @return
     */
    private Object doCreateBean(BeanDefinition bd) {
        Class<?> clz = null;
        Object obj = null;
        Constructor<?> con = null;
        try {
            clz = Class.forName(bd.getClassName());

            // 处理构造器注入
            ArgumentValues argumentValues = bd.getConstructorArgumentValues();
            if (!argumentValues.isEmpty()) {
                Class<?>[] paramTypes = new Class<?>[argumentValues.getArgumentCount()];
                Object[] paramValues =   new Object[argumentValues.getArgumentCount()];
                for (int i=0; i<argumentValues.getArgumentCount(); i++) {
                    ArgumentValue argumentValue = argumentValues.getIndexedArgumentValue(i);
                    if ("String".equals(argumentValue.getType()) || "java.lang.String".equals(argumentValue.getType())) {
                        paramTypes[i] = String.class;
                        paramValues[i] = argumentValue.getValue();
                    }
                    else if ("Integer".equals(argumentValue.getType()) || "java.lang.Integer".equals(argumentValue.getType())) {
                        paramTypes[i] = Integer.class;
                        paramValues[i] = Integer.valueOf((String) argumentValue.getValue());
                    }
                    else if ("int".equals(argumentValue.getType())) {
                        paramTypes[i] = int.class;
                        paramValues[i] = Integer.valueOf((String) argumentValue.getValue()).intValue();
                    }
                    else {
                        paramTypes[i] = String.class;
                        paramValues[i] = argumentValue.getValue();
                    }
                }
                try {
                    con = clz.getConstructor(paramTypes);
                    obj = con.newInstance(paramValues);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (SecurityException e) {
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
            else {
                obj = clz.newInstance();
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(bd.getId() + " bean 已经创建. " + bd.getClassName() + " : " + obj.toString());
        return obj;

    }

    /**
     * 创建一个 bean 实例，并完成注入
     * @param bd
     * @return
     */
    private Object createBean(BeanDefinition bd) {
        Class<?> clz = null;
        Object obj = doCreateBean(bd);

        // 创建完毛坯实例后，丢入 earlySingletonObjects ，以解决循环依赖问题
        this.earlySingletonObjects.put(bd.getId(), obj);
        try {
            clz = Class.forName(bd.getClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // 处理属性，setter 注入
        handleProperties(bd, clz, obj);
        return obj;
    }

    /**
     * 解耦，专门处理属性
     * @param bd
     * @param clz
     * @param obj
     */
    private void handleProperties(BeanDefinition bd, Class<?> clz, Object obj) {
        // handle properties
        System.out.println("处理 Bean 的属性注入 : " + bd.getId());
        // 获取 BeanDefinition 中的所有定义的属性
        PropertyValues propertyValues = bd.getPropertyValues();

        if (!propertyValues.isEmpty()) {
            // 遍历处理每个属性
            for (int i=0; i<propertyValues.size(); i++) {
                // 获取每个属性的基本信息，包括名称、类型、值 和 是否是引用
                PropertyValue propertyValue = propertyValues.getPropertyValueList().get(i);
                String pName = propertyValue.getName();
                String pType = propertyValue.getType();
                Object pValue = propertyValue.getValue();
                boolean isRef = propertyValue.getIsRef();

                // 参数的 类型数组 和 值数组
                Class<?>[] paramTypes = new Class<?>[1];
                Object[] paramValues =   new Object[1];

                // 如果当前该属性不是引用
                if (!isRef) {
                    if ("String".equals(pType) || "java.lang.String".equals(pType)) {
                        paramTypes[0] = String.class;
                    }
                    else if ("Integer".equals(pType) || "java.lang.Integer".equals(pType)) {
                        paramTypes[0] = Integer.class;
                    }
                    else if ("int".equals(pType)) {
                        paramTypes[0] = int.class;
                    }
                    else {
                        paramTypes[0] = String.class;
                    }

                    paramValues[0] = pValue;
                }
                // 如果当前该属性是一个引用，依赖其他 bean
                else {
                    try {
                        // 获取 被依赖（引用） bean 的类型
                        paramTypes[0] = Class.forName(pType);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    try {
                        // getBean 获取 bean
                        paramValues[0] = getBean((String)pValue);
                    } catch (BeansException e) {
                        e.printStackTrace();
                    }
                }

                // 获取 set 方法名称
                String methodName = "set" + pName.substring(0,1).toUpperCase() + pName.substring(1);
                Method method = null;
                try {
                    // 反射
                    method = clz.getMethod(methodName, paramTypes);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (SecurityException e) {
                    e.printStackTrace();
                }
                try {
                    // 反射
                    method.invoke(obj, paramValues);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


