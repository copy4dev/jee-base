/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cn.jee.modules.qrtz.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.cn.jee.common.persistence.DataEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 触发器记录Entity
 * 
 * @author 1002360
 * @version 2017-03-18
 */
public class QrtzTriggers extends DataEntity<QrtzTriggers> {

	private static final long serialVersionUID = 1L;
	private String schedName; // 调度器名称
	private String triggerName; // 触发器名称
	private String triggerGroup; // 触发器所属组
	private String jobName; // 任务名称
	private String jobGroup; // 任务所属组
	private String description; // 描述
	private Long nextFireTime; // 下次触发时间
	private Long prevFireTime; // 上次触发时间
	private String priority; // 优先级
	private String triggerState; // 触发器状态
	private String triggerType; // 触发器类型
	private Long startTime; // 开始时间
	private Long endTime; // 停止时间
	private String calendarName; // 流程名称
	private String misfireInstr; // 异常数
	private String jobData; // 任务数据块

	/* 虚拟字段 */
	private Date nextFireDate; // 下次触发日期
	private Date prevFireDate; // 上次触发日期
	private Date startDate; // 开始日期
	private Date endDate; // 停止日期

	/* 关联表字段 */
	private String cronExpression; // 时间表达式

	public QrtzTriggers() {
		super();
	}

	public QrtzTriggers(String id) {
		super(id);
	}

	@Length(min = 1, max = 120, message = "调度器名称长度必须介于 1 和 120 之间")
	public String getSchedName() {
		return schedName;
	}

	public void setSchedName(String schedName) {
		this.schedName = schedName;
	}

	@Length(min = 1, max = 200, message = "触发器名称长度必须介于 1 和 200 之间")
	public String getTriggerName() {
		return triggerName;
	}

	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}

	@Length(min = 1, max = 200, message = "触发器所属组长度必须介于 1 和 200 之间")
	public String getTriggerGroup() {
		return triggerGroup;
	}

	public void setTriggerGroup(String triggerGroup) {
		this.triggerGroup = triggerGroup;
	}

	@Length(min = 1, max = 200, message = "任务名称长度必须介于 1 和 200 之间")
	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	@Length(min = 1, max = 200, message = "任务所属组长度必须介于 1 和 200 之间")
	public String getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	@Length(min = 0, max = 250, message = "描述长度必须介于 0 和 250 之间")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getNextFireTime() {
		return nextFireTime;
	}

	public void setNextFireTime(Long nextFireTime) {
		this.nextFireTime = nextFireTime;
	}

	public Long getPrevFireTime() {
		return prevFireTime;
	}

	public void setPrevFireTime(Long prevFireTime) {
		this.prevFireTime = prevFireTime;
	}

	@Length(min = 0, max = 11, message = "优先级长度必须介于 0 和 11 之间")
	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	@Length(min = 1, max = 16, message = "触发器状态长度必须介于 1 和 16 之间")
	public String getTriggerState() {
		return triggerState;
	}

	public void setTriggerState(String triggerState) {
		this.triggerState = triggerState;
	}

	@Length(min = 1, max = 8, message = "触发器类型长度必须介于 1 和 8 之间")
	public String getTriggerType() {
		return triggerType;
	}

	public void setTriggerType(String triggerType) {
		this.triggerType = triggerType;
	}

	@NotNull(message = "开始时间不能为空")
	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	@Length(min = 0, max = 200, message = "流程名称长度必须介于 0 和 200 之间")
	public String getCalendarName() {
		return calendarName;
	}

	public void setCalendarName(String calendarName) {
		this.calendarName = calendarName;
	}

	@Length(min = 0, max = 2, message = "异常数长度必须介于 0 和 2 之间")
	public String getMisfireInstr() {
		return misfireInstr;
	}

	public void setMisfireInstr(String misfireInstr) {
		this.misfireInstr = misfireInstr;
	}

	@NotNull(message = "任务数据块不能为空")
	@JsonIgnore
	public String getJobData() {
		return jobData;
	}

	public void setJobData(String jobData) {
		this.jobData = jobData;
	}

	public Date getNextFireDate() {
		nextFireDate = new Date(this.getNextFireTime());
		return nextFireDate;
	}

	public void setNextFireDate(Date nextFireDate) {
		this.nextFireDate = nextFireDate;
	}

	public Date getPrevFireDate() {
		prevFireDate = new Date(this.getPrevFireTime());
		return prevFireDate;
	}

	public void setPrevFireDate(Date prevFireDate) {
		this.prevFireDate = prevFireDate;
	}

	public Date getStartDate() {
		startDate = new Date(this.getStartTime());
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		endDate = new Date(this.getEndTime());
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

}