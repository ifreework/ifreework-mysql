package com.ifreework.common.aop;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.ifreework.common.constant.CacheConstant;
import com.ifreework.common.shiro.realm.ShiroAuthInterface;
import com.ifreework.entity.system.User;

/**
 * 描述：  用户缓存界面
 * <p/>
 * 1、当调用如下方法时，加缓存
 * {@link com.ifreework.service.system.UserServiceImpl#login}
 * <p/>
 * 2、当调用如下方法时，更新缓存
 * {@link com.ifreework.service.system.UserServiceImpl#update}
 * 
 * @author：wangyh
 * @createDate：2017年5月2日
 * @modify：wangyh    
 * @modifyDate：2017年5月2日 
 * @version 1.0
 */
@Component
@Aspect
public class UserCacheAspect {

	@Autowired
	@Qualifier("userService")
	private ShiroAuthInterface shiroAuthInterface;// 与数据库同步接口
	
	@Autowired
	private CacheManager cacheManager;
	private String cacheName; // 设置缓存地址名称
	private String usernameKeyPrefix; // 用户名key前缀

	public UserCacheAspect() {
		cacheName = CacheConstant.USER_CACHE_NAME.toString(); // 设置缓存地址名称
		usernameKeyPrefix = CacheConstant.USERNAME_KEY_PREFIX.toString(); // 用户名key前缀
	}

	private Cache<String, User> getCache() {
		return cacheManager.getCache(cacheName);
	}

	/**
	 * 
	 * 描述：登录成功后，保存缓存信息，用户修改后，修改缓存信息
	 * 
	 * @Title: cachePutPointcut
	 * @param 
	 * @return   
	 * @throws
	 */
	@Pointcut(value = "execution(* com.ifreework.service.system.UserServiceImpl.update(..))")
	private void cachePutPointcutByArg() {
	}

	/**
	 * 
	 * 描述：登录成功后，保存缓存信息，用户修改后，修改缓存信息
	 * 
	 * @Title: cachePutPointcut
	 * @param 
	 * @return   
	 * @throws
	 */
	@Pointcut(value = "execution(* com.ifreework.service.system.UserServiceImpl.login(..))")
	private void cachePutPointcutByReturn() {
	}

	@AfterReturning(value = "cachePutPointcutByReturn()", returning = "user")
	public void cachePutByQuery(Object user) {
		put((User) user);
	}

	@After(value = "cachePutPointcutByArg()")
	public void cachePutByUpdate(JoinPoint point) {
		User user = (User) point.getArgs()[0];
		user = shiroAuthInterface.getUserById(user.getUserId());
		put((User) user);
	}

	private String usernameKey(String username) {
		return usernameKeyPrefix + username;
	}

	public void put(User user) {
		if (user == null) {
			return;
		}
		String userName = user.getUsername();
		getCache().put(usernameKey(userName), user);
	}

	public void rmove(String userName) {
		rmove(usernameKey(userName));
	}

	public void rmove(User user) {
		if (user == null) {
			return;
		}
		String userName = user.getUsername();
		rmove(userName);
	}

	public ShiroAuthInterface getShiroAuthInterface() {
		return shiroAuthInterface;
	}

	public void setShiroAuthInterface(ShiroAuthInterface shiroAuthInterface) {
		this.shiroAuthInterface = shiroAuthInterface;
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

	public String getUsernameKeyPrefix() {
		return usernameKeyPrefix;
	}

	public void setUsernameKeyPrefix(String usernameKeyPrefix) {
		this.usernameKeyPrefix = usernameKeyPrefix;
	}
}
