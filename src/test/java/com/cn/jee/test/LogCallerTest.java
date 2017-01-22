package com.cn.jee.test;

/**
 * 获取调用者的类名与方法名<br/>
 * 参考:http://hellboys.bokee.com/1904804.html<br>
 * 要求:JDK1.5+
 * 
 * @author Admin
 * @version 2017年1月19日 下午9:53:35
 */
public class LogCallerTest {

	public static void main(String[] args) {
		myLog();
	}

	private final static void myLog() {
		StackTraceElement[] trace = Thread.currentThread().getStackTrace();
		int idx = trace.length - 1;
		System.out.println(trace[idx].getClassName() + " - " + trace[idx].getMethodName());
	}
}
