package com.ifreework.entity.weixin;

import java.io.Serializable;

public class ProductType implements Serializable{
	
	
	/**    
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）    
	 *    
	 * @version 1.0    
	 */    
	
	private static final long serialVersionUID = -5361995046064778383L;
	//columns START
	private java.lang.String productTypeId;
	private java.lang.String parentId;
	private java.lang.String isLeaf;
	private java.lang.String productTypeName;
	private java.lang.String remark;
	private java.lang.String companyId;
	private java.lang.String status;
	//columns END

	public void setProductTypeId(java.lang.String value) {
		this.productTypeId = value;
	}
	
	public java.lang.String getProductTypeId() {
		return this.productTypeId;
	}
	
	
	
	public void setParentId(java.lang.String value) {
		this.parentId = value;
	}
	public java.lang.String getParentId() {
		return this.parentId;
	}	
	
	public void setIsLeaf(java.lang.String value) {
		this.isLeaf = value;
	}
	public java.lang.String getIsLeaf() {
		return this.isLeaf;
	}	
	
	public void setProductTypeName(java.lang.String value) {
		this.productTypeName = value;
	}
	public java.lang.String getProductTypeName() {
		return this.productTypeName;
	}	
	
	public void setRemark(java.lang.String value) {
		this.remark = value;
	}
	public java.lang.String getRemark() {
		return this.remark;
	}	
	
	public void setCompanyId(java.lang.String value) {
		this.companyId = value;
	}
	public java.lang.String getCompanyId() {
		return this.companyId;
	}	
	
	public void setStatus(java.lang.String value) {
		this.status = value;
	}
	public java.lang.String getStatus() {
		return this.status;
	}	
}


