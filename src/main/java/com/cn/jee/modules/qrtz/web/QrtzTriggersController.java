/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cn.jee.modules.qrtz.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cn.jee.common.config.Global;
import com.cn.jee.common.persistence.Page;
import com.cn.jee.common.utils.StringUtils;
import com.cn.jee.common.web.BaseController;
import com.cn.jee.modules.qrtz.QuartzClusterMain;
import com.cn.jee.modules.qrtz.QuartzConsts;
import com.cn.jee.modules.qrtz.entity.QrtzTriggers;
import com.cn.jee.modules.qrtz.service.QrtzTriggersService;

/**
 * 触发器记录Controller
 * 
 * @author 1002360
 * @version 2017-03-18
 */
@Controller
@RequestMapping(value = "${adminPath}/qrtz/qrtzTriggers")
public class QrtzTriggersController extends BaseController implements QuartzConsts {

	@Autowired
	private QrtzTriggersService qrtzTriggersService;
	@Autowired
	private QuartzClusterMain quartzClusterMain;

	@ModelAttribute
	public QrtzTriggers get(@RequestParam(value = "schedName", required = false) String schedName, @RequestParam(value = "triggerName", required = false) String triggerName, @RequestParam(value = "triggerGroup", required = false) String triggerGroup) {
		QrtzTriggers entity = null;
		if (StringUtils.isNotBlank(schedName) && StringUtils.isNotBlank(triggerName) && StringUtils.isNotBlank(triggerGroup)) {
			entity = qrtzTriggersService.get(schedName, triggerName, triggerGroup);
		}
		if (entity == null) {
			entity = new QrtzTriggers();
		}
		return entity;
	}

	@RequiresPermissions("qrtz:qrtzTriggers:view")
	@RequestMapping(value = { "list", "" })
	public String list(QrtzTriggers qrtzTriggers, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<QrtzTriggers> page = qrtzTriggersService.findPage(new Page<QrtzTriggers>(request, response), qrtzTriggers);
		model.addAttribute("page", page);
		return "modules/qrtz/qrtzTriggersList";
	}

	@RequiresPermissions("qrtz:qrtzTriggers:view")
	@RequestMapping(value = "form")
	public String form(QrtzTriggers qrtzTriggers, Model model) {
		model.addAttribute("qrtzTriggers", qrtzTriggers);
		return "modules/qrtz/qrtzTriggersForm";
	}

	@RequiresPermissions("qrtz:qrtzTriggers:edit")
	@RequestMapping(value = "save")
	public String save(QrtzTriggers qrtzTriggers, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, qrtzTriggers)) {
			return form(qrtzTriggers, model);
		}
		qrtzTriggersService.save(qrtzTriggers);
		addMessage(redirectAttributes, "保存触发器记录成功");
		return "redirect:" + Global.getAdminPath() + "/qrtz/qrtzTriggers/?repage";
	}

	@RequiresPermissions("qrtz:qrtzTriggers:edit")
	@RequestMapping(value = "delete")
	public String delete(QrtzTriggers qrtzTriggers, RedirectAttributes redirectAttributes) {
		qrtzTriggersService.delete(qrtzTriggers);
		addMessage(redirectAttributes, "删除触发器记录成功");
		return "redirect:" + Global.getAdminPath() + "/qrtz/qrtzTriggers/?repage";
	}

	@RequiresPermissions("qrtz:qrtzTriggers:view")
	@RequestMapping(value = { "option" })
	public String option(QrtzTriggers qrtzTriggers, HttpServletRequest request, HttpServletResponse response, Model model) {
		// 更改任务状态
		changeStatus(qrtzTriggers);
		Page<QrtzTriggers> page = qrtzTriggersService.findPage(new Page<QrtzTriggers>(request, response), qrtzTriggers);
		model.addAttribute("page", page);
		return "redirect:" + Global.getAdminPath() + "/qrtz/qrtzTriggers/?repage";
	}

	/**
	 * 更改任务状态
	 * 
	 * @param qrtzTriggers
	 */
	private final void changeStatus(QrtzTriggers qrtzTriggers) {
		boolean success = false;
		String status = qrtzTriggers.getTriggerState();
		if (QRTZ_TRIGGER_PAUSED.equals(status)) {
			// 恢复
			success = quartzClusterMain.resumeJob(qrtzTriggers.getJobName(), qrtzTriggers.getJobGroup());
		} else if (QRTZ_TRIGGER_ACQUIRED.equals(status) || QRTZ_TRIGGER_WAITING.equals(status)) {
			// 挂起
			success = quartzClusterMain.pauseJob(qrtzTriggers.getJobName(), qrtzTriggers.getJobGroup());
		}
		if (!success) {
			// 异常处理
			logger.debug("error");
		}
	}

}