package com.ifreework.entity.weixin;


import java.io.Serializable;
import java.util.Date;



public class LeaveMessage implements Serializable{
	
	
	/**    
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）    
	 *    
	 * @version 1.0    
	 */    
	
	private static final long serialVersionUID = 5289771494159232029L;
	//columns START
	private java.lang.String leaveMessageId;
	private java.lang.String userId;
	private java.lang.String title;
	private java.lang.String content;
	private java.lang.String leaveUser;
	private java.lang.String leavePhone;
	private Date createTime;
	//columns END

	public void setLeaveMessageId(java.lang.String value) {
		this.leaveMessageId = value;
	}
	
	public java.lang.String getLeaveMessageId() {
		return this.leaveMessageId;
	}
	
	
	
	public void setUserId(java.lang.String value) {
		this.userId = value;
	}
	public java.lang.String getUserId() {
		return this.userId;
	}	
	
	public void setTitle(java.lang.String value) {
		this.title = value;
	}
	public java.lang.String getTitle() {
		return this.title;
	}	
	
	public void setContent(java.lang.String value) {
		this.content = value;
	}
	public java.lang.String getContent() {
		return this.content;
	}	
	
	public void setLeaveUser(java.lang.String value) {
		this.leaveUser = value;
	}
	public java.lang.String getLeaveUser() {
		return this.leaveUser;
	}	
	
	public void setLeavePhone(java.lang.String value) {
		this.leavePhone = value;
	}
	public java.lang.String getLeavePhone() {
		return this.leavePhone;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}	
}


