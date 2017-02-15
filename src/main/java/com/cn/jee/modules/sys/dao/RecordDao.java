/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cn.jee.modules.sys.dao;

import java.util.List;

import com.cn.jee.common.persistence.TreeDao;
import com.cn.jee.common.persistence.annotation.MyBatisDao;
import com.cn.jee.modules.sys.entity.Record;

/**
 * 数据权限DAO接口
 * 
 * @author admin
 * @version 2017-02-10
 */
@MyBatisDao
public interface RecordDao extends TreeDao<Record> {
	
	/**
	 * 获取当前用户授权的数据权限
	 * @param userId
	 * @return
	 */
	public List<Record> findByUserId(String userId);
	
}