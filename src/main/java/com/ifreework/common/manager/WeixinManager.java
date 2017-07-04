package com.ifreework.common.manager;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ifreework.common.constant.CacheConstant;
import com.ifreework.entity.system.Config;
import com.ifreework.util.HttpRequestUtil;
import com.ifreework.util.StringUtil;

public class WeixinManager {

	private static Logger logger = LoggerFactory.getLogger(WeixinManager.class);
	
	private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	private static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	private static final String AUTHORIZATION_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
	private static final String JSAPI_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
	
	
	private static final String TICKET_KEY = CacheConstant.WEIXIN_CACHE_PREFIX.toString() + "JSAPI_TICKET";
	private static final String TICKET_EXPIRES_KEY = CacheConstant.WEIXIN_CACHE_PREFIX.toString()
			+ "JSAPI_TICKET_EXPIRES";
	
	private static final String TOKEN_KEY = CacheConstant.WEIXIN_CACHE_PREFIX.toString() + "ACCESS_TOKEN";
	private static final String TOKEN_EXPIRES_KEY = CacheConstant.WEIXIN_CACHE_PREFIX.toString()
			+ "ACCESS_TOKEN_EXPIRES";

	private static final long EXPIRES_TIME = 1000 * 60 * 90; // token超时时间，90分钟

	/**
	 * 
	 * 描述：重新在微信中查询AccessToken，
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
		logger.info("Weixin AccessToken:{}",requestUrl);
		
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

		if (!StringUtil.isEmpty(resultStr)) {
			JSONObject json = JSON.parseObject(resultStr);
			return json;
		} else {
			JSONObject json = new JSONObject();
			json.put("errcode", "-1");
			json.put("errmsg", "未知异常");
			return json;
		}
	}
	
	/**
	 * 描述：根据传入参数，生成授权请求URL
	 * @param redirect_uri 授权后重定向的回调链接地址
	 * @param scope 应用授权作用域，snsapi_base （不弹出授权页面，直接跳转，只能获取用户openid），snsapi_userinfo （弹出授权页面，可通过openid拿到昵称、性别、所在地。并且，即使在未关注的情况下，只要用户授权，也能获取其信息）
	 * @param state 重定向后会带上state参数，开发者可以填写a-zA-Z0-9的参数值，最多128字节
	 * @return 微信授权地址
	 * @throws UnsupportedEncodingException 
	 */
	public static String getAuthorizationUrl(String redirect_uri,String scope,String state) throws UnsupportedEncodingException{
		redirect_uri = URLEncoder.encode(redirect_uri, "UTF-8");
		String appid = Config.init().get(Config.WEIXIN_APPID);
		String url = AUTHORIZATION_URL.replace("APPID", appid).replace("REDIRECT_URI", redirect_uri).replace("SCOPE", scope).replace("STATE", state);
		logger.info("Weixin AuthorizationUrl is {}" ,url);
		return url;
	}
	
	
	
	/**
	 * 描述：从微信端获取ticket，并保存在缓存中
	 * @return 
	 */
	private static String resetJsapiTicket(){
		CacheManager cacheManager = SpringManager.getCacheManager();
		Cache<Object, Object> cache = cacheManager.getCache(CacheConstant.WEIXIN_CACHE_NAME.toString());
		String ticket = null;
		
		String token = getAccessToken();

		String requestUrl = JSAPI_URL.replace("ACCESS_TOKEN", token);
		String resultStr = HttpRequestUtil.httpRequest(requestUrl);
		
		logger.info("Weixin JsapiTicket:{}",requestUrl);
		if (!StringUtil.isEmpty(resultStr)) {
			JSONObject josn = JSON.parseObject(resultStr);
			if (null != josn) {
				ticket = josn.getString("ticket");
				long expiresTime = new Date().getTime();
				
				cache.put(TICKET_KEY, ticket);
				cache.put(TICKET_EXPIRES_KEY, expiresTime);
			}
		}
		return ticket;
	} 
	
	/**
	 * 描述：从缓存中获取AccessToken
	 * @return 
	 */
	public static String getJsapiTicket() {
		CacheManager cacheManager = SpringManager.getCacheManager();
		Cache<Object, Object> cache = cacheManager.getCache(CacheConstant.WEIXIN_CACHE_NAME.toString());
		long nowTime = new Date().getTime();
		Long expiresTime = (Long) cache.get(TICKET_EXPIRES_KEY);
		if (expiresTime == null || nowTime - expiresTime > EXPIRES_TIME) {
			return resetJsapiTicket();
		}
		return (String) cache.get(TICKET_KEY);
	}

}
