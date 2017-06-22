
/**    
 * 文件名：IPUtil.java    
 *    
 * 版本信息：    
 * 日期：2016年7月4日    
 * Copyright  Corporation 2016     
 * 版权所有    
 *    
 */
package com.ifreework.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

/**
 * 描述：
 * 
 * @author：wangyh qq735789026
 * @创建时间：2016年7月4日 下午2:58:11
 * @修改人：wangyh
 * @修改时间：2016年7月4日 下午2:58:11
 * @version 1.0
 */
public class IPUtil {

	/**
	 * 
	 * 描述：获取本机IP
	 * @Title: getIp
	 * @param 
	 * @return   
	 * @throws
	 */
	public static String getIp() {
		String ip = "";
		try {
			InetAddress inet = InetAddress.getLocalHost();
			ip = inet.getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		return ip;
	}
	
	/**
	 * 
	 * 描述：获取请求机器IP
	 * @Title: getIpAddr
	 * @param 
	 * @return   
	 * @throws
	 */
    public static String getIpAddr(HttpServletRequest request) {
        if (request == null) {
            return null;
        }
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
