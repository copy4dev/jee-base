package com.cn.jee.common.quartz;

import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.stereotype.Component;

/**
 * <p> Title: QuartzMain</p>
 * <p>Description: 调度器入口类，自动扫描所有{@link com.cn.jee.msg.quartz.ICronJob}对象</p>
 * <p>Copyright: Copyright (c) 2013 ITDCL All right reserved.</p>
 * <p>Company: ITDCL</p>
 * 
 * @author 1002360
 * @version 2017年2月9日上午11:37:23 1002360 新增
 */
@Component
@Lazy(false)
public class QuartzMain {
	
	private static Logger logger = LoggerFactory.getLogger(QuartzMain.class);

	private static Scheduler sched;

	@Resource
	private ApplicationContext context;

	@PostConstruct
	public void startQuartz() {
		logger.info("进入调度器启动入口");
		try {
			sched = StdSchedulerFactory.getDefaultScheduler();

			Map<String, ICronJob> map = context.getBeansOfType(ICronJob.class);
			for (Entry<String, ICronJob> e : map.entrySet()) {
				logger.info("扫描并添加新任务['{}'],触发表达式={}", e.getKey(), e.getValue().getCronExpression());
				JobDetail jobDetail = createJobDetail(e.getValue());
				if (jobDetail != null) {
					logger.debug(jobDetail.toString());
					sched.scheduleJob(jobDetail, createTrigger(e));
				}
			}

			sched.start();

		} catch (SchedulerException e) {
			logger.error("", e);
		}
	}

	private JobDetail createJobDetail(ICronJob job) {
		MethodInvokingJobDetailFactoryBean factory = new MethodInvokingJobDetailFactoryBean();
		factory.setName(job.toString());
		factory.setTargetObject(job);
		factory.setTargetMethod("execute");
		try {
			factory.afterPropertiesSet();

			return factory.getObject();
		} catch (ClassNotFoundException e) {
			logger.error("", e);
			return null;
		} catch (NoSuchMethodException e) {
			logger.error("", e);
			return null;
		}

	}

	private CronTrigger createTrigger(Entry<String, ICronJob> e) {
		CronScheduleBuilder cron = CronScheduleBuilder.cronSchedule(e.getValue().getCronExpression());
		TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger().withIdentity(e.getKey());

		return triggerBuilder.withSchedule(cron).build();
	}

	@PreDestroy
	public void shutdown() {
		if (sched != null) {
			try {
				sched.shutdown(true);
			} catch (SchedulerException e) {
				logger.error("", e);
			}
		}
	}
}
