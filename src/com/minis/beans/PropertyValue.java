/**
 * @projectName SongSpring
 * @package com.minis.beans
 * @className com.minis.beans.PropertyValue
 */
package com.minis.beans;

/**
 * PropertyValue
 * @description 属性类
 * @author SongJian
 * @date 2023/3/30 16:59
 * @version
 */
public class PropertyValue {
    private final String type;
    private final String name;
    private final Object value;
    private final boolean isRef;

    public PropertyValue(String type, String name, Object value, boolean isRef) {
        this.type = type;
        this.name = name;
        this.value = value;
        this.isRef = isRef;
    }

    public String getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }

    public Object getValue() {
        return this.value;
    }

    public boolean getIsRef() {
        return isRef;
    }
}

