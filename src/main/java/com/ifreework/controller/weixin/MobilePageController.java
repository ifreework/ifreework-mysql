package com.ifreework.controller.weixin;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ifreework.common.controller.BaseControllerSupport;
import com.ifreework.common.entity.PageData;
import com.ifreework.common.manager.ServletRequestManager;
import com.ifreework.common.manager.UserManager;
import com.ifreework.common.manager.WeixinManager;
import com.ifreework.entity.system.Config;
import com.ifreework.entity.system.User;
import com.ifreework.service.system.UserService;

/**
 * 描述：微信接口调用总接口    
 * @author：wangyh
 * @createDate：2017年6月22日
 * @modify：wangyh    
 * @modifyDate：2017年6月22日 
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/mobile")
public class MobilePageController extends BaseControllerSupport {

	@Autowired
	private UserService userService;

	/**
	 * 接口验证,总入口,用于微信公众号绑定时的验签
	 */
	@RequestMapping(value = "/index")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		PageData pd = this.getPageData();
		String code = pd.getString("code");
		User user = userService.getUserByCode(code);
		if (user != null) {
			mv.addObject("user", user);
		} else {
			mv.setViewName("/mobile/error/error");
		}
		return mv;
	}

	
	
	/**
	 * 描述：跳转到微信授权页面
	 * @throws IOException 
	 */
	@RequestMapping(value = "/authorization")
	public void authorization() throws IOException {
		HttpServletResponse response = ServletRequestManager.getHttpServletResponse();
		PageData pd = this.getPageData();
		String article = pd.getString("article");
		String domainName = Config.init().get(Config.SYSTEM_DOMAIN_NAME);
		String redirectUri = domainName + "/mobile/redirect";
		String authorization = WeixinManager.getAuthorizationUrl(redirectUri, "snsapi_userinfo", article);
		response.sendRedirect(authorization);
	}
	
	
	/**
	 * 描述：跳转到微信授权页面
	 * @throws IOException 
	 */
	@RequestMapping(value = "/redirect")
	public void redirect() throws IOException {
		HttpServletResponse response = ServletRequestManager.getHttpServletResponse();
		PageData pd = this.getPageData();
		String code = pd.getString("code");
		String article = pd.getString("state");
		User user = userService.getUserByCode(code);
		if (user != null) {
			String domainName = Config.init().get(Config.SYSTEM_DOMAIN_NAME);
			String url = domainName + "/mobile/page?article=" + article + "&mark=" + user.getUserId();
			response.sendRedirect(url);
		} else {
			response.sendRedirect("/mobile/error/error.jsp");
		}
	}
	
	
	/**
	 * 
	 * 描述：跳转到文章页面
	 */
	@RequestMapping(value = "/page")
	public ModelAndView page() {
		ModelAndView mv = new ModelAndView();
		PageData pd = this.getPageData();
		
		String article = pd.getString("article");
		String userId = pd.getString("mark");
		
		User user = userService.getUserById(userId);
		if (user != null) {
			mv.addObject("user", user);
		} else {
			mv.setViewName("/mobile/error/error");
		}
		return mv;
	}
	
	
	/**
	 * 
	 * 描述：跳转到微主页页面
	 */
	@RequestMapping(value = "/homePage")
	public ModelAndView homePage() {
		ModelAndView mv = new ModelAndView();
		PageData pd = this.getPageData();
		String userId = pd.getString("mark");
		User user = UserManager.getUser(userId);
		mv.addObject("user", user);
		mv.setViewName("/mobile/homePage/homePage");
		return mv;
	}
	
	
	/**
	 * 
	 * 描述：跳转到个人中心页面
	 */
	@RequestMapping(value = "/personal")
	public ModelAndView personal() {
		ModelAndView mv = new ModelAndView();
		PageData pd = this.getPageData();
		String userId = pd.getString("mark");
		User user = UserManager.getUser(userId);
		mv.addObject("user", user);
		mv.setViewName("/mobile/personal/personal");
		return mv;
	}
	
	/**
	 * 
	 * 描述：跳转到文章页面
	 */
	@RequestMapping(value = "/articleList")
	public ModelAndView articleList() {
		ModelAndView mv = new ModelAndView();
		PageData pd = this.getPageData();
		String userId = pd.getString("mark");
		User user = UserManager.getUser(userId);
		mv.addObject("user", user);
		mv.setViewName("/mobile/article/articleList");
		return mv;
	}
	
	/**
	 * 描述：跳转到注册页面
	 */
	@RequestMapping(value = "/register")
	public ModelAndView register() {
		ModelAndView mv = new ModelAndView();
		PageData pd = this.getPageData();
		String userId = pd.getString("mark");
		User user = UserManager.getUser(userId);
		mv.addObject("user", user);
		mv.setViewName("/mobile/personal/register");
		return mv;
	}
	
	/**
	 * 描述：跳转到公司介绍页面
	 */
	@RequestMapping(value = "/company")
	public ModelAndView company() {
		ModelAndView mv = new ModelAndView();
		PageData pd = this.getPageData();
		String userId = pd.getString("mark");
		User user = UserManager.getUser(userId);
		mv.addObject("user", user);
		mv.setViewName("/mobile/company/company");
		return mv;
	}

	
	/**
	 * 描述：跳转到公司介绍详情页面
	 */
	@RequestMapping(value = "/companyInfo")
	public ModelAndView companyInfo() {
		ModelAndView mv = new ModelAndView();
		PageData pd = this.getPageData();
		String userId = pd.getString("mark");
		User user = UserManager.getUser(userId);
		mv.addObject("user", user);
		mv.setViewName("/mobile/company/companyInfo");
		return mv;
	}
	
	/**
	 * 描述：跳转到个人名片页面
	 */
	@RequestMapping(value = "/card")
	public ModelAndView card() {
		ModelAndView mv = new ModelAndView();
		PageData pd = this.getPageData();
		String userId = pd.getString("mark");
		User user = UserManager.getUser(userId);
		mv.addObject("user", user);
		mv.setViewName("/mobile/homePage/card");
		return mv;
	}
}
