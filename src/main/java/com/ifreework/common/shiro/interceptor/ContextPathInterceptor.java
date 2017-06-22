package com.ifreework.common.shiro.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


/**
 * 
 * 描述：设置初始化参数    
 * @author：wangyh
 * @createDate：2017年4月30日
 * @modify：wangyh    
 * @modifyDate：2017年4月30日 
 * @version 1.0
 */
public class ContextPathInterceptor extends HandlerInterceptorAdapter{
	Logger logger = Logger.getLogger(ContextPathInterceptor.class);


	/**
	 * 
	 * 描述：在请求前设置初始化参数
	 * @Title: preHandle
	 * @param 
	 * @return   
	 * @throws
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		request.setAttribute("response", response);
		
		String contextPath = request.getContextPath();     //设置项目路径
		String cssPath = contextPath + "/assets/css";   //CSS路径
		String jsPath = contextPath + "/assets/js";     //JS路径
		String imagePath = contextPath + "/assets/img"; //图片路径
		
		request.setAttribute("contextPath", contextPath);
		request.setAttribute("cssPath", cssPath);
		request.setAttribute("jsPath", jsPath);
		request.setAttribute("imagePath", imagePath);
		return true;
	}
	
}
