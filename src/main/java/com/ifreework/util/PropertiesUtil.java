package com.ifreework.util;

import java.util.Properties;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * 
 * 描述： 读取Properties文件的例子
 * 
 * @author：wangyh qq735789026
 * @创建时间：2016年7月5日 下午3:56:16
 * @修改人：wangyh
 * @修改时间：2016年7月5日 下午3:56:16
 * @version 1.0
 */
public final class PropertiesUtil {
	static Logger logger = Logger.getLogger(PropertiesUtil.class);

	public static String getProperty(String filePath, String key) {
		Properties prop = new Properties();
		InputStream in;
		String value = null;
		try {
			in = new FileInputStream(new File(filePath));
			prop.load(in);
			value = prop.getProperty(key);
			value = new String(value.getBytes("ISO-8859-1"), "UTF-8");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(key + ":" + value + "不存在", e);
			e.printStackTrace();
		}
		return value;

	}

}