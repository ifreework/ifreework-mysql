package com.ifreework.common.websocked.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.ifreework.common.websocked.handler.SystemWebSocketHandler;
import com.ifreework.common.websocked.interceptor.HandshakeInterceptor;


//@Configuration  
//@EnableWebMvc
//@EnableWebSocket  
/**
 * 
 * 描述：   创建非注解模式的推送，已经过时，被WebSocketeBrokerConfig所替代
 * @author：wangyh qq735789026  
 * @创建时间：2016年8月11日 下午4:27:30    
 * @修改人：wangyh    
 * @修改时间：2016年8月11日 下午4:27:30    
 * @version 1.0
 */
public class WebSocketConfig implements WebSocketConfigurer {  
  
    @Override  
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {  
        registry.addHandler(myhandler(), "/websocket").addInterceptors(myInterceptors()).setAllowedOrigins("*");  
        registry.addHandler(myhandler(), "/sockjs/websocket").addInterceptors(myInterceptors()).withSockJS();  
    }  
  
    @Bean  
    public WebSocketHandler myhandler() {  
        return new SystemWebSocketHandler();  
    }  
  
    @Bean  
    public HandshakeInterceptor myInterceptors() {  
        return new HandshakeInterceptor();  
    }  
}  