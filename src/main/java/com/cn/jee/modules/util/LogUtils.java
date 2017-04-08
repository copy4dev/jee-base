package com.cn.jee.modules.util;

import com.cn.jee.common.utils.SpringContextHolder;
import com.cn.jee.modules.log.service.ModLogService;

public class LogUtils {

	private static ModLogService bsLicenseDefinedService = SpringContextHolder.getBean(ModLogService.class);

	/**
	 * 创建模块日志
	 * 
	 * @param logType 0:异常; 1:警告; 2:信息
	 * @param moduleType 功能模块
	 * @param notes 普通摘要
	 * @param expection 异常信息
	 */
	public static void addModLog(String logType, String moduleType, String notes, String expection) {
		bsLicenseDefinedService.addLog(logType, moduleType, notes, expection);
	}
}
