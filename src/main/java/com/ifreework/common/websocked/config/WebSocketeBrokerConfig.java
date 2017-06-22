package com.ifreework.common.websocked.config;


import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

import com.ifreework.common.websocked.interceptor.HandshakeInterceptor;
import com.ifreework.common.websocked.listener.SessionConnectedListener;
import com.ifreework.common.websocked.listener.SessionDisconnectListener;

@Configuration
@EnableWebMvc
@EnableWebSocketMessageBroker
public class WebSocketeBrokerConfig extends AbstractWebSocketMessageBrokerConfigurer  {
	
	
	Logger logger = Logger.getLogger(getClass());
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/websocket").addInterceptors(myInterceptors()).setAllowedOrigins("*").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.setApplicationDestinationPrefixes("/app");
        config.enableSimpleBroker("/topic", "/queue");
    }
    
    @Bean  
    public HandshakeInterceptor myInterceptors() {  
        return new HandshakeInterceptor();  
    }  
    
    @Bean
    public SessionConnectedListener sessionConnectedListener(){
        return new SessionConnectedListener();
    }
    
    @Bean
    public SessionDisconnectListener sessionDisconnectListener(){
        return new SessionDisconnectListener();
    }

}