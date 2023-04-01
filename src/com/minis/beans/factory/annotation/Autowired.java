/**
 * @projectName SongSpring
 * @package com.minis.beans.factory.annotation
 * @className com.minis.beans.factory.annotation.Autowired
 */
package com.minis.beans.factory.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Autowired
 * @description Autowired 注解：
 *              - 修饰的是字段
 *              - 运行时生效
 * @author SongJian
 * @date 2023/4/1 16:41
 * @version
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Autowired {
}

