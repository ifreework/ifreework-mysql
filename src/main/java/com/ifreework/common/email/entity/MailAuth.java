package com.ifreework.common.email.entity;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * 
 * 描述：保存用户认证信息 ，包涵用户名密码   
 * @author：wangyh qq735789026  
 * @createTime：2017年4月19日 下午2:29:08    
 * @editer：wangyh    
 * @editTime：2017年4月19日 下午2:29:08    
 * @version 1.0
 */
public class MailAuth extends Authenticator {
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 密码
	 */
	private String password;

	/**
	 * 用户认证类的初始化
	 * 
	 * @param user
	 *            用户名
	 * @param password
	 *            密码
	 */
	public MailAuth(String user, String password) {
		this.username = user;
		this.password = password;
	}

	/**
	 * 得到用户认证信息
	 */
	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(username, password);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
