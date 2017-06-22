package com.ifreework.common.entity;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;

/**
 * 
 * 描述：用于接收从前端传入的字段属性    
 * @author：wangyh qq735789026  
 * @createTime：2017年4月17日 下午5:23:05    
 * @editer：wangyh    
 * @editTime：2017年4月17日 下午5:23:05    
 * @version 1.0
 */
public class Column implements Serializable  {
	
	/**    
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）    
	 *    
	 * @version 1.0    
	 */    
	
	private static final long serialVersionUID = -5041978990883042405L;
	private String data; //字段名称
	private String name; //字段对应的数据库中的字段名
	private boolean orderable = false; //是否进行排序
	private String orderDir; //排序规则
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isOrderable() {
		return orderable;
	}
	public void setOrderable(boolean orderable) {
		this.orderable = orderable;
	}
	public String getOrderDir() {
		return orderDir;
	}
	public void setOrderDir(String orderDir) {
		this.orderDir = orderDir;
	}
	
	public String toString(){
		return JSON.toJSONString(this);
	}
}
