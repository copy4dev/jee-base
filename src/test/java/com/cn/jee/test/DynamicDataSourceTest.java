package com.cn.jee.test;

import com.cn.jee.common.db.DynamicDataSource;

/**
 * 多数据源切换<br>
 * 相关文件:spring-context.xml,Global.java
 * 
 * @author Admin
 * @version 2017年1月17日 下午11:54:30
 */
public class DynamicDataSourceTest {

    public static void main(String[] args) {
        DynamicDataSource.setCurrentLookupKey("dataSource2nd");// 切换数据源dataSource2nd
        System.out.println("===============警告:数据库方言并没有切换===============");
        System.err.println("=======Global.getConfig('jdbc.def.type')=======");
        DynamicDataSource.setCurrentLookupKey("dataSourceDef");// 切换回默认数据源dataSourceDef
    }
}
