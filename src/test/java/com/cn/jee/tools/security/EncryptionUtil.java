package com.cn.jee.tools.security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * 常规加密与时间戳
 * 
 * @author 1002360
 * @date 2016年11月18日
 *
 */
public class EncryptionUtil {

	public static void main(String[] args) {

		String string = "123";

		try {

			// MD5加密
			System.out.println(EncoderByMd5(string));
			System.out.println(EncoderByMd5_32(string));

			// SHA1加密
			System.out.println(getSHA1(string));

			// Base64加密
			System.out.println(getBase64Encode(string));

			// 时间戳
			System.out.println(getTimeSpan(new Date()));

		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}

	/**
	 * MD5加密(24位)
	 * 
	 * @param str
	 * @return
	 * @throws NoSuchAlgorithmException 没有MD5算法
	 * @throws UnsupportedEncodingException 转utf-8异常
	 */
	public static String EncoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		// 确定计算方法
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		// 加密后的字符串
		String cipherText = Base64.encodeBase64String(md5.digest(str.getBytes("utf-8")));
		return cipherText;
	}

	/**
	 * MD5加密(32位)
	 * 
	 * @param src
	 * @return
	 */
	public static String EncoderByMd5_32(String src) {
		return DigestUtils.md5Hex(src);
	}

	/**
	 * 获取Base64密文
	 * 
	 * @param src
	 * @return
	 * @throws UnsupportedEncodingException 转utf-8异常
	 */
	public static String getBase64Encode(String src) throws UnsupportedEncodingException {
		return Base64.encodeBase64String(src.getBytes("utf-8"));
	}

	/**
	 * 获取毫秒级时间戳<br/>
	 * 可通过取商进行调整
	 *
	 */
	public static long getTimeSpan(Date time) {
		// return System.currentTimeMillis() / 1000;
		return System.currentTimeMillis();
	}

	/**
	 * 获取SHA1密文
	 * 
	 * @param src
	 * @return
	 */
	public static String getSHA1(String src) {
		return DigestUtils.sha1Hex(src);
	}

}
