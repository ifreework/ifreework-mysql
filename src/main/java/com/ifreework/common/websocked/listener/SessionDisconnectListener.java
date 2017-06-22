package com.ifreework.common.websocked.listener;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;


public class SessionDisconnectListener implements ApplicationListener<SessionDisconnectEvent> {
	Logger logger = Logger.getLogger(getClass());

	@Override
	public void onApplicationEvent(SessionDisconnectEvent event) {
		StompHeaderAccessor headers = StompHeaderAccessor.wrap(event.getMessage()); // 获取消息头
		String name = headers.getUser().getName(); // 获取账号名
//		Const.WEBSOCKET_USER_MAP.remove(name);
//		logger.debug(name + " websocket断开连接。");
//		Msg msg = new Msg();
//		msg.setFromUser(name);
//		msg.setHandleType("userOffline");
//		WebsocketHelp.send("/topic/greetings", msg);
		logger.debug(name + " websocket接口断开连接。");
	}
}
