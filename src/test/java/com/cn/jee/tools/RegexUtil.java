package com.cn.jee.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式使用工具
 * 
 * @author ccp
 *
 */
public class RegexUtil {

	// ------------------ part 1: 字符串匹配 ---------------------

	/**
	 * 手机号码匹配<br/>
	 * 匹配：1[34578]\\d{9}
	 * 
	 * @param phoneNum
	 * @return
	 */
	public static final boolean checkMobileNumber(String phoneNum) {
		return phoneNum.matches("1[34578]\\d{9}");
	}

	/**
	 * 邮箱匹配
	 * 
	 * @param mailAdr
	 * @return
	 */
	public static final boolean checkMailAdr(String mailAdr) {
		return mailAdr.matches("^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
	}

	/**
	 * QQ号匹配
	 * 
	 * @param mailAdr
	 * @return
	 */
	public static final boolean checkQQNum(String qqNum) {
		return qqNum.matches("^[0-9]{5,10}$");
	}

	/**
	 * 括号匹配
	 * @param str
	 * @return
	 */
	public static final boolean checkBrackets(String str) {
		return str.matches("^(.*?)\\((.*?)\\)(.*?)$");
	}

	/**
	 * 字符串匹配
	 * 
	 * @param str
	 * @param rule
	 * @return
	 */
	public static final boolean checkStr(String str, String rule) {
		return str.matches(rule);
	}

	// ---------------------- part 2 字符串切割 ---------------------

	/**
	 * 以","切割字符串
	 * 
	 * @param str
	 * @return
	 */
	public static final String[] splitByComma(String str) {
		return str.split(",");
	}

	/**
	 * 以"."切割字符串
	 * 
	 * @param str
	 * @return
	 */
	public static final String[] splitByPoint(String str) {
		return str.split("\\.");
	}

	/**
	 * 以回车符切割字符串
	 * 
	 * @param str
	 * @return
	 */
	public static final String[] splitByEnter(String str) {
		return str.split("\\r");
	}

	/**
	 * 以换行符切割字符串
	 * 
	 * @param str
	 * @return
	 */
	public static final String[] splitByNewLine(String str) {
		return str.split("\\n");
	}

	/**
	 * 以空白符切割字符串 "+" -> 一个或多个
	 * 
	 * @param str
	 * @return
	 */
	public static final String[] splitBySpace(String str) {
		return str.split("\\s+");
	}

	/**
	 * 以空格符切割字符串
	 * 
	 * @param str
	 * @return
	 */
	public static final String[] splitBySpaceOnly(String str) {
		return str.split("[ ]+");
	}

	/**
	 * 字符串切割
	 * 
	 * @param str
	 * @param rule
	 * @return
	 */
	public static final String[] strSplit(String str, String rule) {
		return str.split(rule);
	}

	// ---------------------- part 3 字符串载取 ---------------------

	/**
	 * 字符串载取
	 * 
	 * @param str
	 * @param rule
	 * @return strList is not null
	 */
	public static final List<String> getStrings(String str, String rule) {
		Pattern p = Pattern.compile(rule);
		Matcher m = p.matcher(str);
		ArrayList<String> strList = new ArrayList<String>();
		while (m.find()) {
			strList.add(m.group(1));
		}
		return strList;
	}

	/**
	 * 以"."截取字符串<br/>
	 * rule: "(.*?)\\."
	 * 
	 * @return
	 */
	public static final ArrayList<String> getStrByPoint(String str) {
		Pattern p = Pattern.compile("(.*?)\\.");
		Matcher m = p.matcher(str);
		ArrayList<String> strs = new ArrayList<String>();
		while (m.find()) {
			strs.add(m.group(1));
		}
		return strs;
	}

	// ---------------------- part 4 字符串替换 ---------------------

	/**
	 * 字符串替换-正则匹配完全替换<br/>
	 * eg:str="12.3";src="\\.";desc="_";out=12_3
	 * 
	 * @param str
	 * @param src
	 * @param desc
	 * @return
	 */
	public static String replaceByAll(String str, String regex, String replacement) {
		str = str.replaceAll(regex, replacement);
		return str;
	}

	/**
	 * 字符串替换-正则匹配替换第一个
	 * 
	 * @param str
	 * @param src
	 * @param desc
	 * @return
	 */
	public static String replaceByFirst(String str, String regex, String replacement) {
		str = str.replaceFirst(regex, replacement);
		return str;
	}

	/**
	 * 字符串替换-单字符匹配完全替换
	 * 
	 * @param str
	 * @param oldChar
	 * @param newChar
	 * @return
	 */
	public static String replaceByChar(String str, char oldChar, char newChar) {
		str = str.replace(oldChar, newChar);
		return str;
	}

	/**
	 * 字符串替换-字符串匹配完全替换
	 * 
	 * @param str
	 * @param target
	 * @param replacement
	 * @return
	 */
	public static String replaceByString(String str, String target, String replacement) {
		str = str.replace(target, replacement);
		return str;
	}

}
