package com.ifreework.common.shiro.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ifreework.entity.system.RequestLog;

public class RequestLogHandlerInterceptor extends HandlerInterceptorAdapter {

	private RequestLogManager requestLogManager; // 获取请求所需权限接口

	

	public RequestLogManager getRequestLogManager() {
		return requestLogManager;
	}

	public void setRequestLogManager(RequestLogManager requestLogManager) {
		this.requestLogManager = requestLogManager;
	}


	/**
	 * 
	 * 描述：验证当前请求是否已经登录
	 * @Title: preHandle
	 * @param 
	 * @return   
	 * @throws
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String path = request.getServletPath();
		if (!path.equals("/")) {
			RequestLog requestLog = requestLogManager.getRequestLog(path);
			request.setAttribute("_request_log", requestLog);
		}
		return true;
	}
	
	@Override
	public void afterCompletion(
			HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		RequestLog requestLog = (RequestLog) request.getAttribute("_request_log");
		if(requestLog != null){
			Long time = new Date().getTime() - requestLog.getRequestTime().getTime();
			requestLog.setResponseTime(time);
			requestLogManager.saveRequestLog(requestLog);
		}
	}
}
