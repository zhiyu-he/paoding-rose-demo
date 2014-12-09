package org.demo.main;

import org.demo.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author hezhiyu on 14/11/7.
 */
public class PaodingStart {


    public static void main(String[] args) {
        String[] applicationContextArr = new String[] {"applicationContext.xml", "applicationContext-jade.xml"};

        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(applicationContextArr);

        UserService userService = (UserService)ctx.getBean("userService");

        String username = userService.getUserNameById(1001278L);
        System.out.println(username);

    }
}
