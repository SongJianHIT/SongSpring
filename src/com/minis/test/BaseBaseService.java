/**
 * @projectName SongSpring
 * @package com.minis.test
 * @className com.minis.test.BaseBaseService
 */
package com.minis.test;

/**
 * BaseBaseService
 * @description
 * @author SongJian
 * @date 2023/3/31 19:26
 * @version
 */
public class BaseBaseService {
    private AServiceImpl as;

    public AServiceImpl getAs() {
        return as;
    }
    public void setAs(AServiceImpl as) {
        this.as = as;
    }
    public BaseBaseService() {
    }
    public void sayHello() {
        System.out.println("Base Base Service says hello");

    }
}
