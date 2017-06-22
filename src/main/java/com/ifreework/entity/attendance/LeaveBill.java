package com.ifreework.entity.attendance;

import java.io.Serializable;
import java.util.List;

import com.ifreework.entity.system.Attachment;
import com.ifreework.entity.system.Dictionary;
import com.ifreework.entity.system.User;

public class LeaveBill implements Serializable{
	
	private static final long serialVersionUID = 8650610280214757927L;
	//columns START
	private java.lang.String leaveBillId;
	private java.lang.String userId;
	private java.util.Date startTime;
	private java.util.Date endTime;
	private java.lang.String leaveType;
	private java.lang.String leaveCause;
	private java.lang.String status;
	private java.lang.String attachmentId;
	private Double leaveDays;
	private String processId ;
	private java.util.Date createTime;
	
	private User user;
	private Dictionary dictionary;
	private List<Attachment> attachments;
	
	//columns END
	
	public java.lang.String getLeaveBillId() {
		return leaveBillId;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<Attachment> getAttachments() {
		return attachments;
	}
	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}
	public Double getLeaveDays() {
		return leaveDays;
	}
	public void setLeaveDays(Double leaveDays) {
		this.leaveDays = leaveDays;
	}
	public void setLeaveBillId(java.lang.String leaveBillId) {
		this.leaveBillId = leaveBillId;
	}
	public java.lang.String getUserId() {
		return userId;
	}
	public void setUserId(java.lang.String userId) {
		this.userId = userId;
	}
	public java.util.Date getStartTime() {
		return startTime;
	}
	public void setStartTime(java.util.Date startTime) {
		this.startTime = startTime;
	}
	public java.util.Date getEndTime() {
		return endTime;
	}
	public void setEndTime(java.util.Date endTime) {
		this.endTime = endTime;
	}
	public java.lang.String getLeaveType() {
		return leaveType;
	}
	public void setLeaveType(java.lang.String leaveType) {
		this.leaveType = leaveType;
	}
	public java.lang.String getLeaveCause() {
		return leaveCause;
	}
	public void setLeaveCause(java.lang.String leaveCause) {
		this.leaveCause = leaveCause;
	}
	public java.lang.String getStatus() {
		return status;
	}
	public void setStatus(java.lang.String status) {
		this.status = status;
	}
	public java.lang.String getAttachmentId() {
		return attachmentId;
	}
	public void setAttachmentId(java.lang.String attachmentId) {
		this.attachmentId = attachmentId;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public Dictionary getDictionary() {
		return dictionary;
	}

	public void setDictionary(Dictionary dictionary) {
		this.dictionary = dictionary;
	}
	
	
}


