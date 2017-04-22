/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cn.jee.modules.qrtz.service;

import java.util.List;

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

    /**
     * 根据组合主键查询
     * 
     * @param schedName 调度器名称
     * @param triggerName 触发器名称
     * @param triggerGroup 触发器所属组
     * @return
     */
    public QrtzTriggers get(String schedName, String triggerName, String triggerGroup) {
        return dao.get(schedName, triggerName, triggerGroup);
    }

    public List<QrtzTriggers> findList(QrtzTriggers qrtzTriggers) {
        return super.findList(qrtzTriggers);
    }

    public Page<QrtzTriggers> findPage(Page<QrtzTriggers> page, QrtzTriggers qrtzTriggers) {
        return super.findPage(page, qrtzTriggers);
    }

    @Transactional(readOnly = false)
    public void save(QrtzTriggers qrtzTriggers) {
        if (StringUtils.isNotBlank(qrtzTriggers.getSchedName())
                && StringUtils.isNotBlank(qrtzTriggers.getTriggerName())
                && StringUtils.isNotBlank(qrtzTriggers.getTriggerGroup())) {
            qrtzTriggers.setId("t");// 设置临时ID以进行更新操作
            super.save(qrtzTriggers);
        }
    }

    @Transactional(readOnly = false)
    public void delete(QrtzTriggers qrtzTriggers) {
        super.delete(qrtzTriggers);
    }

    /**
     * 追加任务执行时间到"描述"
     * 
     * @param l 秒
     * @param triggerName xxxTrigger
     */
    @Transactional(readOnly = false)
    public void remarkRunTime(long l, String triggerName) {
        QrtzTriggers qrtzTriggers = dao.getByTriggerName(triggerName);
        if (qrtzTriggers == null) {
            return;
        }
        StringBuffer sb = new StringBuffer();
        sb.append("[ 耗时 ").append(l).append(" 秒 ] ");
        String d = qrtzTriggers.getDescription();
        if(StringUtils.isNoneBlank(d)){
            String s = d.substring(d.indexOf("]")+1);
            if (StringUtils.isNoneBlank(s)) {
                sb.append(s);
            }
        }
        qrtzTriggers.setDescription(sb.toString());
        qrtzTriggers.preUpdate();
        dao.update(qrtzTriggers);
    }

    /**
     * 根据触发器名称查询一条记录
     * 
     * @param triggerName xxxTrigger
     * @return
     */
    public QrtzTriggers getByTriggerName(String triggerName) {
        return dao.getByTriggerName(triggerName);
    }

}