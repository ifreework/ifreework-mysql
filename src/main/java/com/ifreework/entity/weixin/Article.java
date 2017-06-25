package com.ifreework.entity.weixin;

import java.io.Serializable;



public class Article implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5828552118005807776L;
	//columns START
	private java.lang.String articleId;
	private java.lang.String articleTypeId;
	private java.lang.String articleTypeName;
	
	private java.lang.String title;
	private java.lang.String image;
	private java.lang.String content;
	private java.lang.String creater;
	private java.util.Date createTime;
	private java.lang.Integer pageView;
	//columns END

	public void setArticleId(java.lang.String value) {
		this.articleId = value;
	}
	public java.lang.String getArticleId() {
		return this.articleId;
	}	
	public void setArticleTypeId(java.lang.String value) {
		this.articleTypeId = value;
	}
	public java.lang.String getArticleTypeId() {
		return this.articleTypeId;
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
	public void setPageView(java.lang.Integer value) {
		this.pageView = value;
	}
	public java.lang.Integer getPageView() {
		return this.pageView;
	}
	public java.lang.String getArticleTypeName() {
		return articleTypeName;
	}
	public void setArticleTypeName(java.lang.String articleTypeName) {
		this.articleTypeName = articleTypeName;
	}	
}


