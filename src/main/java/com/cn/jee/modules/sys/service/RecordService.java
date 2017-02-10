/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cn.jee.modules.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cn.jee.common.service.TreeService;
import com.cn.jee.common.utils.StringUtils;
import com.cn.jee.modules.sys.entity.Record;
import com.cn.jee.modules.sys.dao.RecordDao;

/**
 * 数据权限Service
 * @author admin
 * @version 2017-02-10
 */
@Service
@Transactional(readOnly = true)
public class RecordService extends TreeService<RecordDao, Record> {

	public Record get(String id) {
		return super.get(id);
	}
	
	public List<Record> findList(Record record) {
		if (StringUtils.isNotBlank(record.getParentIds())){
			record.setParentIds(","+record.getParentIds()+",");
		}
		return super.findList(record);
	}
	
	@Transactional(readOnly = false)
	public void save(Record record) {
		super.save(record);
	}
	
	@Transactional(readOnly = false)
	public void delete(Record record) {
		super.delete(record);
	}
	
}