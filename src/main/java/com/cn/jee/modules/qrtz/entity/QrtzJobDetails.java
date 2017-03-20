/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cn.jee.modules.qrtz.entity;

import org.hibernate.validator.constraints.Length;

import com.cn.jee.common.persistence.DataEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 触发器Job明细Entity
 * @author 1002360
 * @version 2017-03-18
 */
public class QrtzJobDetails extends DataEntity<QrtzJobDetails> {
	
	private static final long serialVersionUID = 1L;
	private String schedName;		// 调度器名称
	private String jobName;		// 任务名称
	private String jobGroup;		// 任务所属组
	private String description;		// 描述
	private String jobClassName;		// 任务所在类
	private String isDurable;		// 是否持久化
	private String isNonconcurrent;		// 是否集群
	private String isUpdateData;		// 是否更新
	private String requestsRecovery;		// 是否自动恢复
	private String jobData;		// 任务数据块
	
	public QrtzJobDetails() {
		super();
	}

	public QrtzJobDetails(String id){
		super(id);
	}

	@Length(min=1, max=120, message="调度器名称长度必须介于 1 和 120 之间")
	public String getSchedName() {
		return schedName;
	}

	public void setSchedName(String schedName) {
		this.schedName = schedName;
	}
	
	@Length(min=1, max=200, message="任务名称长度必须介于 1 和 200 之间")
	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	
	@Length(min=1, max=200, message="任务所属组长度必须介于 1 和 200 之间")
	public String getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}
	
	@Length(min=0, max=250, message="描述长度必须介于 0 和 250 之间")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Length(min=1, max=250, message="任务所在类长度必须介于 1 和 250 之间")
	public String getJobClassName() {
		return jobClassName;
	}

	public void setJobClassName(String jobClassName) {
		this.jobClassName = jobClassName;
	}
	
	@Length(min=1, max=1, message="是否持久化长度必须介于 1 和 1 之间")
	public String getIsDurable() {
		return isDurable;
	}

	public void setIsDurable(String isDurable) {
		this.isDurable = isDurable;
	}
	
	@Length(min=1, max=1, message="是否集群长度必须介于 1 和 1 之间")
	public String getIsNonconcurrent() {
		return isNonconcurrent;
	}

	public void setIsNonconcurrent(String isNonconcurrent) {
		this.isNonconcurrent = isNonconcurrent;
	}
	
	@Length(min=1, max=1, message="是否更新长度必须介于 1 和 1 之间")
	public String getIsUpdateData() {
		return isUpdateData;
	}

	public void setIsUpdateData(String isUpdateData) {
		this.isUpdateData = isUpdateData;
	}
	
	@Length(min=1, max=1, message="是否自动恢复长度必须介于 1 和 1 之间")
	public String getRequestsRecovery() {
		return requestsRecovery;
	}

	public void setRequestsRecovery(String requestsRecovery) {
		this.requestsRecovery = requestsRecovery;
	}
	
	@JsonIgnore
	public String getJobData() {
		return jobData;
	}

	public void setJobData(String jobData) {
		this.jobData = jobData;
	}
	
}