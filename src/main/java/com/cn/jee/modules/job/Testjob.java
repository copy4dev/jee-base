package com.cn.jee.modules.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.cn.jee.modules.qrtz.CronQuartzBean;

@Component
public class Testjob extends CronQuartzBean {

	@Value("${corn.test}")
	private String cron;

	@Override
	protected String getCronExpression() {
		return cron;
	}

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		logger.debug("定时调度测试");
	}

}
