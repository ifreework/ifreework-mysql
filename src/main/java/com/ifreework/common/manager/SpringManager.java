/**    
 * 文件名：SpringHelper.java    
 *    
 * 版本信息：    
 * 日期：2014-11-18    
 * Copyright  Corporation 2014     
 * 版权所有    
 *    
 */
package com.ifreework.common.manager;

import org.apache.shiro.cache.CacheManager;
import org.springframework.web.context.ContextLoader;


/**
 * 
 * 描述：    通过id获取spring application中的对象
 * @author：wangyh
 * @createDate：2017年5月5日
 * @modify：wangyh    
 * @modifyDate：2017年5月5日 
 * @version 1.0
 */
public class SpringManager {

	/**
	 * 
	 * 描述：根据beanName获取bean对象
	 * @Title: getBean
	 * @param 
	 * 			beanName 配置中的beanid
	 * @return   
	 * @throws
	 */
	public static Object getBean(String beanName) {
		return ContextLoader.getCurrentWebApplicationContext().getBean(beanName);
	}

	/**
	 * 
	 * 描述：根据beanname获取指定的class对象，此处如果对象类型与class不一致，会抛出class case异常
	 * @Title: getBean
	 * @param 
	 * @return   
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<?> clazz) {
		T t = null;
		t = (T) ContextLoader.getCurrentWebApplicationContext().getBean(clazz);
		return t;
	}
	
	/**
	 * 
	 * 描述：根据beanname获取指定的class对象，此处如果对象类型与class不一致，会抛出class case异常
	 * @Title: getBean
	 * @param 
	 * @return   
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String beanName, Class<?> clazz) {
		T t = null;
		t = (T) ContextLoader.getCurrentWebApplicationContext().getBean(beanName, clazz);
		return t;
	}
	
	
	/**
	 * 
	 * 描述：获取ehcahche缓存管理对象
	 * @Title: getEhCacheManager
	 * @param 
	 * @return   
	 * @throws
	 */
	public static CacheManager getCacheManager(){
		return getBean("cacheManager",CacheManager.class);
	}
}
