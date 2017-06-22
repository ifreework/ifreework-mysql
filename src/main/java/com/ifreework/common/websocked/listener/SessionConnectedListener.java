package com.ifreework.common.websocked.listener;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.socket.messaging.SessionConnectedEvent;


public class SessionConnectedListener implements ApplicationListener<SessionConnectedEvent> {
	Logger logger = Logger.getLogger(getClass());

	@Override
	public void onApplicationEvent(SessionConnectedEvent event) {
		StompHeaderAccessor headers = StompHeaderAccessor.wrap(event.getMessage()); // 获取消息头
		String name = headers.getUser().getName(); // 获取账号名
//		Msg msg = new Msg();
//		msg.setFromUser(name);
//		msg.setHandleType("userOnline");
//		WebsocketHelp.send("/topic/greetings", msg);
//		Const.WEBSOCKET_USER_MAP.put(name, headers.getUser());
		logger.debug(name + " 创建websocket接口成功过。");
	}
}
