package com.ifreework.entity.system;

import java.io.Serializable;

public class Department implements Serializable {
	
	
	/**    
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）    
	 *    
	 * @version 1.0    
	 */    
	
	private static final long serialVersionUID = 5876289493599066877L;
	//columns START
	private java.lang.String departmentId;
	private java.lang.String parentId;
	private java.lang.String isLeaf;
	private java.lang.Integer departmentOrder;
	private java.lang.String departmentNo;
	private java.lang.String departmentName;
	private java.lang.String tel;
	private java.lang.String address;
	private java.lang.String remarks;
	private java.lang.String status;
	//columns END

	public void setDepartmentId(java.lang.String value) {
		this.departmentId = value;
	}
	
	public java.lang.String getDepartmentId() {
		return this.departmentId;
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
	
	public void setDepartmentOrder(java.lang.Integer value) {
		this.departmentOrder = value;
	}
	public java.lang.Integer getDepartmentOrder() {
		return this.departmentOrder;
	}	
	
	public void setDepartmentNo(java.lang.String value) {
		this.departmentNo = value;
	}
	public java.lang.String getDepartmentNo() {
		return this.departmentNo;
	}	
	
	public void setDepartmentName(java.lang.String value) {
		this.departmentName = value;
	}
	public java.lang.String getDepartmentName() {
		return this.departmentName;
	}	
	
	public void setTel(java.lang.String value) {
		this.tel = value;
	}
	public java.lang.String getTel() {
		return this.tel;
	}	
	
	public void setAddress(java.lang.String value) {
		this.address = value;
	}
	public java.lang.String getAddress() {
		return this.address;
	}	
	
	public void setRemarks(java.lang.String value) {
		this.remarks = value;
	}
	public java.lang.String getRemarks() {
		return this.remarks;
	}

	public java.lang.String getStatus() {
		return status;
	}

	public void setStatus(java.lang.String status) {
		this.status = status;
	}	
	
}


