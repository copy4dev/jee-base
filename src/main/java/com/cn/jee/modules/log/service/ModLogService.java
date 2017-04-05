/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cn.jee.modules.log.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cn.jee.common.persistence.Page;
import com.cn.jee.common.service.CrudService;
import com.cn.jee.common.utils.StringUtils;
import com.cn.jee.modules.log.dao.ModLogDao;
import com.cn.jee.modules.log.entity.ModLog;
import com.cn.jee.modules.sys.dao.RecordDao;
import com.cn.jee.modules.sys.entity.User;
import com.cn.jee.modules.sys.utils.UserUtils;
import com.cn.jee.modules.utils.Contacts;

/**
 * 模块日志Service
 * 
 * @author admin
 * @version 2017-02-10
 */
@Service
@Transactional(readOnly = true)
public class ModLogService extends CrudService<ModLogDao, ModLog> {

	@Autowired
	private RecordDao recordDao;

	public ModLog get(String id) {
		return super.get(id);
	}

	public List<ModLog> findList(ModLog modLog) {
		// 数据权限过滤器
		usrDateFilter(modLog);

		// 生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
		String dsfSql = aaa(UserUtils.getUser());
		if (StringUtils.isNotBlank(dsfSql)) {
			modLog.getSqlMap().put("dsf", dsfSql);
		}
		return super.findList(modLog);
	}

	public Page<ModLog> findPage(Page<ModLog> page, ModLog modLog) {
		// 数据权限过滤器
		usrDateFilter(modLog);
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
		modLog.setEntityName(trace[idx].getClassName());// 类名
		modLog.setBisMethod(trace[idx].getMethodName());// 方法名

		super.save(modLog);
	}

	/**
	 * 数据权限过滤器
	 * 
	 * @param modLog
	 * @return
	 */
	private final ModLog usrDateFilter(ModLog modLog) {

		/* modLog-logType-数据过滤 */

		String logType = modLog.getLogType();
		// 查找用户拥有的数据权限
		List<String> modLogTypeList = UserUtils.getRecordFieldList(Contacts.MOD_LOG, Contacts.MOD_LOG_TYPE);

		if (StringUtils.isBlank(logType)) {
			// 如果没有指定目标字段(logType),则使用数据权限列表进行查询
			modLog.setLogTypeList(modLogTypeList);
		} else {
			Iterator<String> it = modLogTypeList.iterator();
			String s = null;
			while (true) {
				s = it.next();
				if (logType.equals(s)) {
					// 指定了目标字段，且包含在权限列表内
					break;
				}
				if (it.hasNext()) {
					continue;
				} else {
					// 指定了目标字段，且不 包含在权限列表内
					modLog.setLogType("");
					break;
				}
			}
		}
		return modLog;
	}

	private final String aaa(User user) {
		String sql = null;
		// 填写需要组装SQL的逻辑...
		return sql;
	}

}