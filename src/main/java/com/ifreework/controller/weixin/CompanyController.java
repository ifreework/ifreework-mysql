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
public class CompanyController extends BaseControllerSupport {
	@Autowired
	private CompanyService companyService;

	@RequestMapping("/weixin/company")
	public ModelAndView gotoView() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("/weixin/company/list");
		return mv;
	}

	@RequestMapping("/weixin/company/add")
	public ModelAndView add() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("/weixin/company/edit");
		return mv;
	}

	@RequestMapping(value = "/weixin/company/edit")
	public ModelAndView edit() {
		ModelAndView mv = this.getModelAndView();
		String companyId = this.getPageData().getString("companyId");
		Company company = companyService.getCompanyById(companyId);
		mv.addObject("company", company);
		mv.setViewName("/weixin/company/edit");
		return mv;
	}


	@RequestMapping(value = "/weixin/company/query")
	@ResponseBody
	public PageData query() {
		PageData pd = this.getPageData();
		return companyService.queryPageList(pd);
	}

	@RequestMapping(value = "/weixin/company/save")
	@ResponseBody
	public PageData save(@ModelAttribute("company") Company company) {
		PageData pd;
		if (StringUtil.isEmpty(company.getCompanyId())) {
			pd = companyService.add(company);
		} else {
			pd = companyService.update(company);
		}
		return pd;
	}

	@RequestMapping(value = "/weixin/company/delete")
	@ResponseBody
	public PageData delete() {
		PageData pd = this.getPageData();
		pd = companyService.delete(pd.getString("companyId"));
		return pd;
	}

}
