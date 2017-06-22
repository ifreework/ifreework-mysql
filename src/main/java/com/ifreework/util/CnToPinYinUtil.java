
/**    
 * 文件名：CnToPinYin.java    
 *    
 * 版本信息：    
 * 日期：2016年7月2日    
 * Copyright  Corporation 2016     
 * 版权所有    
 *    
 */
package com.ifreework.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 描述：
 *     通过汉字获取汉字相对应的拼音首字母或者全拼
 * @author：wangyh qq735789026
 * @创建时间：2016年7月2日 下午5:08:39
 * @修改人：wangyh
 * @修改时间：2016年7月2日 下午5:08:39
 * @version 1.0
 */
public class CnToPinYinUtil {
	
	/**
	 * @Title: getPingYin
	 * @Description: TODO(得到全拼)
	 * @param 
	 * @return   
	 * @throws
	 */
	public static String getPingYin(String src) {
		char[] t1 = null;
		t1 = src.toCharArray();
		String[] t2 = new String[t1.length];
		HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
		t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		t3.setVCharType(HanyuPinyinVCharType.WITH_V);
		String t4 = "";
		int t0 = t1.length;
		try {
			for (int i = 0; i < t0; i++) {
				// 判断是否为汉字字符
				if (java.lang.Character.toString(t1[i]).matches("[\\u4E00-\\u9FA5]+")) {
					t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);
					t4 += t2[0];
				} else {
					t4 += java.lang.Character.toString(t1[i]);
				}
			}
			return t4;
		} catch (BadHanyuPinyinOutputFormatCombination e1) {
			e1.printStackTrace();
		}
		return t4;
	}

	/**
	 * 
	 * @Title: getPinYinHeadChar
	 * @Description: TODO(得到首字母)
	 * @param 
	 * @return   
	 * @throws
	 */
	public static String getPinYinHeadChar(String str) {

		String convert = "";
		for (int j = 0; j < str.length(); j++) {
			char word = str.charAt(j);
			String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
			if (pinyinArray != null) {
				convert += pinyinArray[0].charAt(0);
			} else {
				convert += word;
			}
		}
		return convert;
	}

	/**
	 * 将字符串转移为ASCII码
	 * 
	 * @param cnStr
	 * @return
	 */
	public static String getCnASCII(String cnStr) {
		StringBuffer strBuf = new StringBuffer();
		byte[] bGBK = cnStr.getBytes();
		for (int i = 0; i < bGBK.length; i++) {
			// System.out.println(Integer.toHexString(bGBK[i]&0xff));
			strBuf.append(Integer.toHexString(bGBK[i] & 0xff));
		}
		return strBuf.toString();
	}

	public static void main(String[] args) {

		String cnStr = "用于Web环境的实现，可以替代ServletContainerSessionManager，自己维护着会话，直接废弃了Servlet容器的会话管理。";
		System.out.println(getPingYin(cnStr));
		System.out.println(getPinYinHeadChar(cnStr));
	}
}
