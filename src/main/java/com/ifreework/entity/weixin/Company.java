package com.ifreework.entity.weixin;

import java.io.Serializable;

public class Company implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 409016700042043144L;
	//columns START
	private java.lang.String companyId;
	private java.lang.String companyName;
	private java.lang.String contactUser;
	private java.lang.String phone;
	private java.lang.String address;
	private java.lang.String logo;
	private java.lang.String creater;
	private java.util.Date createTime;
	//columns END

	public void setCompanyId(java.lang.String value) {
		this.companyId = value;
	}
	public java.lang.String getCompanyId() {
		return this.companyId;
	}	
	public void setCompanyName(java.lang.String value) {
		this.companyName = value;
	}
	public java.lang.String getCompanyName() {
		return this.companyName;
	}	
	public void setContactUser(java.lang.String value) {
		this.contactUser = value;
	}
	public java.lang.String getContactUser() {
		return this.contactUser;
	}	
	public void setAddress(java.lang.String value) {
		this.address = value;
	}
	public java.lang.String getAddress() {
		return this.address;
	}	
	public void setLogo(java.lang.String value) {
		this.logo = value;
	}
	public java.lang.String getLogo() {
		return this.logo;
	}	
	public void setCreater(java.lang.String value) {
		this.creater = value;
	}
	public java.lang.String getCreater() {
		return this.creater;
	}	
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	public java.lang.String getPhone() {
		return phone;
	}
	public void setPhone(java.lang.String phone) {
		this.phone = phone;
	}	
}


