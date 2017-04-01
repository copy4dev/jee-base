/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cn.jee.modules.sys.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cn.jee.common.config.Global;
import com.cn.jee.common.utils.StringUtils;
import com.cn.jee.common.web.BaseController;
import com.cn.jee.modules.sys.entity.Record;
import com.cn.jee.modules.sys.service.RecordService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 数据权限Controller
 * 
 * @author admin
 * @version 2017-02-10
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/record")
public class RecordController extends BaseController {

	@Autowired
	private RecordService recordService;

	@ModelAttribute
	public Record get(@RequestParam(required = false) String id) {
		Record entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = recordService.get(id);
		}
		if (entity == null) {
			entity = new Record();
		}
		return entity;
	}

	@RequiresPermissions("sys:record:view")
	@RequestMapping(value = { "list", "" })
	public String list(Record record, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<Record> list = recordService.findList(record);
		model.addAttribute("list", list);
		return "modules/sys/recordList";
	}

	@RequiresPermissions("sys:record:view")
	@RequestMapping(value = "form")
	public String form(Record record, Model model) {
		if (record.getParent() != null && StringUtils.isNotBlank(record.getParent().getId())) {
			record.setParent(recordService.get(record.getParent().getId()));
			// 获取排序号，最末节点排序号+30
			if (StringUtils.isBlank(record.getId())) {
				Record recordChild = new Record();
				recordChild.setParent(new Record(record.getParent().getId()));
				List<Record> list = recordService.findList(record);
				if (list.size() > 0) {
					record.setSort(list.get(list.size() - 1).getSort());
					if (record.getSort() != null) {
						record.setSort(record.getSort() + 30);
					}
				}
			}
		}
		if (record.getSort() == null) {
			record.setSort(30);
		}
		model.addAttribute("record", record);
		return "modules/sys/recordForm";
	}

	@RequiresPermissions("sys:record:edit")
	@RequestMapping(value = "save")
	public String save(Record record, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, record)) {
			return form(record, model);
		}
		recordService.save(record);
		addMessage(redirectAttributes, "保存数据权限成功");
		return "redirect:" + Global.getAdminPath() + "/sys/record/?repage";
	}

	@RequiresPermissions("sys:record:edit")
	@RequestMapping(value = "delete")
	public String delete(Record record, RedirectAttributes redirectAttributes) {
		recordService.delete(record);
		addMessage(redirectAttributes, "删除数据权限成功");
		return "redirect:" + Global.getAdminPath() + "/sys/record/?repage";
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required = false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<Record> list = recordService.findList(new Record());
		for (int i = 0; i < list.size(); i++) {
			Record e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId != null && !extId.equals(e.getId()) && e.getParentIds().indexOf("," + extId + ",") == -1)) {
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("name", e.getName());
				mapList.add(map);
			}
		}
		return mapList;
	}

}