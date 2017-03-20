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
import com.cn.jee.modules.qrtz.dao.QrtzJobDetailsDao;
import com.cn.jee.modules.qrtz.entity.QrtzJobDetails;

/**
 * 触发器Job明细Service
 * 
 * @author 1002360
 * @version 2017-03-18
 */
@Service
@Transactional(readOnly = true)
public class QrtzJobDetailsService extends CrudService<QrtzJobDetailsDao, QrtzJobDetails> {

	@Autowired
	private QrtzJobDetailsDao qrtzJobDetailsDao;

	/**
	 * 根据组合主键查询
	 * 
	 * @param schedName 调度器名称
	 * @param jobName 任务名称
	 * @param jobGroup 任务所属组
	 * @return
	 */
	public QrtzJobDetails get(String schedName, String jobName, String jobGroup) {
		return qrtzJobDetailsDao.get(schedName, jobName, jobGroup);
	}

	public List<QrtzJobDetails> findList(QrtzJobDetails qrtzJobDetails) {
		return super.findList(qrtzJobDetails);
	}

	public Page<QrtzJobDetails> findPage(Page<QrtzJobDetails> page, QrtzJobDetails qrtzJobDetails) {
		return super.findPage(page, qrtzJobDetails);
	}

	@Transactional(readOnly = false)
	public void save(QrtzJobDetails qrtzJobDetails) {
		if (StringUtils.isNotBlank(qrtzJobDetails.getSchedName()) && StringUtils.isNotBlank(qrtzJobDetails.getJobName()) && StringUtils.isNotBlank(qrtzJobDetails.getJobGroup())) {
			qrtzJobDetails.setId("t");//设置临时ID以进行更新操作
			super.save(qrtzJobDetails);
		}
	}

	@Transactional(readOnly = false)
	public void delete(QrtzJobDetails qrtzJobDetails) {
		super.delete(qrtzJobDetails);
	}

}