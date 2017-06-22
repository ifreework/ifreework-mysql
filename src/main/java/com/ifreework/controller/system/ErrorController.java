/**    
 * 文件名：LoginController.java    
 *    
 * 版本信息：    
 * 日期：2014-11-19    
 * Copyright  Corporation 2014     
 * 版权所有    
 *    
 */
package com.ifreework.controller.system;





import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ifreework.common.controller.BaseControllerSupport;


/**
 * 
 * 类名称：LoginController 
 * 类描述： 
 * 创建人：王宜华 
 * 创建时间：2014-11-19 下午12:40:35 
 * 修改人：王宜华
 * 修改时间：2014-11-19 下午12:40:35 
 * 修改备注：
 * 
 * @version
 * 
 */
@Controller
@RequestMapping({ "/error" })
public class ErrorController extends BaseControllerSupport {


	/**
	 * 
	 * 描述：路径错误页面
	 * @return 
	 * @return
	 */
	@RequestMapping(value = "/404")
	public ModelAndView error404() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("/system/error/404");
		return mv;
	}
	
	

	/**
	 * 
	 * 描述：系统异常跳转界面
	 * @return
	 */
	@RequestMapping(value = "/500")
	public ModelAndView error500() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("/system/error/500");
		return mv;
	}
	
	

	/**
	 * 
	 * 描述：没有权限跳转界面
	 * @return 
	 * @return
	 */
	@RequestMapping(value = "/600")
	public ModelAndView error600() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("/system/error/unauthorized");
		return mv;
	}
	
	

	/**
	 * 
	 * 描述：用户登录超时登录界面
	 * @return 
	 * @return
	 */
	@RequestMapping(value = "/userIsNull")
	public ModelAndView userIsNull() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("/system/error/userIsNull");
		return mv;
	}
}
