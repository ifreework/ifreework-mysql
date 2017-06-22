package com.ifreework.util;

import java.security.MessageDigest;

/**
 * 
 * 描述：
 * 
 * @author：wangyh qq735789026
 * @创建时间：2016年7月4日 下午2:28:12
 * @修改人：wangyh
 * @修改时间：2016年7月4日 下午2:28:12
 * @version 1.0
 */
public class MD5 {

	public static String md5(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte b[] = md.digest();

			int i;

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			str = buf.toString();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return str;
	}

}
