/**
 * @projectName miniSpring
 * @package test
 * @className test.Test1
 */


import com.minis.context.ClassPathXmlApplicationContext;
import com.minis.test.AService;


/**
 * Test1
 * @description
 * @author SongJian
 * @date 2023/3/29 13:12
 * @version
 */


public class Test1 {
    public static void main(String[] args) throws com.minis.beans.BeansException {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
        AService aService = (AService) ctx.getBean("aservice");
        aService.sayHello();
    }
}
