package org.demo.quartz;

import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

/**
 * @author hezhiyu on 15/4/7.
 *
 * 说明：
 * 1. Quartz Job的配置, 见 resources/applicationContext.xml
 * 2. 对于Quartz Job 不建议定义在业务节点之中, 因为会有重复执行的问题,
 *    应该定义于某个独立的schedule之下，有且只有一个
 */
@Component
public class QuartzJob {

    public void doSomeQuartzWork() {
        Calendar calendar = Calendar.getInstance();
        Date data = calendar.getTime();
        System.out.println("I'm a quartz job work~~ time: " + data);
    }
}
