/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cn.jee.modules.log.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cn.jee.common.persistence.Page;
import com.cn.jee.common.service.CrudService;
import com.cn.jee.modules.log.dao.ModLogDao;
import com.cn.jee.modules.log.entity.ModLog;

/**
 * 模块日志Service
 * 
 * @author admin
 * @version 2017-01-19
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
	 * @param notes
	 * @param msg
	 */
	@Transactional(readOnly = false)
	public void addLog(String logType, String moduleType, String notes, String msg) {
		ModLog modLog = new ModLog();
		modLog.setLogType(logType);
		modLog.setModuleType(moduleType);
		modLog.setNotes(notes);
		modLog.setMsg(msg);

		StackTraceElement[] trace = Thread.currentThread().getStackTrace();
		int idx = trace.length - 1;
		modLog.setEntityClass(trace[idx].getClassName());//类名
		modLog.setBisMethod(trace[idx].getMethodName());//方法名

		super.save(modLog);
	}

}