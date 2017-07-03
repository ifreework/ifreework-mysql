package com.ifreework.entity.weixin;

import java.io.Serializable;
import java.util.Date;

public class CompanyIntroduction implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6782138065704062055L;
	//columns START
	private java.lang.String introductionId;
	private java.lang.String companyId;
	private java.lang.String title;
	private java.lang.String image;
	private java.lang.String content;
	private java.lang.Integer pageView;
	private java.lang.String creater;
	private Date createTime;
	//columns END

	public void setIntroductionId(java.lang.String value) {
		this.introductionId = value;
	}
	public java.lang.String getIntroductionId() {
		return this.introductionId;
	}	
	public void setCompanyId(java.lang.String value) {
		this.companyId = value;
	}
	public java.lang.String getCompanyId() {
		return this.companyId;
	}	
	public void setTitle(java.lang.String value) {
		this.title = value;
	}
	public java.lang.String getTitle() {
		return this.title;
	}	
	public void setImage(java.lang.String value) {
		this.image = value;
	}
	public java.lang.String getImage() {
		return this.image;
	}	
	public void setContent(java.lang.String value) {
		this.content = value;
	}
	public java.lang.String getContent() {
		return this.content;
	}	
	public void setPageView(java.lang.Integer value) {
		this.pageView = value;
	}
	public java.lang.Integer getPageView() {
		return this.pageView;
	}
	public java.lang.String getCreater() {
		return creater;
	}
	public void setCreater(java.lang.String creater) {
		this.creater = creater;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}	
}


