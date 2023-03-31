/**
 * @projectName SongSpring
 * @package com.minis.beans
 * @className com.minis.beans.ArgumentValue
 */
package com.minis.beans;

/**
 * ArgumentValue
 * @description 属性类
 * @author SongJian
 * @date 2023/3/30 16:57
 * @version
 */
public class ArgumentValue {
    private Object value;
    private String type;
    private String name;
    public ArgumentValue(Object value, String type) {
        this.value = value;
        this.type = type;
    }
    public ArgumentValue(Object value, String type, String name) {
        this.value = value;
        this.type = type;
        this.name = name;
    }
    //省略getter和setter

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

