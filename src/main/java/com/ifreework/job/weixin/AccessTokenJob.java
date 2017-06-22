package com.ifreework.job.weixin;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.ifreework.common.constant.CacheConstant;
import com.ifreework.common.manager.SpringManager;
import com.ifreework.entity.system.Config;

public class AccessTokenJob implements Job{
	
	private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	
	
	/**
	 * 
	 * 描述：定时查询微信公众号Token
	 * @param arg0
	 * @throws JobExecutionException 
	 * @return
	 */
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		CacheManager cacheManager = SpringManager.getCacheManager();
		Cache<Object, Object> cache = cacheManager.getCache(CacheConstant.WEIXIN_CACHE_NAME.toString());
		
		String appid = Config.init().get(Config.WEIXIN_APPID);
		String appsecret =  Config.init().get(Config.WEIXIN_APPSECRET);
		
		String requestUrl = ACCESS_TOKEN_URL.replace("APPID", appid).replace("APPSECRET", appsecret);
		
		
	}
	
	private String getKey(String macAddress){
		return CacheConstant.WEIXIN_CACHE_PREFIX.toString() + macAddress;
	}
	
}
