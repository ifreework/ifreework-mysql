package com.ifreework.common.manager;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

import com.ifreework.common.constant.CacheConstant;
import com.ifreework.entity.system.User;
import com.ifreework.mapper.system.UserMapper;
import com.ifreework.util.StringUtil;

/**
 * 
 * 描述：登录用户管理类    
 * @author：wangyh
 * @createDate：2017年5月2日
 * @modify：wangyh    
 * @modifyDate：2017年5月2日 
 * @version 1.0
 */
public class UserManager {
	
	private static Logger log = Logger.getLogger(UserManager.class);
	
	/**
	 * 
	 * 描述：获取当前登录用户
	 * @Title: getUser
	 * @param 
	 * @return   User
	 * @throws
	 */
	public static User getUser(){
		String username = getUsername();
		if(StringUtil.isEmpty(username)){
			log.info("The login username is null");
			return null;
		}
		
		CacheManager cacheManager = SpringManager.getCacheManager();
		Cache<String,User> cache = cacheManager.getCache(CacheConstant.USER_CACHE_NAME.toString());
		return cache.get(CacheConstant.USERNAME_KEY_PREFIX + username);
	}
	

	/**
	 * 
	 * 描述：获取当前用户名
	 * @Title: getUsername
	 * @param 
	 * @return   
	 * @throws
	 */
	public static String getUsername(){
		return  (String) SecurityUtils.getSubject().getPrincipal();
	}
	
	
	/**
	 * 
	 * 描述：根据用户id，从数据库中检索用户信息
	 * @Title: getUser
	 * @param 
	 * @return   
	 * @throws
	 */
	public static User getUser(String userId){
		UserMapper userMapper = SpringManager.getBean("userMapper",UserMapper.class);
		return userMapper.getUserById(userId);
	}
	
	
}
