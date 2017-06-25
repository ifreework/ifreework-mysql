package com.ifreework.entity.system;


import java.io.Serializable;

public class DictionaryType implements Serializable{
	
	
	/**    
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）    
	 *    
	 * @version 1.0    
	 */    
	
	private static final long serialVersionUID = 4224199770408086456L;
	//columns START
	private java.lang.String dictionaryTypeId;
	private java.lang.String dictionaryTypeName;
	private java.lang.String remark;
	//columns END

	public void setDictionaryTypeId(java.lang.String value) {
		this.dictionaryTypeId = value;
	}
	
	public java.lang.String getDictionaryTypeId() {
		return this.dictionaryTypeId;
	}
	
	
	
	public void setDictionaryTypeName(java.lang.String value) {
		this.dictionaryTypeName = value;
	}
	public java.lang.String getDictionaryTypeName() {
		return this.dictionaryTypeName;
	}

	public java.lang.String getRemark() {
		return remark;
	}

	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}	
	
}


