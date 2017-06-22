package com.ifreework.entity.system;

import java.io.Serializable;

public class Resource implements Serializable{
	
	
	/**    
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）    
	 *    
	 * @version 1.0    
	 */    
	
	private static final long serialVersionUID = 5832965730683282037L;
	//columns START
	private java.lang.String resourceId;
	private java.lang.String parentId;
	private Integer resourceOrder;
	private java.lang.String isLeaf;
	private java.lang.String resourceType;
	private java.lang.String resourceName;
	private java.lang.String resourceUrl;
	private java.lang.String resourceFlag;
	private java.lang.String pk;
	private java.lang.String remarks;
	private java.lang.String iconCls;
	//columns END

	public void setResourceId(java.lang.String value) {
		this.resourceId = value;
	}
	
	public java.lang.String getResourceId() {
		return this.resourceId;
	}
	
	
	
	public void setParentId(java.lang.String value) {
		this.parentId = value;
	}
	public java.lang.String getParentId() {
		return this.parentId;
	}	
	
	public void setResourceOrder(Integer value) {
		this.resourceOrder = value;
	}
	public Integer getResourceOrder() {
		return this.resourceOrder;
	}	
	
	public void setIsLeaf(java.lang.String value) {
		this.isLeaf = value;
	}
	public java.lang.String getIsLeaf() {
		return this.isLeaf;
	}	
	
	public void setResourceType(java.lang.String value) {
		this.resourceType = value;
	}
	public java.lang.String getResourceType() {
		return this.resourceType;
	}	
	
	public void setResourceName(java.lang.String value) {
		this.resourceName = value;
	}
	public java.lang.String getResourceName() {
		return this.resourceName;
	}	
	
	public void setResourceUrl(java.lang.String value) {
		this.resourceUrl = value;
	}
	public java.lang.String getResourceUrl() {
		return this.resourceUrl;
	}	
	
	public void setResourceFlag(java.lang.String value) {
		this.resourceFlag = value;
	}
	public java.lang.String getResourceFlag() {
		return this.resourceFlag;
	}	
	
	public void setPk(java.lang.String value) {
		this.pk = value;
	}
	public java.lang.String getPk() {
		return this.pk;
	}	
	
	public void setRemarks(java.lang.String value) {
		this.remarks = value;
	}
	public java.lang.String getRemarks() {
		return this.remarks;
	}

	public java.lang.String getIconCls() {
		return iconCls;
	}

	public void setIconCls(java.lang.String iconCls) {
		this.iconCls = iconCls;
	}	
	
	
}


