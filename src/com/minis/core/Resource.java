/**
 * @projectName miniSpring
 * @package minis.beans
 * @className minis.core.Resource
 */
package com.minis.core;

import java.util.Iterator;

/**
 * Resource
 * @description 外部的配置信息定义为resource进行抽象
 *              抽象后，就可方便我们拓展
 * @author SongJian
 * @date 2023/3/29 13:39
 * @version
 */
public interface Resource extends Iterator<Object> {
}
