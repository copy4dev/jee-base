package com.cn.jee.tools.security.rsa;

public class MainTest {

	private static String SRC = "abc";

	public static void main(String[] args) {
		try {

			// 密钥存放位置
			String filepath = "";

			// 密钥对生成
			createKey(filepath, true);

			// 公钥加密私钥解密过程
			code_1(filepath, false);

			// 私钥加密公钥解密过程
			code_2(filepath, false);

			//签名验证
			sign(filepath, true);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	/** 密钥对生成 */
	public static void createKey(String filepath, boolean isRun) {
		if (!isRun)
			return;
		RSAEncrypt.genKeyPair(filepath);
	}

	/**
	 * 公钥加密私钥解密过程
	 * 
	 * @throws Exception
	 */
	public static void code_1(String filepath, boolean isRun) throws Exception {
		if (!isRun)
			return;
		System.out.println("--------------公钥加密私钥解密过程-------------------");
		String plainText = SRC;
		// 公钥加密过程
		byte[] cipherData = RSAEncrypt.encrypt(RSAEncrypt.loadPublicKeyByStr(RSAEncrypt.loadPublicKeyByFile(filepath)), plainText.getBytes());
		String cipher = Base64.encode(cipherData);
		// 私钥解密过程
		byte[] res = RSAEncrypt.decrypt(RSAEncrypt.loadPrivateKeyByStr(RSAEncrypt.loadPrivateKeyByFile(filepath)), Base64.decode(cipher));
		String restr = new String(res);
		System.out.println("原文：" + plainText);
		System.out.println("加密：" + cipher);
		System.out.println("解密：" + restr);
		System.out.println();
	}

	/**
	 * 私钥加密公钥解密过程
	 * 
	 * @throws Exception
	 */
	public static void code_2(String filepath, boolean isRun) throws Exception {
		if (!isRun)
			return;
		System.out.println("--------------私钥加密公钥解密过程-------------------");
		String plainText = SRC;
		// 私钥加密过程
		byte[] cipherData = RSAEncrypt.encrypt(RSAEncrypt.loadPrivateKeyByStr(RSAEncrypt.loadPrivateKeyByFile(filepath)), plainText.getBytes());
		String cipher = Base64.encode(cipherData);
		// 公钥解密过程
		byte[] res = RSAEncrypt.decrypt(RSAEncrypt.loadPublicKeyByStr(RSAEncrypt.loadPublicKeyByFile(filepath)), Base64.decode(cipher));
		String restr = new String(res);
		System.out.println("原文：" + plainText);
		System.out.println("加密：" + cipher);
		System.out.println("解密：" + restr);
		System.out.println();
	}

	/**
	 * 签名验证<br/>
	 * 私钥加密-公钥解密
	 * 
	 * @throws Exception
	 */
	public static void sign(String filepath, boolean isRun) throws Exception {
		if (!isRun)
			return;
		System.out.println("---------------签名过程------------------");
		String content = SRC;
		String signstr = RSASignature.sign(content, RSAEncrypt.loadPrivateKeyByFile(filepath));
		System.out.println("签名原串：" + content);
		System.out.println("签名串：" + signstr);
		System.out.println("验签结果：" + RSASignature.doCheck(content, signstr, RSAEncrypt.loadPublicKeyByFile(filepath)));
		System.out.println();
	}

}
