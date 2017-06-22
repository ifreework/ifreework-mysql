/**
 * Copyright (c) 2005-2012 https://github.com/zhangkaitao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.ifreework.common.aop;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ifreework.common.constant.CacheConstant;
import com.ifreework.common.manager.UserManager;
import com.ifreework.entity.system.User;
import com.ifreework.entity.system.UserRole;

/**
 * 用户权限的切面
 * <p/>
 * 1、当调用如下方法时，加缓存
 * {@link com.ifreework.common.shiro.realm.ShiroAuthInterface#queryAuthorityByUserName}
 * {@link com.ifreework.common.shiro.realm.ShiroAuthInterface#getAuthorityByUrl}
 * <p/>
 * 2、授权（Auth）
 * 当增删改授权时，
 * 如果是用户相关的，只删用户的即可，
 * 其他的全部清理
 * <p/>
 * 3。1、资源（Resource）
 * 当修改资源时判断是否发生变化（如resourceIdentity，是否显示），如果变了清缓存
 * 当删除资源时，清缓存
 * 3.2、权限（Permission）
 * 当修改权限时判断是否发生变化（如permission，是否显示），如果变了清缓存
 * 当删除权限时，清缓存
 * <p/>
 * 4、角色（Role）
 * 当删除角色时，请缓存
 * 当修改角色show/role/resourcePermissions关系时，清缓存
 * <p/>
 * 
 * 5、用户
 * 修改时，如果组织机构/工作职务变了，仅需清自己的缓存
 * <p/>
 * 清理自己时也同时清理菜单的缓存
 * <p/>
 * TODO
 * 1、异步失效缓存
 * 2、预填充缓存（即把此刻失效的再通过异步任务查一次） 目前只查前100个吧
 * 3、加二级缓存 提高失效再查的效率
 * <p/>
 * 此方法的一个缺点就是 只要改了一个，所有缓存失效。。。。
 * TODO 思考更好的做法？
 * <p/>
 * 
 * @author：wangyh
 * @createDate：2017年5月2日
 * @modify：wangyh    
 * @modifyDate：2017年5月2日 
 * @version 1.0
 */
@Component
@Aspect
public class UserAuthCacheAspect {

	private static Logger logger = LoggerFactory.getLogger(UserAuthCacheAspect.class);
	
	@Autowired
	private CacheManager cacheManager;
	private String cacheName; // 设置缓存地址名称
	private String userPermissionsPrefix; // 用户拥有角色缓存key前缀
	private String resourcePermissionsPrefix;

	public UserAuthCacheAspect() {
		cacheName = CacheConstant.AUTH_CACHE_NAME.toString(); // 设置缓存地址名称
		userPermissionsPrefix = CacheConstant.USER_PERMISSIONS_PREFIX.toString(); // 用户拥有角色缓存key前缀
		resourcePermissionsPrefix = CacheConstant.RESOURCE_PERMISSIONS_PREFIX.toString();
	}

	private Cache<String, Object> getCache() {
		return cacheManager.getCache(cacheName);
	}

	public String getUserPermissionsPrefix() {
		return userPermissionsPrefix;
	}

	public void setUserPermissionsPrefix(String userPermissionsPrefix) {
		this.userPermissionsPrefix = userPermissionsPrefix;
	}

	public String getResourcePermissionsPrefix() {
		return resourcePermissionsPrefix;
	}

	public void setResourcePermissionsPrefix(String resourcePermissionsPrefix) {
		this.resourcePermissionsPrefix = resourcePermissionsPrefix;
	}

	private String getUserPermissionsKey(String username) {
		return userPermissionsPrefix + username;
	}

	private String getResourcePermissionsKey(String resourceId) {
		return resourcePermissionsPrefix + resourceId;
	}

	/**
	 * 
	 * 描述：切点，通过resourceId获取权限时
	 * @Title: authCacheAuthorityByResourcePointcut
	 * @param 
	 * @return   
	 * @throws
	 */
	@Pointcut(value = "execution(* com.ifreework.service.system.UserServiceImpl.queryAuthorityByResourceId(..))")
	private void authCacheAuthorityByResourcePointcut() {
	}

	/**
	 * 
	 * 描述：如果缓存中包涵该用户所拥有的权限，则从缓存中获取，
	 *       如果没有该权限，则调用原方法，并将查询结果保存在缓存中
	 * @Title: queryAuthorityByUserName
	 * @param 
	 * @return   
	 * @throws
	 */
	@Around(value = "authCacheAuthorityByUsernamePointcut()")
	public Object queryAuthorityByUserName(ProceedingJoinPoint joinPoint) throws Throwable {
		String username = (String) joinPoint.getArgs()[0];
		String key = getUserPermissionsKey(username);
		Object obj = getCache().get(key);
		if (obj != null) {
			logger.debug("cacheName:{},method:queryAuthorityByUserName, hit key:{},value:{}", cacheName, key, obj);
			return obj;
		}
		obj = joinPoint.proceed();
		getCache().put(key, obj);
		return obj;

	}

	/**
	 * 
	 * 描述：切点，通过用户名获取用户拥有的权限时
	 * @Title: authCacheAuthorityByUsernamePointcut
	 * @param 
	 * @return   
	 * @throws
	 */
	@Pointcut(value = "execution(* com.ifreework.service.system.UserServiceImpl.queryAuthorityByUserName(..))")
	private void authCacheAuthorityByUsernamePointcut() {
	}

	/**
	 * 
	 * 描述：如果缓存中包涵该资源所对应的资源，则从缓存中获取，
	 *       如果没有该权限，则调用原方法，并将查询结果保存在缓存中
	 * @Title: queryAuthorityByUserName
	 * @param 
	 * @return   
	 * @throws
	 */
	@Around(value = "authCacheAuthorityByUsernamePointcut()")
	public Object queryAuthorityByResourceId(ProceedingJoinPoint joinPoint) throws Throwable {
		String resourceId = (String) joinPoint.getArgs()[0];
		String key = getResourcePermissionsKey(resourceId);
		Object obj = getCache().get(key);
		if (obj != null) {
			logger.debug("cacheName:{},method:queryAuthorityByUserName, hit key:{},value:{}", cacheName, key, obj);
			return obj;
		}
		obj = joinPoint.proceed();
		getCache().put(key, obj);
		return obj;

	}

	/**
	 * 
	 * 描述：切点，当用户修改角色时，重置用户缓存
	 * @Title: authCacheAuthorityByUsernamePointcut
	 * @param 
	 * @return   
	 * @throws
	 */
	@Pointcut(value = "execution(*  com.ifreework.mapper.system.UserRoleMapper.add(..))"
			+ "|| execution(*  com.ifreework.mapper.system.UserRoleMapper.delete(..))")
	private void resetCacheAuthorityByUsernamePointcut() {
	}

	/**
	 * 
	 * 描述：修改用户角色前，先清除该用户对应的缓存
	 * @Title: queryAuthorityByUserName
	 * @param 
	 * @return   
	 * @throws
	 */
	@Before(value = "resetCacheAuthorityByUsernamePointcut()")
	public void resetCacheAuthorityByUsername(JoinPoint joinPoint) throws Throwable {
		UserRole ur = (UserRole) joinPoint.getArgs()[0];
		User user = UserManager.getUser(ur.getUserId());
		String key = getUserPermissionsKey(user.getUsername());
		Object obj = getCache().remove(key);
		logger.debug("cacheName:{},method:remove, hit key:{},value:{}", cacheName, key, obj);
	}

	/**
	 * 
	 * 描述：切点，清除全部缓存
	 * 该方法在执行以下操作时执行
	 * 1、com.ifreework.service.system.RoleAuthorityServiceImpl.save(PageData pd) 
	 * 2、com.ifreework.service.system.RoleServiceImpl.add(Role role) || update || delete
	 * 3、com.ifreework.service.system.ResourceServiceImpl.add(Resource resource, String addArray) || update || delete
	 * 4、com.ifreework.service.system.OperationServiceImpl.add(Operation operation) || update || delete
	 * @Title: authCacheAuthorityByUsernamePointcut
	 * @param 
	 * @return   
	 * @throws
	 */
	@Pointcut(value = "execution(*  com.ifreework.service.system.RoleAuthorityServiceImpl.save(..))" +

	"|| execution(*  com.ifreework.service.system.RoleServiceImpl.add(..))"
			+ "|| execution(*  com.ifreework.service.system.RoleServiceImpl.update(..))"
			+ "|| execution(*  com.ifreework.service.system.RoleServiceImpl.delete(..))" +

	"|| execution(*  com.ifreework.service.system.ResourceServiceImpl.add(..))"
			+ "|| execution(*  com.ifreework.service.system.ResourceServiceImpl.update(..))"
			+ "|| execution(*  com.ifreework.service.system.ResourceServiceImpl.delete(..))" +

	"|| execution(*  com.ifreework.service.system.OperationServiceImpl.add(..))"
			+ "|| execution(*  com.ifreework.service.system.OperationServiceImpl.update(..))"
			+ "|| execution(*  com.ifreework.service.system.OperationServiceImpl.delete(..))")
	private void resetCachePointcut() {
	}

	/**
	 * 
	 * 描述：清除全部缓存
	 * @Title: resetCache
	 * @param 
	 * @return   
	 * @throws
	 */
	@Before(value = "resetCachePointcut()")
	public void resetCache() throws Throwable {
		getCache().clear();
		logger.debug("cacheName:" + cacheName + " clear");
	}

	public CacheManager getCacheManager() {
		return cacheManager;
	}

	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	public String getCacheName() {
		return cacheName;
	}

	public void setCacheName(String cacheName) {
		this.cacheName = cacheName;
	}
}
