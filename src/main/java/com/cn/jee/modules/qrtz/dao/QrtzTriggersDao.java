/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cn.jee.modules.qrtz.dao;

import com.cn.jee.common.persistence.CrudDao;
import com.cn.jee.common.persistence.annotation.MyBatisDao;
import com.cn.jee.modules.qrtz.entity.QrtzTriggers;

/**
 * 触发器记录DAO接口
 * 
 * @author 1002360
 * @version 2017-03-18
 */
@MyBatisDao
public interface QrtzTriggersDao extends CrudDao<QrtzTriggers> {

    /**
     * 根据组合主键查询
     * 
     * @param schedName 调度器名称
     * @param triggerName 触发器名称
     * @param triggerGroup 触发器所属组
     * @return
     */
    public QrtzTriggers get(String schedName, String triggerName, String triggerGroup);

    /**
     * 把定时任务的执行时间记录到描述里
     * 
     * @param description 描述
     * @param triggerName xxxTrigger
     */
    public void remarkRunTime(String description, String triggerName);

    /**
     * 根据触发器名称查询一条记录
     * 
     * @param triggerName xxxTrigger
     * @return
     */
    public QrtzTriggers getByTriggerName(String triggerName);

}