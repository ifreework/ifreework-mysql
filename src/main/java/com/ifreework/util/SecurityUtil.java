package com.ifreework.util;

import com.sun.crypto.provider.SunJCE;
import java.security.Key;
import java.security.MessageDigest;
import java.security.Security;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Encoder;

/**
 * 
 * 描述：加密处理工具类    
 * @author：wangyh qq735789026  
 * @创建时间：2016年7月6日 下午3:03:44    
 * @修改人：wangyh    
 * @修改时间：2016年7月6日 下午3:03:44    
 * @version 1.0
 */
@SuppressWarnings("restriction")
public class SecurityUtil {
	private static String strDefaultKey = "national";

	private static Cipher encryptCipher = null;

	private static Cipher decryptCipher = null;

	public SecurityUtil() throws Exception {
		this(strDefaultKey);
	}

	public SecurityUtil(String strKey) throws Exception {
		Security.addProvider(new SunJCE());
		Key key = getKey(strKey.getBytes());

		encryptCipher = Cipher.getInstance("DES");
		encryptCipher.init(1, key);

		decryptCipher = Cipher.getInstance("DES");
		decryptCipher.init(2, key);
	}

	public static String byteArr2HexStr(byte[] arrB) throws Exception {
		int iLen = arrB.length;

		StringBuffer sb = new StringBuffer(iLen * 2);

		for (int i = 0; i < iLen; i++) {
			int intTmp = arrB[i];

			while (intTmp < 0) {
				intTmp += 256;
			}

			if (intTmp < 16) {
				sb.append("0");
			}
			sb.append(Integer.toString(intTmp, 16));
		}
		return sb.toString();
	}

	public static byte[] hexStr2ByteArr(String strIn) throws Exception {
		byte[] arrB = strIn.getBytes();
		int iLen = arrB.length;

		byte[] arrOut = new byte[iLen / 2];

		for (int i = 0; i < iLen; i += 2) {
			String strTmp = new String(arrB, i, 2);
			arrOut[(i / 2)] = ((byte) Integer.parseInt(strTmp, 16));
		}
		return arrOut;
	}

	public static byte[] encrypt(byte[] arrB) throws Exception {
		return encryptCipher.doFinal(arrB);
	}

	public static String encrypt(String key, String strIn) {
		try {
			init(key);
			return byteArr2HexStr(encrypt(strIn.getBytes()));
		} catch (Exception e) {
		}
		return "0";
	}

	public static String encrypt(String strIn) {
		try {
			init(strDefaultKey);
			return byteArr2HexStr(encrypt(strIn.getBytes()));
		} catch (Exception e) {
		}
		return "0";
	}

	public static byte[] decrypt(byte[] arrB) throws Exception {
		return decryptCipher.doFinal(arrB);
	}

	public static String decrypt(String key, String strIn) {
		try {
			init(key);
			return new String(decrypt(hexStr2ByteArr(strIn)));
		} catch (Exception e) {
		}
		return "0";
	}

	public static String decrypt(String strIn) {
		try {
			init(strDefaultKey);
			return new String(decrypt(hexStr2ByteArr(strIn)));
		} catch (Exception e) {
		}
		return "0";
	}

	private static Key getKey(byte[] arrBTmp) throws Exception {
		byte[] arrB = new byte[8];

		for (int i = 0; (i < arrBTmp.length) && (i < arrB.length); i++) {
			arrB[i] = arrBTmp[i];
		}

		Key key = new SecretKeySpec(arrB, "DES");

		return key;
	}

	public static void init(String strKey) throws Exception {
		Security.addProvider(new SunJCE());
		Key key = getKey(strKey.getBytes());

		encryptCipher = Cipher.getInstance("DES");
		encryptCipher.init(1, key);

		decryptCipher = Cipher.getInstance("DES");
		decryptCipher.init(2, key);
	}


	public static String encoderPwdByMd5(String str) {
		// 确定计算方法
		MessageDigest md5;
		try {
			md5 = MessageDigest.getInstance("MD5");
		
		BASE64Encoder base64en = new BASE64Encoder();
		
		// 加密后的字符串
		String newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));
		return newstr;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	public static void main(String[] args) { 
//		String[] strs = "/,/login,^/login/.*,^/resources/.*,/user".split(",");
		System.out.println("jiami----" + encrypt("1"));
//		System.out.println("jiamilenght----" + str.length());
//		System.out.println("jiemi-----" + decrypt(str));
	}
}