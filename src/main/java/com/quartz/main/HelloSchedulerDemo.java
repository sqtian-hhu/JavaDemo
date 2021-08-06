package com.quartz.main;

import com.quartz.job.HelloJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class HelloSchedulerDemo {

    public static void main(String[] args) throws SchedulerException {
        //1. Scheduler　调度器
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        //2. JobDetail　任务实例

        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)
                .withIdentity("jobTest")
                .build();

        //3. Trigger　触发器
        SimpleTrigger trigger1 = TriggerBuilder.newTrigger()
                .withIdentity("trigger1")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().repeatSecondlyForever(5))
                .build();
        scheduler.scheduleJob(jobDetail,trigger1);
        scheduler.start();
    }
}
