/**
 * @projectName SongSpring
 * @package com.minis.beans
 * @className com.minis.beans.ArgumentValues
 */
package com.minis.beans.factory.config;

import com.minis.beans.factory.config.ConstructorArgumentValue;

import java.util.*;

/**
 * ArgumentValues
 * @description constructor 注入多个属性
 * @author SongJian
 * @date 2023/3/30 17:01
 * @version
 */

public class ConstructorArgumentValues {
    private final List<ConstructorArgumentValue> argumentValueList = new ArrayList<ConstructorArgumentValue>();

    public ConstructorArgumentValues() {
    }

    public void addArgumentValue(ConstructorArgumentValue argumentValue) {
        this.argumentValueList.add(argumentValue);
    }

    public ConstructorArgumentValue getIndexedArgumentValue(int index) {
        ConstructorArgumentValue argumentValue = this.argumentValueList.get(index);
        return argumentValue;
    }

    public int getArgumentCount() {
        return (this.argumentValueList.size());
    }

    public boolean isEmpty() {
        return (this.argumentValueList.isEmpty());
    }
}
