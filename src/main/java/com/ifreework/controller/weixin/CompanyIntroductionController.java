package com.ifreework.controller.weixin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ifreework.common.controller.BaseControllerSupport;
import com.ifreework.common.entity.PageData;
import com.ifreework.common.manager.UserManager;
import com.ifreework.entity.system.User;
import com.ifreework.entity.weixin.CompanyIntroduction;
import com.ifreework.service.weixin.CompanyIntroductionService;
import com.ifreework.util.StringUtil;

/**
 * 
 * 描述：微信接口调用总接口
 * 
 * @author：wangyh
 * @createDate：2017年6月22日 @modify：wangyh
 * @modifyDate：2017年6月22日 @version 1.0
 */
@Controller
public class CompanyIntroductionController extends BaseControllerSupport {
	
	@Autowired
	private CompanyIntroductionService companyIntroductionService;
	

	@RequestMapping("/weixin/companyIntroduction")
	public ModelAndView gotoView() {
		ModelAndView mv = this.getModelAndView();
		mv.addObject("companyId", UserManager.getUser().getDeptId());
		mv.setViewName("/weixin/companyIntroduction/list");
		return mv;
	}

	@RequestMapping("/mobile/company")
	public ModelAndView company() {
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		String userId = pd.getString("m");
		User user = UserManager.getUser(userId);
		pd.put("companyId", user.getCompanyId());
		List<CompanyIntroduction> list = companyIntroductionService.queryList(pd);
		mv.addObject("user", user);
		mv.addObject("list", list);
		mv.setViewName("/mobile/company/company");
		return mv;
	}
	
	
	/**
	 * 描述：跳转到公司介绍详情页面
	 */
	@RequestMapping(value = "/mobile/companyInfo")
	public ModelAndView companyInfo() {
		ModelAndView mv = new ModelAndView();
		PageData pd = this.getPageData();
		String userId = pd.getString("m");
		String introductionId = pd.getString("p");
		User user = UserManager.getUser(userId);
		CompanyIntroduction introduction = companyIntroductionService.getCompanyIntroductionById(introductionId);
		companyIntroductionService.pageView(introduction);
		mv.addObject("user", user);
		mv.addObject("introduction", introduction);
		mv.setViewName("/mobile/company/companyInfo");
		return mv;
	}
	
	@RequestMapping("/weixin/companyIntroduction/add")
	public ModelAndView add() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("/weixin/companyIntroduction/edit");
		return mv;
	}

	@RequestMapping(value = "/weixin/companyIntroduction/edit")
	public ModelAndView edit() {
		ModelAndView mv = this.getModelAndView();
		String introductionId = this.getPageData().getString("introductionId");
		CompanyIntroduction companyIntroduction = companyIntroductionService.getCompanyIntroductionById(introductionId);
		mv.addObject("companyIntroduction", companyIntroduction);
		mv.setViewName("/weixin/companyIntroduction/edit");
		return mv;
	}

	@RequestMapping("/weixin/companyIntroduction/img")
	public ModelAndView img() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("/weixin/companyIntroduction/img");
		return mv;
	}

	@RequestMapping(value = "/weixin/companyIntroduction/query")
	@ResponseBody
	public PageData query() {
		PageData pd = this.getPageData();
		return companyIntroductionService.queryPageList(pd);
	}

	@RequestMapping(value = "/weixin/companyIntroduction/save")
	@ResponseBody
	public PageData save(@ModelAttribute("companyIntroduction") CompanyIntroduction companyIntroduction) {
		PageData pd;
		if (StringUtil.isEmpty(companyIntroduction.getIntroductionId())) {
			companyIntroduction.setCompanyId(UserManager.getUser().getDeptId());
			pd = companyIntroductionService.add(companyIntroduction);
		} else {
			pd = companyIntroductionService.update(companyIntroduction);
		}
		return pd;
	}

	@RequestMapping(value = "/weixin/companyIntroduction/delete")
	@ResponseBody
	public PageData delete() {
		PageData pd = this.getPageData();
		pd = companyIntroductionService.delete(pd.getString("introductionId"));
		return pd;
	}

}
