package com.cn.jee.common.db;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 多数据源切换方案
 * 
 * @author 1002377
 *
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

	@Override
	protected Object determineCurrentLookupKey() {
		return getCurrentLookupKey();
	}

	public static String getCurrentLookupKey() {
		return (String) contextHolder.get();
	}

	public static void setCurrentLookupKey(String currentLookupKey) {
		contextHolder.set(currentLookupKey);
	}
}
