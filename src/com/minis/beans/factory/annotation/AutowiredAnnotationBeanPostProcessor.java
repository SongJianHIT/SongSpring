/**
 * @projectName SongSpring
 * @package com.minis.beans.factory.annotation
 * @className com.minis.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor
 */
package com.minis.beans.factory.annotation;

import com.minis.beans.BeansException;
import com.minis.beans.factory.config.AutowireCapableBeanFactory;
import com.minis.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Field;

/**
 * AutowiredAnnotationBeanPostProcessor
 * @description Autowired 的处理类
 * @author SongJian
 * @date 2023/4/1 16:45
 * @version
 */
public class AutowiredAnnotationBeanPostProcessor implements BeanPostProcessor {

    private AutowireCapableBeanFactory beanFactory;

    public AutowireCapableBeanFactory getBeanFactory() {
        return beanFactory;
    }

    public void setBeanFactory(AutowireCapableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    /**
     * 初始化 Bean 之前的操作
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Object result = bean;
        // 通过反射拿到 bean 的类
        Class<?> clazz = result.getClass();
        // 获取类的所有成员属性
        Field[] fields = clazz.getDeclaredFields();
        if(fields!=null){
            for(Field field : fields){
                // 对每一个属性，判断是否是被 Autowired 注解所修饰
                boolean isAutowired = field.isAnnotationPresent(Autowired.class);
                if(isAutowired){
                    // 如果带有 Autowired 注解
                    String fieldName = field.getName();
                    // 根据属性名称查找同名的 bean
                    Object autowiredObj = this.getBeanFactory().getBean(fieldName);
                    try {
                        // 通过反射，把找到的 bean 注入到属性中
                        field.setAccessible(true);
                        field.set(bean, autowiredObj);
                        System.out.println("autowire " + fieldName + " for bean " + beanName);
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
        return result;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }
}

