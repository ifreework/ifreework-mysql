package com.ifreework.common.shiro.interceptor;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.UriUtils;

import com.ifreework.common.manager.SystemConfigManager;
import com.ifreework.common.manager.WeixinManager;
import com.ifreework.entity.system.Config;
import com.ifreework.util.StringUtil;

/**
 * 描述：验证来自微信的请求是否有openId
 * @author：wangyh
 * @createDate：2017年6月29日
 * @modify：wangyh    
 * @modifyDate：2017年6月29日 
 * @version 1.0
 */
public class WeixinOpenIdHandlerInterceptor extends HandlerInterceptorAdapter {
	private static Logger logger = LoggerFactory.getLogger(WeixinOpenIdHandlerInterceptor.class);

	private String oauth2RedirectUrl;  //微信授权回调页面，用于获取微信用户信息
	
	/**
	 * 
	 * 描述：验证当前请求是否有openId,如果不包涵该ID，则跳转到微信授权页面
	 * @Title: preHandle
	 * @param 
	 * @return   
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Cookie[] cookies = request.getCookies();
		String openId = null;

		// 判断cookie中是否存在openid 若存在则直接跳过，不存在则获取一次
		openId = getOpenIdFromCookie(cookies);

		if (StringUtil.isEmpty(openId)) { // 如果没有用户openID信息，则通过微信授权接口获取用户信息
			String domain = SystemConfigManager.get(Config.SYSTEM_DOMAIN_NAME); // 域名
			String servletPath = request.getServletPath(); // 请求路径
			String queryString = request.getQueryString(); // 请求参数
			String fUrl = domain + servletPath + "?" + queryString; // 原始请求路径
			if (!StringUtil.isEmpty(queryString)) {
				fUrl += "?" + queryString;
			}
			
			logger.info("原始请求路径：{}", fUrl);

			String redirectUrl = domain + oauth2RedirectUrl.replace("[FORWARD]", fUrl);

			logger.info("微信授权回调路径：{}", redirectUrl);

			redirectUrl = UriUtils.encode(redirectUrl, "UTF-8");

			String authorization = WeixinManager.getAuthorizationUrl(redirectUrl, "snsapi_userinfo", "");

			logger.info("微信授权路径：{}", authorization);

			response.sendRedirect(authorization);

			return false;
		} else {
			return true;
		}
	}

	/**
	 * 测试用例
	 */
	
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//			throws Exception {
//		Cookie[] cookies = request.getCookies();
//		String openId = null;
//
//		
//		// 判断cookie中是否存在openid 若存在则直接跳过，不存在则获取一次
//		openId = getOpenIdFromCookie(cookies);
//
//		if (StringUtil.isEmpty(openId)) {  //如果没有用户openID信息，则通过微信授权接口获取用户信息
//			String domain = SystemConfigManager.get(Config.SYSTEM_DOMAIN_NAME); //域名
//			String servletPath = request.getServletPath();  //请求路径
//			String queryString = request.getQueryString();  //请求参数
//			String fUrl = domain + servletPath ;  //原始请求路径
//			if(!StringUtil.isEmpty(queryString)){
//				fUrl += "?" + queryString;
//			}
//			logger.info("原始请求路径：{}" ,fUrl);
//			
//			fUrl = UriUtils.encode(fUrl, "UTF-8");
//			
//			String redirectUrl = domain + oauth2RedirectUrl.replace("[FORWARD]", fUrl) + "&code=1";
//			
//			logger.info("微信授权回调路径：{}" ,redirectUrl);
//			
//			response.sendRedirect(redirectUrl);
//			return false;
//		} else {
//			return true;
//		}
//	}
	/**
	 * 
	 * 描述：从cookies中获取openId的值
	 * @param cookies
	 * @return openId的值，如果不存在，返回null
	 */
	private String getOpenIdFromCookie(Cookie[] cookies) {
		String openId = null;
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("openId")) {
					openId = cookie.getValue();
				}
			}
		}
		return openId;
	}

	public String getOauth2RedirectUrl() {
		return oauth2RedirectUrl;
	}

	public void setOauth2RedirectUrl(String oauth2RedirectUrl) {
		this.oauth2RedirectUrl = oauth2RedirectUrl;
	}
}
