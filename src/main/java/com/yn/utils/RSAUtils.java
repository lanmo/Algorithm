package com.yn.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.io.FileUtils;

/**
 * Copyright (C), nanyang205380@sohu-inc.com.
 * 
 * @ClassName: RSAUtils
 * @Description:RSA加密解密
 * @date 2015年1月28日 上午9:36:30
 */
public final class RSAUtils {

	/** 加密算法 */
	public final static String KEY_ALGORITHM = "RSA";
	/** 获取公钥 */
	public final static String PUBLIC_KEY = "RSAPublicKey";
	/** 获取私钥 */
	public final static String PRIVATE_KEY = "RSAPrivateKey";
	/** RSA最大加密明文大小 */
	public final static int MAX_ENCRYPT_BLOCK = 117;
	/** RSA最大解密密文大小 */
	public final static int MAX_DECRYPT_BLOCK = 128;
	/** RSA 密钥长度(1024(单位是位，也就是 bit)，生成的密钥长度就是 1024位 / 8位/字节 = 128字节) */
	public final static int MAX_KEY_SIZE = 1024;
	/** 后缀名 */
	private final static String SUFFIX = ".xml";
	/** 加密/解密填充算法 */
	public final static String RSA = "RSA/ECB/PKCS1Padding";

	/** 密钥对 */
	private static Map<String, Object> keys = new HashMap<String, Object>();

	static {
		try {
			keys = loadKey();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author: YangNan(杨楠)
	 * @date: 2015年1月28日 上午9:44:17
	 * @Title: genKeyPair
	 * @Description: 生成密钥对,公钥和私钥
	 * @throws:
	 */
	public static Map<String, Object> genKeyPair() throws Exception {

		KeyPairGenerator keyPairGenerator = KeyPairGenerator
				.getInstance(KEY_ALGORITHM);
		keyPairGenerator.initialize(MAX_KEY_SIZE, new SecureRandom());
		KeyPair keyPair = keyPairGenerator.generateKeyPair();

		// 公钥
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		// 私钥
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put(PUBLIC_KEY, publicKey);
		map.put(PRIVATE_KEY, privateKey);

		return map;
	}

	/**
	 * @author: YangNan(杨楠)
	 * @date: 2015年1月28日 上午9:56:18
	 * @Title: decrypt
	 * @Description: 私钥解密
	 * @param encryptedData
	 *            待解密的加密数据
	 * @param privateKey
	 *            私钥(BASE64编码)
	 * @throws:
	 */
	public static byte[] decrypt(byte[] encryptedData) throws Exception {

		RSAPrivateKey privateKey = (RSAPrivateKey) keys.get(PRIVATE_KEY);
		// 对数据解密
		Cipher cipher = Cipher.getInstance(RSA);
		cipher.init(Cipher.DECRYPT_MODE, privateKey);

		int length = encryptedData.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 分段解密,解密最大值为128
		while (length - offSet > 0) {
			if (length - offSet > MAX_DECRYPT_BLOCK) {
				cache = cipher
						.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(encryptedData, offSet, length - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_DECRYPT_BLOCK;
		}

		byte[] decryptedData = out.toByteArray();
		out.close();

		return decryptedData;
	}

	/**
	 * @author: YangNan(杨楠)
	 * @date: 2015年1月28日 上午10:26:46
	 * @Title: encrypt
	 * @Description: 公钥加密
	 * @param data
	 *            待加密的数据
	 * @param publicKey
	 *            公钥(BASE64编码)
	 * @throws:
	 */
	public static byte[] encrypt(byte[] data) throws Exception {

		RSAPublicKey publicKey = (RSAPublicKey) keys.get(PUBLIC_KEY);
		// 对数据加密
		Cipher cipher = Cipher.getInstance(RSA);
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);

		int length = data.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 分段加密,加密的最大值为117
		while (length - offSet > 0) {
			if (length - offSet > MAX_ENCRYPT_BLOCK) {
				cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(data, offSet, length - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_ENCRYPT_BLOCK;
		}

		byte[] encryptedData = out.toByteArray();
		out.close();

		return encryptedData;
	}

	/**
	 * @author: YangNan(杨楠)
	 * @date: 2015年1月28日 上午10:34:04
	 * @Title: getPrivateKey
	 * @Description: 获取私钥,返回base64编码的字符串
	 * @param keyMap
	 */
	public static String getPrivateKey(Map<String, Object> keyMap) {
		RSAPrivateKey privateKey = (RSAPrivateKey) keyMap.get(PRIVATE_KEY);
		return Base64.encodeBase64String(privateKey.getEncoded());
	}

	/**
	 * @author: YangNan(杨楠)
	 * @date: 2015年1月28日 上午10:34:04
	 * @Title: getPrivateKey
	 * @Description: 获取私钥 返回base64编码的字符串
	 * @param keyMap
	 */
	public static String getPublicKey(Map<String, Object> keyMap) {
		RSAPublicKey publicKey = (RSAPublicKey) keyMap.get(PUBLIC_KEY);
		return Base64.encodeBase64String(publicKey.getEncoded());
	}

	/**
	 * @author: YangNan(杨楠)
	 * @date: 2015年1月28日 上午11:10:42
	 * @Title: saveKey
	 * @Description: 保存密钥对
	 * @throws:
	 */
	public static void saveKey() throws Exception {
		Map<String, Object> keyMap = genKeyPair();
		String privateKey = getPrivateKey(keyMap);
		String publicKey = getPublicKey(keyMap);
		String filePath = RSAUtils.class.getResource("/").toURI().getPath();
		filePath = filePath.replace("target/classes/", "src/main/java/rsa/");
		// 保存私钥
		XMLConfiguration privateConfig = new XMLConfiguration();
		privateConfig.setProperty(PRIVATE_KEY, privateKey);
		privateConfig.save(filePath + PRIVATE_KEY + SUFFIX);

		// 保存公钥
		XMLConfiguration publicConfig = new XMLConfiguration();
		publicConfig.setProperty(PUBLIC_KEY, publicKey);
		publicConfig.save(filePath + PUBLIC_KEY + SUFFIX);

	}
	
	/**
	 * @author: YangNan(杨楠)
	 * @date: 2015年1月28日 上午11:10:42
	 * @Title: saveKey
	 * @Description: 保存密钥对,用mac电脑生成的公钥和密钥对
	 * @throws:
	 */
	public static void saveKeyPem() throws Exception {

		String filePath = RSAUtils.class.getResource("/").toURI().getPath();
		filePath = filePath.replace("target/classes/", "src/main/java/rsa/");
		
		String privatePath = filePath + "pkcs8_private_key.pem";
		String publicPath = filePath + "rsa_public_key.pem";
		// 获取私钥
		File privateFile = new File(privatePath);
		List<String> privateKeys = FileUtils.readLines(privateFile);
		StringBuffer privateKey = new StringBuffer();
		for(int i=1; i<privateKeys.size()-1;i++) {
			privateKey.append(privateKeys.get(i));
		}
		
		// 获取公钥
		File publicFile = new File(publicPath);
		List<String> publicKeys = FileUtils.readLines(publicFile);
		StringBuffer publicKey = new StringBuffer();
		for(int i=1; i<publicKeys.size()-1;i++) {
			publicKey.append(publicKeys.get(i));
		}
		
		// 保存私钥
		XMLConfiguration privateConfig = new XMLConfiguration();
		privateConfig.setProperty(PRIVATE_KEY, privateKey.toString());
		privateConfig.save(filePath + PRIVATE_KEY + SUFFIX);

		// 保存公钥
		XMLConfiguration publicConfig = new XMLConfiguration();
		publicConfig.setProperty(PUBLIC_KEY, publicKey.toString());
		publicConfig.save(filePath + PUBLIC_KEY + SUFFIX);

	}

	/**
	 * @author: YangNan(杨楠)
	 * @date: 2015年1月28日 上午11:50:01
	 * @Title: loadKey
	 * @Description: 从文件中获取密钥对
	 * @throws:
	 */
	public static Map<String, Object> loadKey() throws Exception {

		Map<String, Object> key = new HashMap<String, Object>();
		
		String filePath = "rsa/";
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

		// 获取私钥
		XMLConfiguration privateConfig = new XMLConfiguration(filePath	+ PRIVATE_KEY + SUFFIX);
		byte[] privateKeyBytes = Base64.decodeBase64(privateConfig.getString(PRIVATE_KEY));
		PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
		RSAPrivateKey privatekey = (RSAPrivateKey) keyFactory.generatePrivate(privateKeySpec);
		key.put(PRIVATE_KEY, privatekey);

		// 获取公钥
		XMLConfiguration publicConfig = new XMLConfiguration(filePath + PUBLIC_KEY + SUFFIX);
		byte[] publicKeyBytes = Base64.decodeBase64(publicConfig.getString(PUBLIC_KEY));
		X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
		RSAPublicKey publickey = (RSAPublicKey) keyFactory.generatePublic(publicKeySpec);
		key.put(PUBLIC_KEY, publickey);

		return key;
	}
	
	public static void main(String[] args) throws Exception {
		
		System.out.println("base64");
		String abc = Base64.encodeBase64String("123".getBytes());
		System.out.println(abc);

		// 加密
		final byte[] data = ("0682d44382f05b45309932cbc3ea54b5058053ae|1422869886373")
				.getBytes();

		long start = System.currentTimeMillis();
		byte[] encryptData = encrypt(data);
		System.out.println(encryptData.length);
		System.out.println("耗时:" + (System.currentTimeMillis() - start));
		String encode = Base64.encodeBase64String(encryptData);
		System.out.println("加密数据:" + encode);

		start = System.currentTimeMillis();
		
		encode = "K3ZsckkI4u775H+JP/kDaYa/e/ZHSrh5SDLzRsYEgK72HHPF1+/Cx7bCFdELkmPKoPxVyuSohFsgTLcRhiFXPOniis6GwiVhtGRszXywjZSJhWtz79YvVSy+XZzCxJLWrTY1ZrjB5DfZ2oVEzvs9eO2OzLSjZZMpa65/JYLwgM4=";
		
		// //解密
		byte[] decryptData = decrypt(Base64.decodeBase64(encode.getBytes()));
		System.out.println("耗时:" + (System.currentTimeMillis() - start));
		System.out.println("解密数据:" + new String(decryptData, "UTF-8"));
		
	}

}
