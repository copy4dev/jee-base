/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cn.jee.modules.quartz.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cn.jee.common.persistence.Page;
import com.cn.jee.common.service.CrudService;
import com.cn.jee.modules.quartz.entity.QuartzJobLog;
import com.cn.jee.modules.quartz.dao.QuartzJobLogDao;

/**
 * 定时任务调度日志Service
 * @author 1002360
 * @version 2017-02-18
 */
@Service
@Transactional(readOnly = true)
public class QuartzJobLogService extends CrudService<QuartzJobLogDao, QuartzJobLog> {

	public QuartzJobLog get(String id) {
		return super.get(id);
	}
	
	public List<QuartzJobLog> findList(QuartzJobLog quartzJobLog) {
		return super.findList(quartzJobLog);
	}
	
	public Page<QuartzJobLog> findPage(Page<QuartzJobLog> page, QuartzJobLog quartzJobLog) {
		return super.findPage(page, quartzJobLog);
	}
	
	@Transactional(readOnly = false)
	public void save(QuartzJobLog quartzJobLog) {
		super.save(quartzJobLog);
	}
	
	@Transactional(readOnly = false)
	public void delete(QuartzJobLog quartzJobLog) {
		super.delete(quartzJobLog);
	}
	
}