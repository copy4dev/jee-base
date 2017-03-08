package com.cn.jee.modules.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.cn.jee.modules.quartz.service.QuartzJobLogService;
import com.cn.jee.modules.quartz.utils.ICronJob;

@Component
public class Testjob implements ICronJob {

	@Autowired
	private QuartzJobLogService quartzJobLogService;

	@Value("${corn.test}")
	private String cron;

	@Override
	public String getCronExpression() {
		return cron;
	}

	@Override
	public void execute() {
		logger.debug("定时调度测试");
	}

}
