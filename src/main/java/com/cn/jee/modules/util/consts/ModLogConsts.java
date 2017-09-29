package com.cn.jee.modules.util.consts;

/**
 * 模块日志常量存放点
 * 
 * @author 1002360
 * @version 2017年2月16日下午8:10:39 1002360 新建
 */
public interface ModLogConsts {

    /** 模块日志实体名称 */
    String MOD_LOG = "modLog";

    /** 模块日志数据权限字段：日志类型 */
    String MOD_LOG_TYPE = "logType";

    /** 模块日志类型:异常 */
    String MOD_LOG_TYPE_ERROR = "0";

    /** 模块日志类型:警告 */
    String MOD_LOG_TYPE_WARN = "1";

    /** 模块日志类型:信息 */
    String MOD_LOG_TYPE_INFO = "2";

    /** 模块日志所属模块:调试 */
    String MOD_LOG_MODEL_DEBUG = "3";

}
