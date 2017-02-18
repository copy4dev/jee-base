/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cn.jee.modules.quartz.dao;

import com.cn.jee.common.persistence.CrudDao;
import com.cn.jee.common.persistence.annotation.MyBatisDao;
import com.cn.jee.modules.quartz.entity.QuartzJobLog;

/**
 * 定时任务调度日志DAO接口
 * @author 1002360
 * @version 2017-02-18
 */
@MyBatisDao
public interface QuartzJobLogDao extends CrudDao<QuartzJobLog> {
	
}