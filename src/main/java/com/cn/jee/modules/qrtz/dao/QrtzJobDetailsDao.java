/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cn.jee.modules.qrtz.dao;

import com.cn.jee.common.persistence.CrudDao;
import com.cn.jee.common.persistence.annotation.MyBatisDao;
import com.cn.jee.modules.qrtz.entity.QrtzJobDetails;

/**
 * 触发器Job明细DAO接口
 * 
 * @author 1002360
 * @version 2017-03-18
 */
@MyBatisDao
public interface QrtzJobDetailsDao extends CrudDao<QrtzJobDetails> {

	/**
	 * 根据组合主键查询
	 * 
	 * @param schedName 调度器名称
	 * @param jobName 任务名称
	 * @param jobGroup 任务所属组
	 * @return
	 */
	public QrtzJobDetails get(String schedName, String jobName, String jobGroup);

}