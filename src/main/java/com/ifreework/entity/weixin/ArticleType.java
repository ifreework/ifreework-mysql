package com.ifreework.entity.weixin;

import java.io.Serializable;

public class ArticleType implements Serializable{
	private static final long serialVersionUID = -4672738495615815635L;
	//columns START
	private java.lang.String articleTypeId;
	private java.lang.String articleTypeName;
	//columns END
	public java.lang.String getArticleTypeId() {
		return articleTypeId;
	}
	public void setArticleTypeId(java.lang.String articleTypeId) {
		this.articleTypeId = articleTypeId;
	}
	public java.lang.String getArticleTypeName() {
		return articleTypeName;
	}
	public void setArticleTypeName(java.lang.String articleTypeName) {
		this.articleTypeName = articleTypeName;
	}
}


