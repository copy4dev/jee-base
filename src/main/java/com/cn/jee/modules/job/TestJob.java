package com.cn.jee.modules.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.cn.jee.modules.qrtz.CronQuartzBean;
import com.cn.jee.modules.qrtz.service.QrtzTriggersService;

@Component
public class TestJob extends CronQuartzBean {

    private final static String TRIGGER_NAME = "testJobTrigger";

    @Autowired
    private QrtzTriggersService qrtzTriggersService;

    @Value("${corn.test}")
    private String cron;

    @Override
    protected String getCronExpression() {
        return cron;
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        long startT = System.currentTimeMillis();
        logger.debug("定时调度测试");
        long endT = System.currentTimeMillis();
        qrtzTriggersService.remarkRunTime((endT - startT) / 1000, TRIGGER_NAME);
    }

}
