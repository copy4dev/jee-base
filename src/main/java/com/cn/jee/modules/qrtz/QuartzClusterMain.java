package com.cn.jee.modules.qrtz;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

/**
 * 
 * <p>
 * Title: QuartzClusterMain<br/>
 * Description: 调度器入口类，自动扫描所有{@link com.cn.chnskin.msg.quartz.CronQuartzBean}对象<br/>
 * Copyright: Copyright (c) 2013 ITDCL All right reserved.<br/>
 * Company: ITDCL<br/>
 * </p>
 * 
 * @author yjf
 * @version 1.0
 * 
 *          修改记录: 下面填写修改的内容以及修改的日期<br/>
 *          1.2013-11-27 下午5:49:37 yjf new <br/>
 *          2.2017-03-21 下午5:49:37 ccp 添加备注 <br/>
 */
@Component
@Lazy(false)
public class QuartzClusterMain {
	private static Logger logger = LoggerFactory.getLogger(QuartzClusterMain.class);

	@Autowired(required = false)
	private SchedulerFactoryBean scheduler;
	@Autowired
	private ApplicationContext context;

	/**
	 * 初始化定时任务
	 */
	@PostConstruct
	public void startQuartz() {
		if (scheduler == null) {
			logger.info("检查无调度器相关配置");
			return;
		}

		logger.info("进入调度器启动入口");

		Map<String, CronQuartzBean> map = context.getBeansOfType(CronQuartzBean.class);

		List<Trigger> triggerList = new ArrayList<Trigger>(map.size());

		for (Entry<String, CronQuartzBean> e : map.entrySet()) {
			logger.info("扫描并添加新任务['{}'] - 触发表达式 = {}", e.getKey(), e.getValue().getCronExpression());
			CronTrigger trigger = createTrigger(e);

			if (trigger != null) {
				triggerList.add(trigger);
			}
		}

		if (!triggerList.isEmpty()) {
			scheduler.setTriggers(triggerList.toArray(new Trigger[triggerList.size()]));
			try {
				scheduler.afterPropertiesSet();
			} catch (Exception e1) {
				logger.error("", e1);
			}
		}

	}

	/**
	 * 创建触发器
	 * 
	 * @param e
	 * @return
	 */
	private CronTrigger createTrigger(Entry<String, CronQuartzBean> e) {
		JobDetail jobDetail = createJobDetail(e);
		if (jobDetail == null) {
			return null;
		}

		CronTriggerFactoryBean factory = new CronTriggerFactoryBean();

		factory.setName(e.getKey() + "Trigger");
		factory.setJobDetail(createJobDetail(e));
		factory.setCronExpression(e.getValue().getCronExpression());

		try {
			factory.afterPropertiesSet();

			return factory.getObject();
		} catch (Exception ex) {
			logger.error("", ex);
			return null;
		}
	}

	/**
	 * 创建调度任务
	 * 
	 * @param e
	 * @return
	 */
	private JobDetail createJobDetail(Entry<String, CronQuartzBean> e) {
		JobDetailFactoryBean factory = new JobDetailFactoryBean();
		factory.setName(e.getKey());
		String clazz = e.getValue().toString();
		clazz = clazz.substring(0, clazz.indexOf("@"));

		try {
			factory.setJobClass(Class.forName(clazz));
			factory.setDurability(true);
			factory.setRequestsRecovery(true);

			factory.afterPropertiesSet();

			return factory.getObject();
		} catch (Exception ex) {
			logger.error("", ex);
			return null;
		}
	}

	/**
	 * 获取一个scheduler调度对象<br/>
	 * 通过scheduler调度对象，可以灵活管理调度器
	 * 
	 * @return
	 * @throws SchedulerException
	 */
	public Scheduler getScheduler() {
		return scheduler.getScheduler();
	}

	/**
	 * 暂停一个任务
	 * 
	 * @param name
	 * @param group
	 * @return
	 */
	public boolean pauseJob(String name, String group) {
		try {
			scheduler.getScheduler().pauseJob(new JobKey(name, group));
		} catch (SchedulerException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 恢复一个任务
	 * 
	 * @param name
	 * @param group
	 * @return
	 */
	public boolean resumeJob(String name, String group) {
		try {
			scheduler.getScheduler().resumeJob(new JobKey(name, group));
		} catch (SchedulerException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
