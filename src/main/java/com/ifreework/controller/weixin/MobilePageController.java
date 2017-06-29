package com.ifreework.controller.weixin;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriUtils;

import com.ifreework.common.controller.BaseControllerSupport;
import com.ifreework.common.entity.PageData;
import com.ifreework.common.manager.ServletRequestManager;
import com.ifreework.common.manager.UserManager;
import com.ifreework.common.manager.WeixinManager;
import com.ifreework.entity.system.Config;
import com.ifreework.entity.system.User;
import com.ifreework.service.system.UserService;
import com.ifreework.util.StringUtil;

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
	 * 描述：微信授权回调页面
	 * 通过回调参数code，调用微信接口，获取用户的相关数据
	 * 用户获取成功后，跳转到中间页面，保存openId
	 * @throws IOException 
	 */
	@RequestMapping(value = "/redirect")
	public ModelAndView redirect() throws IOException {
		ModelAndView mv = new ModelAndView();
		PageData pd = this.getPageData();
		String code = pd.getString("code");
		String f = pd.getString("f");
//		User user = userService.getUserByCode(code);
		
		User user = userService.getUserById(code);  //测试用例
		if (user != null) {  
			mv.addObject("openId", user.getUserId());
			mv.addObject("f", f);
		} else {  //如果当前用户为空，则说明业务出错，跳转到错误页面
			mv.addObject("f", "/mobile/error");
		}
		
		mv.setViewName("/mobile/forward/forward");
		return mv;
	}
	
	
	/**
	 * 描述：跳转到文章页面
	 */
	@RequestMapping(value = "/forward")
	public ModelAndView forward() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/mobile/forward/forward");
		return mv;
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
	 * 
	 * 描述：跳转到文章详情
	 */
	@RequestMapping(value = "/articleInfo")
	public ModelAndView articleInfo() {
		ModelAndView mv = new ModelAndView();
		PageData pd = this.getPageData();
		String userId = pd.getString("mark");
		User user = UserManager.getUser(userId);
		mv.addObject("user", user);
		mv.setViewName("/mobile/article/articleInfo");
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
	
	
	/**
	 * 描述：跳转到产品列表页面
	 */
	@RequestMapping(value = "/product")
	public ModelAndView product() {
		ModelAndView mv = new ModelAndView();
		PageData pd = this.getPageData();
		String userId = pd.getString("mark");
		User user = UserManager.getUser(userId);
		mv.addObject("user", user);
		mv.setViewName("/mobile/product/product");
		return mv;
	}
	
	/**
	 * 描述：跳转到产品列表页面
	 */
	@RequestMapping(value = "/productList")
	public ModelAndView productList() {
		ModelAndView mv = new ModelAndView();
		PageData pd = this.getPageData();
		String userId = pd.getString("mark");
		User user = UserManager.getUser(userId);
		mv.addObject("user", user);
		mv.setViewName("/mobile/product/productList");
		return mv;
	}
	
	/**
	 * 描述：跳转到添加产品页面
	 */
	@RequestMapping(value = "/productEdit")
	public ModelAndView productEdit() {
		ModelAndView mv = new ModelAndView();
		PageData pd = this.getPageData();
		String userId = pd.getString("mark");
		User user = UserManager.getUser(userId);
		mv.addObject("user", user);
		mv.setViewName("/mobile/product/productEdit");
		return mv;
	}
	
	/**
	 * 描述：跳转到添加产品页面
	 */
	@RequestMapping(value = "/productType")
	public ModelAndView productType() {
		ModelAndView mv = new ModelAndView();
		PageData pd = this.getPageData();
		String userId = pd.getString("mark");
		User user = UserManager.getUser(userId);
		mv.addObject("user", user);
		mv.setViewName("/mobile/product/productType");
		return mv;
	}
	
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		String s = UriUtils.decode("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxe4d5e6ad2c81acf2&redirect_uri=http%3A%2F%2Fwww.zxcjhb.cn%2Fwxcode%2Fgetcode.php%3Fscope%3Dsnsapi_base%26auk%3Dforward%26forward%3Dhttp%3A%2F%2Fwz.cjhb168.com%2Farticle%2Fmobile%2Fdo%2Fdetail%2Fid%2F62696.html%3Fu%3D87543c737cef1f15%26__is_from_weixin_lion__%3Dyes&response_type=code&scope=snsapi_base&state=1498702387&connect_redirect=1#wechat_redirect", "UTF-8");
		System.out.println(s);
	}
}
