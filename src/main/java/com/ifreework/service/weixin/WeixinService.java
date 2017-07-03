package com.ifreework.service.weixin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface WeixinService {

	/**
	 * 
	 * 描述：验签微信回写数据
	 * @param signature 微信加密签名
	 * @param timestamp 时间戳
	 * @param nonce 随机数
	 * @return  true:验签通过，false：验签未通过
	 */
	public boolean sign(String signature, String timestamp, String nonce);

	/**
	 * 处理微信服务器发过来的各种消息，包括：文本、图片、地理位置、音乐等等 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void sendMsg(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	/**
	 * 
	 * 描述： 微信菜单创建
	 * @return
	 */
	public void createMenu();
}