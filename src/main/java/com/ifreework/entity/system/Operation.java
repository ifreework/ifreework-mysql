package com.ifreework.entity.system;

import java.io.Serializable;

public class Operation implements Serializable{
	
	
	/**    
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）    
	 *    
	 * @version 1.0    
	 */    
	
	private static final long serialVersionUID = -633306191521779561L;
	//columns START
	private java.lang.String operationId;
	private java.lang.String pk;
	private java.lang.String operationName;
	private java.lang.String remarks;
	private java.lang.String operationType;
	private String operationLevel;
	//columns END

	public void setOperationId(java.lang.String value) {
		this.operationId = value;
	}
	
	public java.lang.String getOperationId() {
		return this.operationId;
	}
	
	
	
	public void setPk(java.lang.String value) {
		this.pk = value;
	}
	public java.lang.String getPk() {
		return this.pk;
	}	
	
	public void setOperationName(java.lang.String value) {
		this.operationName = value;
	}
	public java.lang.String getOperationName() {
		return this.operationName;
	}	
	
	public void setRemarks(java.lang.String value) {
		this.remarks = value;
	}
	public java.lang.String getRemarks() {
		return this.remarks;
	}	
	
	public void setOperationType(java.lang.String value) {
		this.operationType = value;
	}
	public java.lang.String getOperationType() {
		return this.operationType;
	}

	public String getOperationLevel() {
		return operationLevel;
	}

	public void setOperationLevel(String operationLevel) {
		this.operationLevel = operationLevel;
	}	
	
	
}


