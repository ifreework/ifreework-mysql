package com.ifreework.common.websocked.handler;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * 
 * 描述：    消息推送类，已经过时，现在使用@sendto注解
 * @author：wangyh qq735789026  
 * @创建时间：2016年8月11日 下午4:28:13    
 * @修改人：wangyh    
 * @修改时间：2016年8月11日 下午4:28:13    
 * @version 1.0
 */
public class SystemWebSocketHandler extends TextWebSocketHandler {
	
	static Logger logger = Logger.getLogger(SystemWebSocketHandler.class);
	//接收消息
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		super.handleTextMessage(session, message);
		TextMessage returnMessage = new TextMessage(message.getPayload() + " received at server");
		session.sendMessage(returnMessage);
	}
	@Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.debug("connect to the websocket success......");
        Map<String, Object> userName = session.getAttributes();
        System.out.println(userName);
    }
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		// TODO Auto-generated method stub
		super.afterConnectionClosed(session, status);
	}
}
