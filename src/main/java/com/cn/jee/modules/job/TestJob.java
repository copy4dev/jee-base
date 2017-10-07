package com.cn.jee.modules.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.cn.jee.modules.qrtz.CronQuartzBean;
import com.cn.jee.modules.qrtz.service.QrtzTriggersService;

@Component
@DisallowConcurrentExecution
public class TestJob extends CronQuartzBean {

    private final static String TRIGGER_NAME = "testJobTrigger";

    @Autowired
    private QrtzTriggersService qrtzTriggersService;

    @Value("${corn.test}")
    private String cron;

    @Override
    protected String getCronExpression() {
        cron = "0/30 * * * * ?";
        return cron;
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        long startT = System.currentTimeMillis();
        Thread thread = Thread.currentThread();

        logger.debug("{} - 定时调度测试 - 开始", thread.getName());
        for (int i = 0; i < 50; i++) {

            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                logger.debug("", e);
            }

        }

        long endT = System.currentTimeMillis();
        qrtzTriggersService.remarkRunTime((endT - startT) / 1000, TRIGGER_NAME);
        
        logger.debug("{} 结束 - 耗时:{}",thread.getName(),(endT - startT) / 1000);
    }

}
