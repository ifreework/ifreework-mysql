package com.ifreework.entity.db;

import java.io.Serializable;

public class County implements Serializable {
	
	
	/**    
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）    
	 *    
	 * @version 1.0    
	 */    
	
	private static final long serialVersionUID = -5832064023445515174L;
	//columns START
	private Integer countyId;
	private String countyName;
	private String municipalityId;
	//columns END

	public void setCountyId(Integer value) {
		this.countyId = value;
	}
	
	public Integer getCountyId() {
		return this.countyId;
	}
	
	
	
	public void setCountyName(String value) {
		this.countyName = value;
	}
	public String getCountyName() {
		return this.countyName;
	}

	public String getMunicipalityId() {
		return municipalityId;
	}

	public void setMunicipalityId(String municipalityId) {
		this.municipalityId = municipalityId;
	}	
	
	
}


