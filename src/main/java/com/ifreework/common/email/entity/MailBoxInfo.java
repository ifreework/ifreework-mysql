package com.ifreework.common.email.entity;

import java.util.List;



/**
 * 
 * 描述：用于保存未读邮件，已读邮件等相关信息    
 * @author：wangyh qq735789026  
 * @createTime：2017年4月19日 下午2:24:24    
 * @editer：wangyh    
 * @editTime：2017年4月19日 下午2:24:24    
 * @version 1.0
 */
public class MailBoxInfo {
	/**
	 * 新邮件数
	 */
	private int newMessageCount = 0;
	/**
	 * 邮件总数
	 */
	private int messageCount = 0;
	/**
	 * 未读邮件数
	 */
	private int unreadMessageCount = 0;

	/**
	 * 邮件列表信息
	 */
	private List<MailBean> mailBeanList;

	public int getNewMessageCount() {
		return newMessageCount;
	}

	public void setNewMessageCount(int newMessageCount) {
		this.newMessageCount = newMessageCount;
	}

	public int getMessageCount() {
		return messageCount;
	}

	public void setMessageCount(int messageCount) {
		this.messageCount = messageCount;
	}

	public int getUnreadMessageCount() {
		return unreadMessageCount;
	}

	public void setUnreadMessageCount(int unreadMessageCount) {
		this.unreadMessageCount = unreadMessageCount;
	}

	public List<MailBean> getMailBeanList() {
		return mailBeanList;
	}

	public void setMailBeanList(List<MailBean> mailBeanList) {
		this.mailBeanList = mailBeanList;
	}

}
