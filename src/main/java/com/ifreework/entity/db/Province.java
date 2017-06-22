package com.ifreework.entity.db;

import java.io.Serializable;

/**
 * 
 * 描述：全国省份
 * @author：wangyh qq735789026  
 * @createTime：2017年4月11日 下午11:15:37    
 * @editer：wangyh    
 * @editTime：2017年4月11日 下午11:15:37    
 * @version 1.0
 */
public class Province implements Serializable{
	
	/**    
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）    
	 *    
	 * @version 1.0    
	 */    
	
	private static final long serialVersionUID = -3389701940492562513L;
	private String provinceId;//省份编码
	private String provinceName;//省份名称
	
	public String getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	
	
}
