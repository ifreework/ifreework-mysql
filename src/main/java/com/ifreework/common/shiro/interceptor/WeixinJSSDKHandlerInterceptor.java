package com.ifreework.common.shiro.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.marker.weixin.MySecurity;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ifreework.common.entity.PageData;
import com.ifreework.common.manager.SystemConfigManager;
import com.ifreework.common.manager.WeixinManager;
import com.ifreework.entity.system.Config;
import com.ifreework.util.StringUtil;

/**
 * 描述：过滤使用JSSDK的路径，获取验签参数
 * @author：wangyh
 * @createDate：2017年6月29日
 * @modify：wangyh    
 * @modifyDate：2017年6月29日 
 * @version 1.0
 */
public class WeixinJSSDKHandlerInterceptor extends HandlerInterceptorAdapter {


	/**
	 * 描述：过滤使用JSSDK的路径，获取验签参数
	 * @Title: preHandle
	 * @param 
	 * @return   
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String jsapi_ticket = WeixinManager.getJsapiTicket();
		String nonce_str = StringUtil.uuid();
		String timestamp = Long.toString(System.currentTimeMillis() / 1000);

		String domain = SystemConfigManager.get(Config.SYSTEM_DOMAIN_NAME); // 域名
		String servletPath = request.getServletPath(); // 请求路径
		String queryString = request.getQueryString(); // 请求参数
		String url = domain + servletPath; // 原始请求路径
		if (!StringUtil.isEmpty(queryString)) {
			url += "?" + queryString;
		}

		String str = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonce_str + "&timestamp=" + timestamp + "&url="
				+ url;
		String signature = new MySecurity().encode(str, MySecurity.SHA_1);
		
		PageData pd = new PageData();
		
		pd.put("appId", SystemConfigManager.get(Config.WEIXIN_APPID));
		pd.put("url", url);
		pd.put("jsapi_ticket", jsapi_ticket);
		pd.put("nonceStr", nonce_str);
		pd.put("timestamp", timestamp);
		pd.put("signature", signature);
		request.setAttribute("jssdk", pd);
		return true;

	}
}
