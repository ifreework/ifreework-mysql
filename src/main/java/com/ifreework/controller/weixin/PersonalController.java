package com.ifreework.controller.weixin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ifreework.common.controller.BaseControllerSupport;
import com.ifreework.common.entity.PageData;
import com.ifreework.entity.weixin.Company;
import com.ifreework.service.weixin.CompanyService;

/**
 * 
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
	
	/**
	 * 描述：跳转到注册页面
	 */
	@RequestMapping(value = "/mobile/register")
	public ModelAndView register() {
		ModelAndView mv = new ModelAndView();
		PageData pd = this.getPageData();
		String f = pd.getString("f");
		
		List<Company> companys = companyService.queryList(new PageData());
		mv.addObject("forward", f);
		mv.addObject("companys", companys);
		mv.setViewName("/mobile/personal/register");
		return mv;
	}
}
