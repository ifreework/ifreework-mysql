package com.ifreework.controller.weixin;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ifreework.common.controller.BaseControllerSupport;
import com.ifreework.common.entity.PageData;
import com.ifreework.common.manager.UserManager;
import com.ifreework.entity.system.User;

/**
 * 描述：微信接口调用总接口
 * 
 * @author：wangyh
 * @createDate：2017年6月22日 @modify：wangyh
 * @modifyDate：2017年6月22日 @version 1.0
 */
@Controller
public class HomePageController extends BaseControllerSupport {

	/**
	 * 
	 * 描述：跳转到微主页页面
	 */
	@RequestMapping(value = "/mobile/homePage")
	public ModelAndView homePage() {
		ModelAndView mv = new ModelAndView();
		PageData pd = this.getPageData();
		String userId = pd.getString("m");
		User user = UserManager.getUser(userId);
		mv.addObject("user", user);
		mv.setViewName("/mobile/homePage/homePage");
		return mv;
	}
	
	/**
	 * 描述：跳转到个人名片页面
	 */
	@RequestMapping(value = "/mobile/card")
	public ModelAndView card() {
		ModelAndView mv = new ModelAndView();
		PageData pd = this.getPageData();
		String userId = pd.getString("m");
		User user = UserManager.getUser(userId);
		mv.addObject("user", user);
		mv.setViewName("/mobile/homePage/card");
		return mv;
	}

}
