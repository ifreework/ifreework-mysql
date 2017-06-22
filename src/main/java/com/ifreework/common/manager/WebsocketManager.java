package com.ifreework.common.manager;

import java.util.List;

import org.springframework.messaging.simp.SimpMessagingTemplate;

/**
 * 
 * 描述：Websocket消息推送工具类    
 * @author：wangyh
 * @createDate：2017年6月7日
 * @modify：wangyh    
 * @modifyDate：2017年6月7日 
 * @version 1.0
 */
public class WebsocketManager {

	/**
	 * 获取spring中默认的brokerMessagingTemplate
	 * @param 
	 */
	public static SimpMessagingTemplate getSimpMessagingTemplate() {
		SimpMessagingTemplate t = SpringManager.getBean("brokerMessagingTemplate", SimpMessagingTemplate.class);
		return t;
	}

	/**
	 * 推送消息到指定地址
	 * @param url      消息推送地址
	 * @param msg      消息内容
	 */
	public static void send(String url, Object msg) {
		getSimpMessagingTemplate().convertAndSend(url, msg);
	}

	/**
	 * 推送消息到指定的用户集
	 * @param userList 用户名列表集
	 * @param url      消息推送地址
	 * @param msg      消息内容
	 */
	public static void send(List<String> userList, String url, Object msg) {
		if (userList != null) {
			for (String userName : userList) {
				getSimpMessagingTemplate().convertAndSendToUser(userName, url, msg);
			}
		}
	}

	/**
	 * 推送消息到指定的用户
	 * @param userName 用户名
	 * @param url      消息推送地址
	 * @param msg      消息内容
	 */
	public static void send(String userName, String url, Object msg) {
		getSimpMessagingTemplate().convertAndSendToUser(userName, url, msg);
	}
}
