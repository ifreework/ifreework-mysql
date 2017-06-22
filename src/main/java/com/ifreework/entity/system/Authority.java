package com.ifreework.entity.system;

import java.io.Serializable;

public class Authority implements Serializable{
	
	
	/**    
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）    
	 *    
	 * @version 1.0    
	 */    
	
	private static final long serialVersionUID = -8796013310099951509L;
	//columns START
	private java.lang.String pk;
	private java.lang.String resourceId;
	private java.lang.String operationId;
	private java.lang.String authorityName;
	private java.lang.String remarks;
	
	private Resource resource;
	private Operation operation;
	
	//columns END

	public void setPk(java.lang.String value) {
		this.pk = value;
	}
	
	public java.lang.String getPk() {
		return this.pk;
	}
	
	
	public void setResourceId(java.lang.String value) {
		this.resourceId = value;
	}
	public java.lang.String getResourceId() {
		return this.resourceId;
	}	
	
	public void setOperationId(java.lang.String value) {
		this.operationId = value;
	}
	public java.lang.String getOperationId() {
		return this.operationId;
	}	
	
	public void setAuthorityName(java.lang.String value) {
		this.authorityName = value;
	}
	public java.lang.String getAuthorityName() {
		return this.authorityName;
	}	
	
	public void setRemarks(java.lang.String value) {
		this.remarks = value;
	}
	public java.lang.String getRemarks() {
		return this.remarks;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}	
	
	
}


