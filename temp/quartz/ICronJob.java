package com.cn.chnskin.msg.quartz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * <p>Title: ICronJob</p>
 * <p>Description: 调度任务接口</p>
 * <p>Copyright: Copyright (c) 2013 ITDCL  All right reserved.</p>
 * <p>Company: ITDCL</p>
 * @author yjf
 * @version 1.0
 *
 * 修改记录:
 * 下面填写修改的内容以及修改的日期
 * 1.2014-3-26 下午2:24:36  yjf    new
 */
public interface ICronJob {
	
	Logger logger = LoggerFactory.getLogger(ICronJob.class);

	/**
	 * 任务执行cron表达式
	 * @return
	 */
	 public String getCronExpression();
	
	 /**
	  * 任务执行
	  */
	 public void execute(); 
}
