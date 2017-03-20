/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cn.jee.modules.qrtz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cn.jee.common.persistence.Page;
import com.cn.jee.common.service.CrudService;
import com.cn.jee.common.utils.StringUtils;
import com.cn.jee.modules.qrtz.dao.QrtzTriggersDao;
import com.cn.jee.modules.qrtz.entity.QrtzTriggers;

/**
 * 触发器记录Service
 * 
 * @author 1002360
 * @version 2017-03-18
 */
@Service
@Transactional(readOnly = true)
public class QrtzTriggersService extends CrudService<QrtzTriggersDao, QrtzTriggers> {

	@Autowired
	private QrtzTriggersDao qrtzTriggersDao;

	/**
	 * 根据组合主键查询
	 * 
	 * @param schedName 调度器名称
	 * @param triggerName 触发器名称
	 * @param triggerGroup 触发器所属组
	 * @return
	 */
	public QrtzTriggers get(String schedName, String triggerName, String triggerGroup) {
		return qrtzTriggersDao.get(schedName, triggerName, triggerGroup);
	}

	public List<QrtzTriggers> findList(QrtzTriggers qrtzTriggers) {
		return super.findList(qrtzTriggers);
	}

	public Page<QrtzTriggers> findPage(Page<QrtzTriggers> page, QrtzTriggers qrtzTriggers) {
		return super.findPage(page, qrtzTriggers);
	}

	@Transactional(readOnly = false)
	public void save(QrtzTriggers qrtzTriggers) {
		if (StringUtils.isNotBlank(qrtzTriggers.getSchedName()) && StringUtils.isNotBlank(qrtzTriggers.getTriggerName()) && StringUtils.isNotBlank(qrtzTriggers.getTriggerGroup())) {
			qrtzTriggers.setId("t");// 设置临时ID以进行更新操作
			super.save(qrtzTriggers);
		}
	}

	@Transactional(readOnly = false)
	public void delete(QrtzTriggers qrtzTriggers) {
		super.delete(qrtzTriggers);
	}

}