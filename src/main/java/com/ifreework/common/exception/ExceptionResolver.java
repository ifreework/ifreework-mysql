package com.ifreework.common.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.ifreework.common.constant.Constant;
import com.ifreework.common.entity.PageData;
import com.ifreework.common.manager.ServletRequestManager;


/**
 * 描述：异常处理工具类,用于捕获后台系统运行中产生的异常，并跳转到异常显示页面
 * 
 * @author：wangyh qq735789026
 * @创建时间：2016年7月5日 上午10:42:42
 * @修改人：wangyh
 * @修改时间：2016年7月5日 上午10:42:42
 * @version 1.0
 */
public class ExceptionResolver implements HandlerExceptionResolver {

	private String errorPath;
	
	private static Logger logger = LoggerFactory.getLogger(ExceptionResolver.class);
	
	
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		HandlerMethod  handlerMethod = (HandlerMethod)handler;

		writeErrorLog(ex, handlerMethod);
		
		String type = request.getParameter("_type");
		if("ajax".equals(type)){
			PageData pd = new PageData();
			pd.setResult(Constant.ERROR);
			String json = JSON.toJSONString(pd);
			ServletRequestManager.printHttpServletResponse(json);
			return null;
		}
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName(errorPath);
		return mv;
	}
	
	/**
	 * 
	 * 描述：记录错误日志信息
	 * @param ex
	 * @param handlerMethod 
	 * @return
	 */
	private void writeErrorLog(Exception ex, HandlerMethod handlerMethod) {
		logger.error("============== Exception Start =============");
		logger.error("Error method is {}",handlerMethod.getMethod());
		MethodParameter[]  params = handlerMethod.getMethodParameters();
		if(params != null && params.length > 0){
			for (MethodParameter methodParameter : params) {
				logger.error("Error method params {}",methodParameter);
			}
		}else{
			logger.error("Error method has not parameters");
		}
		
		logger.error("Error's details is {} " ,ex);
		logger.error("============== Exception End =============");
	}
	public String getErrorPath() {
		return errorPath;
	}
	public void setErrorPath(String errorPath) {
		this.errorPath = errorPath;
	}
	
	

}
