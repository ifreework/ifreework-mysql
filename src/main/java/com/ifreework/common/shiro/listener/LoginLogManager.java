package com.ifreework.common.shiro.listener;

public interface LoginLogManager {

	/**
	 * 
	 * 描述：登出系统时调用
	 * @Title: logout
	 * @param username 退出的系统的用户
	 * @return   
	 * @throws
	 */
	public void logoutLog(String username);
}
