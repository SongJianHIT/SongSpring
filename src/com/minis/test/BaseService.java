/**
 * @projectName SongSpring
 * @package com.minis.test
 * @className com.minis.test.BaseService
 */
package com.minis.test;

/**
 * BaseService
 * @description
 * @author SongJian
 * @date 2023/3/31 19:25
 * @version
 */
public class BaseService {
    private BaseBaseService bbs;

    public BaseBaseService getBbs() {
        return bbs;
    }
    public void setBbs(BaseBaseService bbs) {
        this.bbs = bbs;
    }
    public BaseService() {
    }
    public void sayHello() {
        System.out.print("Base Service says hello");
        bbs.sayHello();
    }
}

