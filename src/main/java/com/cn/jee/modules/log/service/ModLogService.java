/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cn.jee.modules.log.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cn.jee.common.persistence.Page;
import com.cn.jee.common.service.CrudService;
import com.cn.jee.modules.log.entity.ModLog;
import com.cn.jee.modules.log.dao.ModLogDao;

/**
 * 模块日志Service
 * @author admin
 * @version 2017-02-10
 */
@Service
@Transactional(readOnly = true)
public class ModLogService extends CrudService<ModLogDao, ModLog> {

	public ModLog get(String id) {
		return super.get(id);
	}
	
	public List<ModLog> findList(ModLog modLog) {
		return super.findList(modLog);
	}
	
	public Page<ModLog> findPage(Page<ModLog> page, ModLog modLog) {
		return super.findPage(page, modLog);
	}
	
	@Transactional(readOnly = false)
	public void save(ModLog modLog) {
		super.save(modLog);
	}
	
	@Transactional(readOnly = false)
	public void delete(ModLog modLog) {
		super.delete(modLog);
	}
	
	/**
	 * 日志写入
	 * 
	 * @param logType 0:异常; 1:警告; 2:信息
	 * @param moduleType 功能模块
	 * @param notes 普通摘要
	 * @param expection 异常信息
	 */
	@Transactional(readOnly = false)
	public void addLog(String logType, String moduleType, String notes, String expection) {
		ModLog modLog = new ModLog();
		modLog.setLogType(logType);
		modLog.setModuleType(moduleType);
		modLog.setNotes(notes);
		modLog.setMsg(expection);

		StackTraceElement[] trace = Thread.currentThread().getStackTrace();
		int idx = trace.length - 1;
		modLog.setEntityName(trace[idx].getClassName());//类名
		modLog.setBisMethod(trace[idx].getMethodName());//方法名

		super.save(modLog);
	}
	
}