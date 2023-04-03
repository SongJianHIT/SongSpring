/**
 * @projectName SongSpring
 * @package com.minis.core.env
 * @className com.minis.core.env.Environment
 */
package com.minis.core.env;

/**
 * Environment
 * @description 获取属性
 * @author SongJian
 * @date 2023/4/3 14:40
 * @version
 */
public interface Environment extends PropertyResolver {

    String[] getActiveProfiles();

    String[] getDefaultProfiles();

    boolean acceptsProfiles(String... profiles);

}
