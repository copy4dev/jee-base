package com.cn.jee.modules.qrtz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

public abstract class CronQuartzBean extends QuartzJobBean {

	protected Logger logger = LoggerFactory.getLogger(CronQuartzBean.class);

	/**
	 * 任务执行cron表达式
	 * @return
	 */
	protected abstract String getCronExpression();

}
