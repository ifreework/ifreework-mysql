/**
 * Module:  Configs.java
 * Author:  wangyh
 * Purpose: 支付宝支付配置类
 **/
package com.ifreework.common.alipay.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AlipayConfigs {
	private Log log = LogFactory.getLog(AlipayConfigs.class);

	private  String openapidomain; // 支付宝openapi域名
	private String pid; // 商户应用id
	private String appid; // 商户应用id

	private  String privatekey; // RSA私钥，用于对商户请求报文加签
	private  String publickey; // RSA公钥，仅用于验证开发者网关
	private  String alipaypublickey; // 支付宝RSA公钥，用于验签支付宝应答

	private  String notifyurl;//异步通知地址
	private  String httpsverifyurl;//支付宝验签地址
	
	private  static Integer maxqueryretry = 5; // 最大查询次数
	private  static Long queryduration = 5000L; // 查询间隔（毫秒）

	private  static Integer maxcancelretry = 3; // 最大撤销次数
	private  static Long cancelduration = 2000L; // 撤销间隔（毫秒）

	private  static String timeoutexpress = "3m";// 订单超时时间

	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		this.log = log;
	}

	public String getOpenapidomain() {
		return openapidomain;
	}

	public void setOpenapidomain(String openapidomain) {
		this.openapidomain = openapidomain;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getPrivatekey() {
		return privatekey;
	}

	public void setPrivatekey(String privatekey) {
		this.privatekey = privatekey;
	}

	public String getPublickey() {
		return publickey;
	}

	public void setPublickey(String publickey) {
		this.publickey = publickey;
	}

	public String getAlipaypublickey() {
		return alipaypublickey;
	}

	public void setAlipaypublickey(String alipaypublickey) {
		this.alipaypublickey = alipaypublickey;
	}

	public String getNotifyurl() {
		return notifyurl;
	}

	public void setNotifyurl(String notifyurl) {
		this.notifyurl = notifyurl;
	}

	public String getHttpsverifyurl() {
		return httpsverifyurl;
	}

	public void setHttpsverifyurl(String httpsverifyurl) {
		this.httpsverifyurl = httpsverifyurl;
	}

	public static Integer getMaxqueryretry() {
		return maxqueryretry;
	}

	public static Long getQueryduration() {
		return queryduration;
	}

	public static Integer getMaxcancelretry() {
		return maxcancelretry;
	}

	public static Long getCancelduration() {
		return cancelduration;
	}

	public static String getTimeoutexpress() {
		return timeoutexpress;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}
	
	
}