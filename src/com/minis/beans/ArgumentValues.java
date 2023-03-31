/**
 * @projectName SongSpring
 * @package com.minis.beans
 * @className com.minis.beans.ArgumentValues
 */
package com.minis.beans;

import java.util.*;

/**
 * ArgumentValues
 * @description constructor 注入多个属性
 * @author SongJian
 * @date 2023/3/30 17:01
 * @version
 */

public class ArgumentValues {
    private final List<ArgumentValue> argumentValueList = new ArrayList<ArgumentValue>();

    public ArgumentValues() {
    }

    public void addArgumentValue(ArgumentValue argumentValue) {
        this.argumentValueList.add(argumentValue);
    }

    public ArgumentValue getIndexedArgumentValue(int index) {
        ArgumentValue argumentValue = this.argumentValueList.get(index);
        return argumentValue;
    }

    public int getArgumentCount() {
        return (this.argumentValueList.size());
    }

    public boolean isEmpty() {
        return (this.argumentValueList.isEmpty());
    }
}
