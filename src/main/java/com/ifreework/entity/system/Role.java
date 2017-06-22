package com.ifreework.entity.system;

import java.io.Serializable;

public class Role implements Serializable{
	
	
	/**    
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）    
	 *    
	 * @version 1.0    
	 */    
	
	private static final long serialVersionUID = -8818762665564595472L;
	//columns START
	private java.lang.String roleId;
	private java.lang.String roleName;
	private java.lang.String parentId;
	private java.lang.String isLeaf;
	private java.lang.String remarks;
	//columns END

	public void setRoleId(java.lang.String value) {
		this.roleId = value;
	}
	
	public java.lang.String getRoleId() {
		return this.roleId;
	}
	
	
	
	public void setRoleName(java.lang.String value) {
		this.roleName = value;
	}
	public java.lang.String getRoleName() {
		return this.roleName;
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
	
	public void setRemarks(java.lang.String value) {
		this.remarks = value;
	}
	public java.lang.String getRemarks() {
		return this.remarks;
	}	
}


