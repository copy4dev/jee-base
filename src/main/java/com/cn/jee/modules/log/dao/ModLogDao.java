/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cn.jee.modules.log.dao;

import com.cn.jee.common.persistence.CrudDao;
import com.cn.jee.common.persistence.annotation.MyBatisDao;
import com.cn.jee.modules.log.entity.ModLog;

/**
 * 模块日志DAO接口
 * @author admin
 * @version 2017-01-19
 */
@MyBatisDao
public interface ModLogDao extends CrudDao<ModLog> {
	
}