package com.ifreework.common.websocked.interceptor;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import com.ifreework.common.manager.UserManager;
import com.ifreework.entity.system.User;

/**
 * 描述：websocked握手拦截器
 * @author：wangyh qq735789026  
 * @创建时间：2016年8月11日 下午1:46:44    
 * @修改人：wangyh    
 * @修改时间：2016年8月11日 下午1:46:44    
 * @version 1.0
 */
public class HandshakeInterceptor extends HttpSessionHandshakeInterceptor {

	private static Logger logger = Logger.getLogger(HandshakeInterceptor.class);

	/**
	 * @Title: beforeHandshake 
	 * @Description: TODO(在握手之前，验证用户是否已经登录，如果尚未登录，则返回false断开连接) 
	 * @param 
	 * @return 
	 * @throws
	 */
	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attributes) throws Exception {
		logger.debug("GOMA ===> Before Handshake");
		User user = UserManager.getUser();
		if (user != null) {
			return super.beforeHandshake(request, response, wsHandler, attributes);
		}
		return false;
	}

	/**
	 * @Title: beforeHandshake 
	 * @Description: TODO(在握手之后触发该方法) 
	 * @param 
	 * @return 
	 * @throws
	 */
	@Override
	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Exception ex) {
		logger.debug("GOMA ===> After Handshake");
		super.afterHandshake(request, response, wsHandler, ex);
	}
}