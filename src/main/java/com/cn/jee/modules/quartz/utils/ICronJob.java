package com.cn.jee.modules.quartz.utils;

import org.apache.log4j.Logger;

/**
 * <p>Title: ICronJob</p> 
 * <p>Description: 调度任务接口</p> 
 * <p>Copyright: Copyright (c) 2013 ITDCL All right reserved.</p> 
 * <p>Company: ITDCL</p>
 * 
 * @author 1002360
 * @version 2017年2月9日上午11:34:34 1002360 新增
 */
public interface ICronJob {

	Logger logger = Logger.getLogger(ICronJob.class);

	/**
	 * 设置任务执行cron表达式
	 * 
	 * @return
	 */
	public String getCronExpression();

	/**
	 * 任务执行
	 */
	public void execute();

}
