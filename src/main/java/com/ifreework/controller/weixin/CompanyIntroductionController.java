package com.ifreework.controller.weixin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ifreework.common.controller.BaseControllerSupport;
import com.ifreework.common.entity.PageData;
import com.ifreework.entity.weixin.Company;
import com.ifreework.entity.weixin.CompanyIntroduction;
import com.ifreework.service.weixin.CompanyIntroductionService;
import com.ifreework.service.weixin.CompanyService;
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
@RequestMapping(value = "/weixin/companyIntroduction")
public class CompanyIntroductionController extends BaseControllerSupport {
	
	@Autowired
	private CompanyIntroductionService companyIntroductionService;
	
	@Autowired
	private CompanyService companyService;

	@RequestMapping()
	public ModelAndView gotoView() {
		ModelAndView mv = this.getModelAndView();
		Company company = companyService.getCompanyByUserId();
		mv.addObject("company", company);
		mv.setViewName("/weixin/companyIntroduction/list");
		return mv;
	}

	@RequestMapping("/add")
	public ModelAndView add() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("/weixin/companyIntroduction/edit");
		return mv;
	}

	@RequestMapping(value = "/edit")
	public ModelAndView edit() {
		ModelAndView mv = this.getModelAndView();
		String introductionId = this.getPageData().getString("introductionId");
		CompanyIntroduction companyIntroduction = companyIntroductionService.getCompanyIntroductionById(introductionId);
		mv.addObject("companyIntroduction", companyIntroduction);
		mv.setViewName("/weixin/companyIntroduction/edit");
		return mv;
	}

	@RequestMapping("/img")
	public ModelAndView img() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("/weixin/companyIntroduction/img");
		return mv;
	}

	@RequestMapping(value = "/query")
	@ResponseBody
	public PageData query() {
		PageData pd = this.getPageData();
		return companyIntroductionService.queryPageList(pd);
	}

	@RequestMapping(value = "/save")
	@ResponseBody
	public PageData save(@ModelAttribute("companyIntroduction") CompanyIntroduction companyIntroduction) {
		PageData pd;
		if (StringUtil.isEmpty(companyIntroduction.getIntroductionId())) {
			Company company = companyService.getCompanyByUserId();
			companyIntroduction.setCompanyId(company.getCompanyId());
			pd = companyIntroductionService.add(companyIntroduction);
		} else {
			pd = companyIntroductionService.update(companyIntroduction);
		}
		return pd;
	}

	@RequestMapping(value = "/delete")
	@ResponseBody
	public PageData delete() {
		PageData pd = this.getPageData();
		pd = companyIntroductionService.delete(pd.getString("introductionId"));
		return pd;
	}

}
