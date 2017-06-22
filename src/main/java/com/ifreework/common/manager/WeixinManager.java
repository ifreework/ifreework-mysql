package com.ifreework.common.manager;

import java.util.Date;
import java.util.Map;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ifreework.common.constant.CacheConstant;
import com.ifreework.entity.system.Config;
import com.ifreework.util.HttpRequestUtil;
import com.ifreework.util.StringUtil;

public class WeixinManager {

	private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	public static String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

	private static final String TOKEN_KEY = CacheConstant.WEIXIN_CACHE_PREFIX.toString() + "ACCESS_TOKEN";
	private static final String TOKEN_EXPIRES_KEY = CacheConstant.WEIXIN_CACHE_PREFIX.toString()
			+ "ACCESS_TOKEN_EXPIRES";

	private static final long EXPIRES_TIME = 1000 * 60 * 90; // token超时时间，90分钟

	/**
	 * 
	 * 描述：重新在微信中查询数据，
	 * @return access_token
	 */
	private static String resetAccessToken() {
		CacheManager cacheManager = SpringManager.getCacheManager();
		Cache<Object, Object> cache = cacheManager.getCache(CacheConstant.WEIXIN_CACHE_NAME.toString());
		String token = null;
		String appid = Config.init().get(Config.WEIXIN_APPID);
		String appsecret = Config.init().get(Config.WEIXIN_APPSECRET);

		String requestUrl = ACCESS_TOKEN_URL.replace("APPID", appid).replace("APPSECRET", appsecret);
		String resultStr = HttpRequestUtil.httpsRequest(requestUrl, "GET", null);
		if (!StringUtil.isEmpty(resultStr)) {
			JSONObject josn = JSON.parseObject(resultStr);
			if (null != josn) {
				token = josn.getString("access_token");
				long expiresTime = new Date().getTime();
				cache.put(TOKEN_KEY, token);
				cache.put(TOKEN_EXPIRES_KEY, expiresTime);
			}
		}
		return token;
	}

	/**
	 * 描述：从缓存中获取AccessToken
	 * @return 
	 */
	public static String getAccessToken() {
		CacheManager cacheManager = SpringManager.getCacheManager();
		Cache<Object, Object> cache = cacheManager.getCache(CacheConstant.WEIXIN_CACHE_NAME.toString());
		long nowTime = new Date().getTime();
		Long expiresTime = (Long) cache.get(TOKEN_EXPIRES_KEY);
		if (expiresTime == null || nowTime - expiresTime > EXPIRES_TIME) {
			return resetAccessToken();
		}
		return (String) cache.get(TOKEN_KEY);
	}

	/**
	 * 描述：创建微信菜单
	 * @param paramStr json格式微信菜单
	 * @return 
	 */
	public static Map<String, Object> createMenu(String paramStr) {
		String url = null;
		// 拼装创建菜单的url
		String accessToken = getAccessToken();
		url = CREATE_MENU_URL.replace("ACCESS_TOKEN", accessToken);

		// 调用接口创建菜单
		String resultStr = HttpRequestUtil.httpsRequest(url, "POST", paramStr);

		if (StringUtil.isEmpty(resultStr)) {
			JSONObject json = JSON.parseObject(resultStr);
			return json;
		} else {
			JSONObject json = new JSONObject();
			json.put("errcode", "-1");
			json.put("errmsg", "未知异常");
			return json;
		}
	}
}
