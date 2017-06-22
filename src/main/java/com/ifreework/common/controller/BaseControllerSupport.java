package com.ifreework.common.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.ifreework.common.entity.PageData;
import com.ifreework.common.manager.ServletRequestManager;


/**
 * 
 * 描述：    Controller基本支持类
 * @author：wangyh qq735789026  
 * @创建时间：2016年7月5日 上午11:04:14    
 * @修改人：wangyh    
 * @修改时间：2016年7月5日 上午11:04:14    
 * @version 1.0
 */
public class BaseControllerSupport {
	
	protected Logger logger = Logger.getLogger(this.getClass());

	/** new PageData对象
	 * @return
	 */
	public PageData getPageData(){
		return new PageData(this.getRequest());
	}
	
	/**得到ModelAndView
	 * @return
	 */
	public ModelAndView getModelAndView(){
		return new ModelAndView();
	}
	
	/**得到request对象
	 * @return
	 */
	public HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}
	
	/**
	 * 获取HttpServletResponse
	 * 
	 * @author 王宜华
	 * @return
	 */
	public HttpServletResponse getHttpServletResponse() {
		return ServletRequestManager.getHttpServletResponse();
	}

	
	/**
	 * 
	 * @Title: printHttpServletResponse
	 * @Description: TODO(将后台数据写入到前端中去)
	 * @param 
	 * @return   
	 * @throws
	 */
	public void printHttpServletResponse(String html) {
		ServletRequestManager.printHttpServletResponse(html);
	}
	
	
	
	public static void logBefore(Logger logger, String interfaceName){
		logger.info("");
		logger.info("start");
		logger.info(interfaceName);
	}
	
	public static void logAfter(Logger logger){
		logger.info("end");
		logger.info("");
	}
	
}
