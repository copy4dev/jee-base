package com.cn.jee.modules.qrtz;

/**
 * quartz 常量表
 * 
 * @author Admin
 * @version 2017年3月21日 下午10:25:12
 */
public interface QuartzContacts {

	/** 触发器状态-挂起 */
	public final static String QRTZ_TRIGGER_PAUSED = "PAUSED";

	/** 触发器状态-运行 */
	public final static String QRTZ_TRIGGER_ACQUIRED = "ACQUIRED"; 
	
	/** 触发器状态-等待 */
	public final static String QRTZ_TRIGGER_WAITING = "WAITING";
}
