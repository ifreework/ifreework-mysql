package com.ifreework.common.shiro.session;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RedisSessionDAO extends AbstractSessionDAO {

	private static Logger logger = LoggerFactory.getLogger(RedisSessionDAO.class);
	
	private CacheManager cacheManager;
	
	private String shiroKey = "shiro_redis_cache"; //shiro缓存在数据库中保存的key
	
	private String keyPrefix = "shiro_redis_session:"; //shiro在redis中默认的缓存key
	
	private int expire = 1800000; // 超时时间，0永不超时,单位毫秒,默认30分钟
	
	@Override
	public void update(Session session) throws UnknownSessionException {
		this.saveSession(session);
	}
	
	/**
	 * 保存session到缓存中
	 * @param session
	 * @throws UnknownSessionException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void saveSession(Session session) throws UnknownSessionException{
		if(session == null || session.getId() == null){
			logger.error("session or session id is null");
			return;
		}
		Cache cache = cacheManager.getCache(shiroKey);
		session.setTimeout(expire);		
		cache.put(getKey(session.getId()), session);
	}

	/**
	 * 
	 * 描述：删除session，并从缓存中删除
	 * @param session 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void delete(Session session) {
		if(session == null || session.getId() == null){
			logger.error("session or session id is null");
			return;
		}
		@SuppressWarnings("rawtypes")
		Cache cache = cacheManager.getCache(shiroKey);
		cache.remove(getKey(session.getId()));
	}

	/**
	 * 
	 * 描述：获取全部活跃的session
	 * @return 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Collection<Session> getActiveSessions() {
		Set<Session> sessions = new HashSet<Session>();
		Cache cache = cacheManager.getCache(shiroKey);
		
		for (Object obj : cache.values()) {
			sessions.add((Session) obj);
		}
		return sessions;
	}

	/**
	 * 
	 * 描述：创建新的session对象
	 * @param session
	 * @return 
	 * @return
	 */
	@Override
	protected Serializable doCreate(Session session) {
		Serializable sessionId = this.generateSessionId(session);  
        this.assignSessionId(session, sessionId);
        session.setTimeout(expire);
        this.saveSession(session);
		return sessionId;
	}

	/**
	 * 
	 * 描述：获取session对象
	 * @param sessionId
	 * @return 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected Session doReadSession(Serializable sessionId) {
		if(sessionId == null){
			logger.error("session id is null");
			return null;
		}
		Cache cache = cacheManager.getCache(shiroKey);
		
		return (Session) cache.get(getKey(sessionId));
	}
	
	/**
	 * 获得byte[]型的key
	 * @param key
	 * @return
	 */
	private String getKey(Serializable sessionId){
		String preKey = this.keyPrefix + sessionId;
		return preKey;
	}

	
	public CacheManager getCacheManager() {
		return cacheManager;
	}

	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	public String getShiroKey() {
		return shiroKey;
	}

	public void setShiroKey(String shiroKey) {
		this.shiroKey = shiroKey;
	}

	public String getKeyPrefix() {
		return keyPrefix;
	}

	public void setKeyPrefix(String keyPrefix) {
		this.keyPrefix = keyPrefix;
	}

	public int getExpire() {
		return expire;
	}

	public void setExpire(int expire) {
		this.expire = expire;
	}
	
	
	
	
}
