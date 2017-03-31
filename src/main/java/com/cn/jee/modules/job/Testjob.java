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
		logger.debug("定时调度测试-开始");
		logger.debug("注意quartz数据表表名的大小写");
		try {
			Thread.sleep(1000 * 30);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		logger.debug("注意mySQL数据库表名大小写敏感");
		logger.debug("定时调度测试-结束");
	}

}
