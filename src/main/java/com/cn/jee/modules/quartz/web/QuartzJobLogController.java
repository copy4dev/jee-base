/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cn.jee.modules.quartz.web;

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
import com.cn.jee.common.web.BaseController;
import com.cn.jee.common.utils.StringUtils;
import com.cn.jee.modules.quartz.entity.QuartzJobLog;
import com.cn.jee.modules.quartz.service.QuartzJobLogService;

/**
 * 定时任务调度日志Controller
 * @author 1002360
 * @version 2017-02-18
 */
@Controller
@RequestMapping(value = "${adminPath}/quartz/quartzJobLog")
public class QuartzJobLogController extends BaseController {

	@Autowired
	private QuartzJobLogService quartzJobLogService;
	
	@ModelAttribute
	public QuartzJobLog get(@RequestParam(required=false) String id) {
		QuartzJobLog entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = quartzJobLogService.get(id);
		}
		if (entity == null){
			entity = new QuartzJobLog();
		}
		return entity;
	}
	
	@RequiresPermissions("quartz:quartzJobLog:view")
	@RequestMapping(value = {"list", ""})
	public String list(QuartzJobLog quartzJobLog, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<QuartzJobLog> page = quartzJobLogService.findPage(new Page<QuartzJobLog>(request, response), quartzJobLog); 
		model.addAttribute("page", page);
		return "modules/quartz/quartzJobLogList";
	}

	@RequiresPermissions("quartz:quartzJobLog:view")
	@RequestMapping(value = "form")
	public String form(QuartzJobLog quartzJobLog, Model model) {
		model.addAttribute("quartzJobLog", quartzJobLog);
		return "modules/quartz/quartzJobLogForm";
	}

	@RequiresPermissions("quartz:quartzJobLog:edit")
	@RequestMapping(value = "save")
	public String save(QuartzJobLog quartzJobLog, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, quartzJobLog)){
			return form(quartzJobLog, model);
		}
		quartzJobLogService.save(quartzJobLog);
		addMessage(redirectAttributes, "保存定时任务调度日志成功");
		return "redirect:"+Global.getAdminPath()+"/quartz/quartzJobLog/?repage";
	}
	
	@RequiresPermissions("quartz:quartzJobLog:edit")
	@RequestMapping(value = "delete")
	public String delete(QuartzJobLog quartzJobLog, RedirectAttributes redirectAttributes) {
		quartzJobLogService.delete(quartzJobLog);
		addMessage(redirectAttributes, "删除定时任务调度日志成功");
		return "redirect:"+Global.getAdminPath()+"/quartz/quartzJobLog/?repage";
	}

}