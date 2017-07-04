package com.ifreework.controller.weixin;


import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriUtils;

import com.ifreework.common.controller.BaseControllerSupport;
import com.ifreework.common.entity.PageData;
import com.ifreework.common.manager.ServletRequestManager;
import com.ifreework.entity.system.User;
import com.ifreework.service.system.UserService;
import com.ifreework.service.weixin.WeixinService;
import com.ifreework.util.StringUtil;

/**
 * 
 * 描述：微信接口调用总接口    
 * @author：wangyh
 * @createDate：2017年6月22日
 * @modify：wangyh    
 * @modifyDate：2017年6月22日 
 * @version 1.0
 */
@Controller
public class WeixinController extends BaseControllerSupport {

	private static Logger logger = LoggerFactory.getLogger(WeixinController.class);

	@Autowired
	private WeixinService weixinService;

	@Autowired
	private UserService userService;

	/**
	 * 接口验证,总入口,用于微信公众号绑定时的验签
	 * @param out
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/weixin/index",method=RequestMethod.GET)
	@ResponseBody
	public void indexGet() {
		logger.debug("开始验签");
		PageData pd = this.getPageData();
		try {
			String signature = pd.getString("signature"); // 微信加密签名
			String timestamp = pd.getString("timestamp"); // 时间戳
			String nonce = pd.getString("nonce"); // 随机数

			String echostr = pd.getString("echostr"); // 字符串

			boolean sign = weixinService.sign(signature, timestamp, nonce);
			logger.debug("验签结束，验签结果：{}", sign);

			if (sign) {
				if (!StringUtil.isEmpty(echostr)) { // 验证服务器
					printHttpServletResponse(echostr); 
				}
			}
		} catch (Exception e) {
			logger.error("验签失败。{}", e);
		}
		printHttpServletResponse( "error");
	}
	
	
	/**
	 * 接口验证,总入口,用于处理微信推的信息
	 * @param out
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/weixin/index",method=RequestMethod.POST)
	@ResponseBody
	public void indexPost() {
		logger.debug("开始验签");
		PageData pd = this.getPageData();
		try {
			String signature = pd.getString("signature"); // 微信加密签名
			String timestamp = pd.getString("timestamp"); // 时间戳
			String nonce = pd.getString("nonce"); // 随机数

			boolean sign = weixinService.sign(signature, timestamp, nonce);
			logger.debug("验签结束，验签结果：{}", sign);

			if (sign) { //验签成功后执行该函数
				HttpServletRequest request = getRequest();
				HttpServletResponse response = getHttpServletResponse();
				weixinService.sendMsg(request, response);
			}
		} catch (Exception e) {
			logger.error("验签失败。{}", e);
		}
		printHttpServletResponse( "error");
	}
	
	/**
	 * 描述： 创建菜单
	 * @return
	 */
	@RequestMapping(value = "/weixin/createMenu")
	@ResponseBody
	public void createMenu(){
		weixinService.createMenu();
	}
	
	
	
	/**
	 * 描述：微信授权回调页面
	 * 通过回调参数code，调用微信接口，获取用户的相关数据
	 * 用户获取成功后，跳转到中间页面，保存openId
	 * @throws IOException 
	 */
	@RequestMapping(value = "/mobile/redirect")
	public ModelAndView redirect() throws IOException {
		ModelAndView mv = new ModelAndView();
		PageData pd = this.getPageData();
		String code = pd.getString("code");
		String f = pd.getString("f");
		
		User user = userService.getUserByCode(code);
		
//		User user = userService.getUserById(code);  //测试用例
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
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/mobile/changeToMine")
	public ModelAndView changeToMine() throws UnsupportedEncodingException {
		ModelAndView mv = new ModelAndView();
		String forward = this.getPageData().getString("f");
		forward = UriUtils.decode(forward, "UTF-8");
		String openId = ServletRequestManager.getCookieValue("openId");
		forward += "&m=" + openId;
		mv.addObject("openId", openId);
		mv.addObject("f", forward);
		mv.setViewName("/mobile/forward/forward");
		return mv;
	}
	
}
