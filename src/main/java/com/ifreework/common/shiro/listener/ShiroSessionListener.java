package com.ifreework.common.shiro.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 描述：    session监听类，监听session创建、停止、过期
 * @author：wangyh
 * @createDate：2017年5月10日
 * @modify：wangyh    
 * @modifyDate：2017年5月10日 
 * @version 1.0
 */
public class ShiroSessionListener implements SessionListener  {

	private static Logger logger = LoggerFactory.getLogger(ShiroSessionListener.class);
	private LoginLogManager loginLogManager;
	
	public LoginLogManager getLoginLogManager() {
		return loginLogManager;
	}

	public void setLoginLogManager(LoginLogManager loginLogManager) {
		this.loginLogManager = loginLogManager;
	}

	@Override
	public void onStart(Session session) {
		logger.debug("session {} start!",session);
	}

	@Override
	public void onStop(Session session) {
		
		logger.debug("session {} stop!",session);
		loginLogManager.logoutLog(session.getId().toString());
	}

	@Override
	public void onExpiration(Session session) {
		logger.debug("session {} expiration!",session);
		loginLogManager.logoutLog(session.getId().toString());
	}

}
