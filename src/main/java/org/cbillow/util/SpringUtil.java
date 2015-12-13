package org.cbillow.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: fubingdao
 * Date: 14-8-26
 * Time: 下午2:45
 * To change this template use File | Settings | File Templates.
 */
public class SpringUtil {

    private static ApplicationContext ctx = new ClassPathXmlApplicationContext(
            "applicationContext.xml");

    public static Object getBean(String beanName) {
        return ctx.getBean(beanName);
    }
}
