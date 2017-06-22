package com.ifreework.common.manager;

import com.ifreework.entity.system.Config;

/**
 * 
 * 描述：    获取系统配置属性
 * @author：wangyh
 * @createDate：2017年5月17日
 * @modify：wangyh    
 * @modifyDate：2017年5月17日 
 * @version 1.0
 */
public class SysTemConfigManager {

	/**
	 * 
	 * 描述：获取系统配置属性
	 * @Title: get
	 * @param key 系统配置KEY
	 * @return    value
	 * @throws
	 */
	public static String get(String key){
		return Config.init().get(key);
	}
}
