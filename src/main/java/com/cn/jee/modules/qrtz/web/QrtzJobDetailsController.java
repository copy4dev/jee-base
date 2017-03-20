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
import com.cn.jee.modules.qrtz.entity.QrtzJobDetails;
import com.cn.jee.modules.qrtz.service.QrtzJobDetailsService;

/**
 * 触发器Job明细Controller
 * 
 * @author 1002360
 * @version 2017-03-18
 */
@Controller
@RequestMapping(value = "${adminPath}/qrtz/qrtzJobDetails")
public class QrtzJobDetailsController extends BaseController {

	@Autowired
	private QrtzJobDetailsService qrtzJobDetailsService;

	@ModelAttribute
	public QrtzJobDetails get(@RequestParam(value = "schedName", required = false) String schedName, @RequestParam(value = "jobName", required = false) String jobName, @RequestParam(value = "jobGroup", required = false) String jobGroup) {
		QrtzJobDetails entity = null;
		if (StringUtils.isNotBlank(schedName) && StringUtils.isNotBlank(jobName) && StringUtils.isNotBlank(jobGroup)) {
			entity = qrtzJobDetailsService.get(schedName, jobName, jobGroup);
		}
		if (entity == null) {
			entity = new QrtzJobDetails();
		}
		return entity;
	}

	@RequiresPermissions("qrtz:qrtzJobDetails:view")
	@RequestMapping(value = { "list", "" })
	public String list(QrtzJobDetails qrtzJobDetails, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<QrtzJobDetails> page = qrtzJobDetailsService.findPage(new Page<QrtzJobDetails>(request, response), qrtzJobDetails);
		model.addAttribute("page", page);
		return "modules/qrtz/qrtzJobDetailsList";
	}

	@RequiresPermissions("qrtz:qrtzJobDetails:view")
	@RequestMapping(value = "form")
	public String form(QrtzJobDetails qrtzJobDetails, Model model) {
		model.addAttribute("qrtzJobDetails", qrtzJobDetails);
		return "modules/qrtz/qrtzJobDetailsForm";
	}

	@RequiresPermissions("qrtz:qrtzJobDetails:edit")
	@RequestMapping(value = "save")
	public String save(QrtzJobDetails qrtzJobDetails, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, qrtzJobDetails)) {
			return form(qrtzJobDetails, model);
		}
		qrtzJobDetailsService.save(qrtzJobDetails);
		addMessage(redirectAttributes, "保存任务明细成功");
		return "redirect:" + Global.getAdminPath() + "/qrtz/qrtzJobDetails/?repage";
	}

	@RequiresPermissions("qrtz:qrtzJobDetails:edit")
	@RequestMapping(value = "delete")
	public String delete(QrtzJobDetails qrtzJobDetails, RedirectAttributes redirectAttributes) {
		qrtzJobDetailsService.delete(qrtzJobDetails);
		addMessage(redirectAttributes, "删除触发器Job明细成功");
		return "redirect:" + Global.getAdminPath() + "/qrtz/qrtzJobDetails/?repage";
	}

}