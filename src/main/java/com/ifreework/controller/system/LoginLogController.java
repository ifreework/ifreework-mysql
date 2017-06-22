package com.ifreework.controller.system;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ifreework.common.controller.BaseControllerSupport;
import com.ifreework.common.entity.PageData;
import com.ifreework.service.system.LoginLogService;

/**
 * 
 * 描述：    
 * @author：wangyh qq735789026  
 * @createTime：2017年4月11日 下午4:16:41    
 * @editer：wangyh    
 * @editTime：2017年4月11日 下午4:16:41    
 * @version 1.0
 */
@Controller
@RequestMapping({ "/system/loginLog" })
public class LoginLogController extends BaseControllerSupport {

	@Autowired
	private LoginLogService loginLogService;

	
	
	/**
	 * 
	 * @Title: gotoView
	 * @Description: TODO(根据用户名查询用户信息，并跳转到修改界面)
	 * @param 
	 * @return   
	 * @throws
	 */
	@RequestMapping()
	public ModelAndView gotoView() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("/system/loginLog/list");
		return mv;
	}
	
	
	@RequestMapping(value = "/query")
	@ResponseBody
	public PageData query(){
		PageData pd = this.getPageData();
		pd = loginLogService.queryPageList(pd);
		return pd;
	}
}
