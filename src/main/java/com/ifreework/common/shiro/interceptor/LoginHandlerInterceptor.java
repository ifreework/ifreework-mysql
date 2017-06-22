package com.ifreework.common.shiro.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;
import com.ifreework.common.constant.Constant;
import com.ifreework.common.entity.PageData;
import com.ifreework.common.manager.ServletRequestManager;
import com.ifreework.util.StringUtil;



/**
 * 
 * 描述：验证用户是否登录    
 * @author：wangyh
 * @createDate：2017年4月30日
 * @modify：wangyh    
 * @modifyDate：2017年4月30日 
 * @version 1.0
 */
public class LoginHandlerInterceptor extends HandlerInterceptorAdapter{
	Logger logger = Logger.getLogger(LoginHandlerInterceptor.class);
	
	private String loginPath; //登陆地址
	private String successPath; //登录成功后的验证地址

	
	public String getLoginPath() {
		return loginPath;
	}


	public void setLoginPath(String loginPath) {
		this.loginPath = loginPath;
	}


	public String getSuccessPath() {
		return successPath;
	}


	public void setSuccessPath(String successPath) {
		this.successPath = successPath;
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
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String path = request.getServletPath();
		logger.debug("The request path is :" + path);
		
		if("/".equals(path) || "/index.htm".equals(path)){
			return true;
		}
		
		String username = (String) SecurityUtils.getSubject().getPrincipal();
		
		if(StringUtil.isEmpty(username)){ //用户不存在，则跳转到login页面
			String type = request.getParameter("_type");
			if("ajax".equals(type)){
				PageData pd = new PageData();
				pd.setResult(Constant.ERROR);
				pd.put("errorType", "userIsNull");
				String json = JSON.toJSONString(pd);
				ServletRequestManager.printHttpServletResponse(json);
			}else if("windowOpen".equals(type)){
				request.getRequestDispatcher("/error/userIsNull").forward(request,response);
			}else{
				request.setAttribute("errorType", "userIsNull");
				request.getRequestDispatcher(loginPath).forward(request,response);
			}
			return false;		
		}
		
		if("/".equals(path)){
			request.getRequestDispatcher(successPath).forward(request,response);
			return false;		
		}
		return true;
	}
	
}
