package com.ifreework.entity.system;

import java.io.Serializable;

public class RoleAuthority implements Serializable{
	
	
	/**    
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）    
	 *    
	 * @version 1.0    
	 */    
	
	private static final long serialVersionUID = 4310607330566605650L;
	//columns START
	private java.lang.String roleId;
	private java.lang.String authorityPk;
	private java.lang.String roleAuthorityId;
	
	private Resource resource;
	private Authority authority;
	
	//columns END
	
	public void setRoleId(java.lang.String value) {
		this.roleId = value;
	}
	public java.lang.String getRoleId() {
		return this.roleId;
	}	
	
	public void setAuthorityPk(java.lang.String value) {
		this.authorityPk = value;
	}
	public java.lang.String getAuthorityPk() {
		return this.authorityPk;
	}

	public java.lang.String getRoleAuthorityId() {
		return roleAuthorityId;
	}

	public void setRoleAuthorityId(java.lang.String roleAuthorityId) {
		this.roleAuthorityId = roleAuthorityId;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public Authority getAuthority() {
		return authority;
	}

	public void setAuthority(Authority authority) {
		this.authority = authority;
	}	
	
	
}


