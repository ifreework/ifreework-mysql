package com.ifreework.controller.weixin;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ifreework.common.controller.BaseControllerSupport;
import com.ifreework.common.email.MailSend;
import com.ifreework.common.email.entity.MailBean;
import com.ifreework.common.entity.PageData;
import com.ifreework.common.manager.ServletRequestManager;
import com.ifreework.common.manager.UserManager;
import com.ifreework.entity.system.User;
import com.ifreework.entity.weixin.Company;
import com.ifreework.service.system.UserService;
import com.ifreework.service.weixin.CompanyService;
import com.ifreework.util.NumberUtil;
import com.ifreework.util.StringUtil;

/**
 * 描述：微信接口调用总接口
 * 
 * @author：wangyh
 * @createDate：2017年6月22日 @modify：wangyh
 * @modifyDate：2017年6月22日 @version 1.0
 */
@Controller
public class PersonalController extends BaseControllerSupport {

	@Autowired
	private CompanyService companyService;

	@Autowired
	private UserService userService;

	/**
	 * 描述：跳转到注册页面
	 */
	@RequestMapping(value = "/mobile/register")
	public ModelAndView register() {
		ModelAndView mv = new ModelAndView();
		PageData pd = this.getPageData();
		String f = pd.getString("f");
		String openId = ServletRequestManager.getCookieValue("openId");
		User user = userService.getUserById(openId);
		if(StringUtil.isEmpty(user.getWeixin())){ //如果用户已经注册，则跳转到
			List<Company> companys = companyService.queryList(new PageData());
			mv.addObject("companys", companys);
		}else{
			mv.addObject("user", user);
		}
		mv.addObject("forward", f);
		mv.setViewName("/mobile/personal/register");
		return mv;
	}
	
	/**
	 * 
	 * 描述：跳转到个人中心页面
	 */
	@RequestMapping(value = "/mobile/personal")
	public ModelAndView personal() {
		ModelAndView mv = new ModelAndView();
		PageData pd = this.getPageData();
		String userId = pd.getString("m");
		User user = UserManager.getUser(userId);
		mv.addObject("user", user);
		mv.setViewName("/mobile/personal/personal");
		return mv;
	}
	
	/**
	 * 
	 * 描述：跳转到个人中心页面
	 */
	@RequestMapping(value = "/mobile/personalEdit")
	public ModelAndView personalEdit() {
		ModelAndView mv = new ModelAndView();
		String userId = ServletRequestManager.getCookieValue("openId");
		User user = UserManager.getUser(userId);
		mv.addObject("user", user);
		mv.setViewName("/mobile/personal/personalEdit");
		return mv;
	}
	
	/**
	 * 
	 * 描述：跳转到个人中心页面
	 */
	@RequestMapping(value = "/mobile/personal/save")
	public PageData personalSave(@ModelAttribute("user") User user) {
		PageData pd = userService.update(user);
		return pd;
	}
	
	/**
	 * 描述：跳转到注册页面
	 */
	@RequestMapping(value = "/mobile/register/identifyingCode")
	@ResponseBody
	public PageData identifyingCode() {
		PageData pd = this.getPageData();
		final String email = pd.getString("email");
		final int identifyingCode = NumberUtil.random(1000, 9999);
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				MailBean mailBean = new MailBean(email, "【芝麻开门】注册验证码", "您好，您的【芝麻开门】验证码为" + identifyingCode + "，请在30分钟内输入！");
				MailSend h = new MailSend(mailBean);
				h.sendMail();
			}
		}).start();
		pd.put("identifyingCode", identifyingCode);
		return pd;
	}
	
	
	/**
	 * 描述：跳转到注册页面
	 */
	@RequestMapping(value = "/mobile/register/save")
	@ResponseBody
	public PageData save(@ModelAttribute("user") User user) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 7);
		user.setVipEndTime(cal.getTime());
		PageData pd = userService.update(user);
		return pd;
	}
	
}
