/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cn.jee.modules.log.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cn.jee.common.config.Global;
import com.cn.jee.common.persistence.Page;
import com.cn.jee.common.utils.DateUtils;
import com.cn.jee.common.utils.StringUtils;
import com.cn.jee.common.utils.excel.ExportExcel;
import com.cn.jee.common.utils.excel.ImportExcel;
import com.cn.jee.common.web.BaseController;
import com.cn.jee.modules.log.entity.ModLog;
import com.cn.jee.modules.log.service.ModLogService;
import com.google.common.collect.Lists;

/**
 * 模块日志Controller
 * 
 * @author admin
 * @version 2017-02-10
 */
@Controller
@RequestMapping(value = "${adminPath}/log/modLog")
public class ModLogController extends BaseController {

    @Autowired
    private ModLogService modLogService;

    @ModelAttribute
    public ModLog get(@RequestParam(required = false) String id) {
        ModLog entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = modLogService.get(id);
        }
        if (entity == null) {
            entity = new ModLog();
        }
        return entity;
    }

    @RequiresPermissions("log:modLog:view")
    @RequestMapping(value = { "list", "" })
    public String list(ModLog modLog, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<ModLog> page = modLogService.findPage(new Page<ModLog>(request, response), modLog);
        model.addAttribute("page", page);
        return "modules/log/modLogList";
    }

    @RequiresPermissions("log:modLog:view")
    @RequestMapping(value = "form")
    public String form(ModLog modLog, Model model) {
        model.addAttribute("modLog", modLog);
        return "modules/log/modLogForm";
    }

    @RequiresPermissions("log:modLog:edit")
    @RequestMapping(value = "save")
    public String save(ModLog modLog, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, modLog)) {
            return form(modLog, model);
        }
        modLogService.save(modLog);
        addMessage(redirectAttributes, "保存模块日志成功");
        return "redirect:" + Global.getAdminPath() + "/log/modLog/?repage";
    }

    @RequiresPermissions("log:modLog:edit")
    @RequestMapping(value = "delete")
    public String delete(ModLog modLog, RedirectAttributes redirectAttributes) {
        modLogService.delete(modLog);
        addMessage(redirectAttributes, "删除模块日志成功");
        return "redirect:" + Global.getAdminPath() + "/log/modLog/?repage";
    }

    /** 导出处理 */
    @RequiresPermissions("log:modLog:view")
    @RequestMapping(value = "export", method = RequestMethod.POST)
    public String export(ModLog modLog, HttpServletRequest request, HttpServletResponse response, Model model, RedirectAttributes redirectAttributes) {
        try {
            String fileName = "模块日志数据_" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
            List<ModLog> modList = modLogService.findList(modLog);
            new ExportExcel("模块日志数据", ModLog.class).setDataList(modList).write(request, response, fileName).dispose();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            addMessage(redirectAttributes, "导出模块日志失败！失败信息：" + e.getMessage());
            return "redirect:" + Global.getAdminPath() + "/log/modLog/?repage";
        }
    }

    /** 导入处理 */
    @RequiresPermissions("log:modLog:edit")
    @RequestMapping(value = "import", method = RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
        try {
            int successNum = 0;
            int failureNum = 0;
            StringBuilder failureMsg = new StringBuilder();
            ImportExcel ei = new ImportExcel(file, 1, 0);
            List<ModLog> list = ei.getDataList(ModLog.class);
            ModLog modLog = null;
            for (int i = 0, size = list.size(); i < size; i++) {
                modLog = list.get(i);
                try {
                    // 写上你的校验逻辑，然后保存...
                    modLogService.save(modLog);
                    successNum++;
                } catch (Exception e) {
                    e.printStackTrace();
                    failureMsg.append("<br/>第 " + i + " 条导入失败：" + e.getMessage());
                    failureNum++;
                    continue;
                }
            }

            if (failureNum > 0) {
                failureMsg.insert(0, "，失败 " + failureNum + " 条记录，导入信息如下：");
            }

            addMessage(redirectAttributes, "已成功导入 " + successNum + " 条记录" + failureMsg);

        } catch (Exception e) {
            addMessage(redirectAttributes, "导入日志数据失败！失败信息：" + e.getMessage());
        }
        return "redirect:" + adminPath + "/log/modLog/list?repage";
    }

    /** 下载导入用户数据模板 */
    @RequiresPermissions("log:modLog:view")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        ModLog ml = new ModLog();
        ml.setLogType("i");
        ml.setModuleType("2");
        ml.setEntityName("实体");
        ml.setBisMethod("业务方法");
        ml.setNotes("摘要");
        ml.setMsg("明细");

        try {
            String fileName = "模块日志数据导入模板.xlsx";
            List<ModLog> list = Lists.newArrayList();
            list.add(ml);
            new ExportExcel("模块日志数据", ModLog.class, 2).setDataList(list).write(request, response, fileName).dispose();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            addMessage(redirectAttributes, "导入模板下载失败！失败信息：" + e.getMessage());
            return "redirect:" + Global.getAdminPath() + "/log/modLog/?repage";
        }
    }

}