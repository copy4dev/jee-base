/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cn.jee.modules.log.entity;

import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Lists;
import com.cn.jee.common.persistence.DataEntity;
import com.cn.jee.common.utils.excel.annotation.ExcelField;

/**
 * 模块日志Entity
 * @author admin
 * @version 2017-02-10
 */
public class ModLog extends DataEntity<ModLog> {
	
	private static final long serialVersionUID = 1L;
	private String logType;		// 日志类型
	private String moduleType;		// 模块类型
	private String entityName;		// 实体名
	private String bisMethod;		// 业务方法
	private String notes;		// 摘要
	private String msg;		// 详细信息
	private Date createTime;		// 生成时间
	private Date beginCreateTime;		// 开始 生成时间
	private Date endCreateTime;		// 结束 生成时间
	
	private List<String> logTypeList = Lists.newArrayList();	//日志类型列表，用于数据权限的in查询
	
	public ModLog() {
		super();
	}

	public ModLog(String id){
		super(id);
	}

	@Length(min=0, max=1, message="日志类型长度必须介于 0 和 1 之间")
	@ExcelField(title="日志类型", type=0, align=0, sort=10)
	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}
	
	@Length(min=0, max=2, message="模块类型长度必须介于 0 和 2 之间")
	@ExcelField(title="模块类型", type=0, align=0, sort=20)
	public String getModuleType() {
		return moduleType;
	}

	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}
	
	@Length(min=0, max=50, message="实体名长度必须介于 0 和 50 之间")
	@ExcelField(title="实体名", type=0, align=0, sort=30)
	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	
	@Length(min=0, max=50, message="业务方法长度必须介于 0 和 50 之间")
	@ExcelField(title="业务方法", type=0, align=0, sort=40)
	public String getBisMethod() {
		return bisMethod;
	}

	public void setBisMethod(String bisMethod) {
		this.bisMethod = bisMethod;
	}
	
	@Length(min=0, max=100, message="摘要长度必须介于 0 和 100 之间")
	@ExcelField(title="摘要", type=0, align=0, sort=50)
	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	@ExcelField(title="明细", type=0, align=0, sort=60)
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="创建时间", type=1, align=0, sort=70)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Date getBeginCreateTime() {
		return beginCreateTime;
	}

	public void setBeginCreateTime(Date beginCreateTime) {
		this.beginCreateTime = beginCreateTime;
	}
	
	public Date getEndCreateTime() {
		return endCreateTime;
	}

	public void setEndCreateTime(Date endCreateTime) {
		this.endCreateTime = endCreateTime;
	}

	public List<String> getLogTypeList() {
		return logTypeList;
	}

	public void setLogTypeList(List<String> logTypeList) {
		this.logTypeList = logTypeList;
	}
		
}