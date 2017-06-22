package com.ifreework.entity.system;

import java.io.Serializable;

public class UserRole implements Serializable{
	
	
	/**    
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）    
	 *    
	 * @version 1.0    
	 */    
	
	private static final long serialVersionUID = 2740348109895212361L;
	//columns START
	private java.lang.String userRoleId;
	private java.lang.String userId;
	private java.lang.String roleId;
	
	private Role role;
	//columns END

	public void setUserRoleId(java.lang.String value) {
		this.userRoleId = value;
	}
	
	public java.lang.String getUserRoleId() {
		return this.userRoleId;
	}
	
	
	
	public void setUserId(java.lang.String value) {
		this.userId = value;
	}
	public java.lang.String getUserId() {
		return this.userId;
	}	
	
	public void setRoleId(java.lang.String value) {
		this.roleId = value;
	}
	public java.lang.String getRoleId() {
		return this.roleId;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}	
}


