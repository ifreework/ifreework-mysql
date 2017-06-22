package com.ifreework.entity.system;


import java.io.Serializable;



public class Dictionary implements Serializable{
	
	private static final long serialVersionUID = 2582625537628640155L;
	//columns START
	private java.lang.String dictionaryId;
	private java.lang.String dictionaryCode;
	private java.lang.String dictionaryName;
	private java.lang.String dictionaryTypeId;
	private java.lang.String parentId;
	private java.lang.String describe;
	private java.lang.String status;
	private java.lang.String isLeaf;
	private java.lang.Integer orderNum;
	
	private DictionaryType dictionaryType;
	
	//columns END

	public void setDictionaryId(java.lang.String value) {
		this.dictionaryId = value;
	}
	
	public java.lang.String getDictionaryId() {
		return this.dictionaryId;
	}
	
	
	
	public void setDictionaryCode(java.lang.String value) {
		this.dictionaryCode = value;
	}
	public java.lang.String getDictionaryCode() {
		return this.dictionaryCode;
	}	
	
	public void setDictionaryName(java.lang.String value) {
		this.dictionaryName = value;
	}
	public java.lang.String getDictionaryName() {
		return this.dictionaryName;
	}	
	
	public void setDictionaryTypeId(java.lang.String value) {
		this.dictionaryTypeId = value;
	}
	public java.lang.String getDictionaryTypeId() {
		return this.dictionaryTypeId;
	}	
	
	public void setParentId(java.lang.String value) {
		this.parentId = value;
	}
	public java.lang.String getParentId() {
		return this.parentId;
	}	
	
	public void setDescribe(java.lang.String value) {
		this.describe = value;
	}
	public java.lang.String getDescribe() {
		return this.describe;
	}	
	
	
	
	public void setStatus(java.lang.String value) {
		this.status = value;
	}
	public java.lang.String getStatus() {
		return this.status;
	}

	public DictionaryType getDictionaryType() {
		return dictionaryType;
	}

	public void setDictionaryType(DictionaryType dictionaryType) {
		this.dictionaryType = dictionaryType;
	}

	public java.lang.String getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(java.lang.String isLeaf) {
		this.isLeaf = isLeaf;
	}

	public java.lang.Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(java.lang.Integer orderNum) {
		this.orderNum = orderNum;
	}	
	
	
}


