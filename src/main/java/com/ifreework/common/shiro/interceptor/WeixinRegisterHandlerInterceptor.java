package com.ifreework.common.shiro.interceptor;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.UriUtils;

import com.ifreework.common.manager.SystemConfigManager;
import com.ifreework.common.shiro.realm.ShiroAuthInterface;
import com.ifreework.entity.system.Config;
import com.ifreework.entity.system.User;
import com.ifreework.util.StringUtil;

/**
 * 描述：验证当前请求用户是否已经注册，如果尚未注册，则跳转到注册页面
 * @author：wangyh
 * @createDate：2017年6月29日
 * @modify：wangyh    
 * @modifyDate：2017年6月29日 
 * @version 1.0
 */
public class WeixinRegisterHandlerInterceptor extends HandlerInterceptorAdapter {
	private static Logger logger = LoggerFactory.getLogger(WeixinRegisterHandlerInterceptor.class);

	private ShiroAuthInterface shiroAuth; // 获取请求所需权限接口
	
	private String registerUrl;  //微信授权回调页面，用于获取微信用户信息
	
	/**
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

		User user = shiroAuth.getUserById(openId);
		
		String weixin = user.getWeixin();  
		
		if (StringUtil.isEmpty(weixin)) {  //验证用户当前是否已经存在微信号，如果存在，则说明当前用户已经注册
			
			String domain = SystemConfigManager.get(Config.SYSTEM_DOMAIN_NAME); //域名
			String servletPath = request.getServletPath();  //请求路径
			String queryString = request.getQueryString();  //请求参数
			String fUrl = domain + servletPath + queryString;  //原始请求路径
			logger.info("原始请求路径：{}" ,fUrl);
			
			
			fUrl = UriUtils.encode(fUrl, "UTF-8");
			
			registerUrl = registerUrl.replace("[FORWARD]", fUrl);
			
			logger.info("微信注册页面路径：{}" ,registerUrl);
			
			response.sendRedirect(registerUrl);
			
			return false;
		} else {
			return true;
		}
	}

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

	public ShiroAuthInterface getShiroAuth() {
		return shiroAuth;
	}

	public void setShiroAuth(ShiroAuthInterface shiroAuth) {
		this.shiroAuth = shiroAuth;
	}

	public String getRegisterUrl() {
		return registerUrl;
	}

	public void setRegisterUrl(String registerUrl) {
		this.registerUrl = registerUrl;
	}
	
	

	
}
