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
import com.ifreework.entity.weixin.ProductType;
import com.ifreework.service.weixin.ProductTypeService;
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
@RequestMapping(value = "/weixin/productType")
public class ProductTypeController extends BaseControllerSupport {
	@Autowired
	private ProductTypeService productTypeService;

	@RequestMapping()
	public ModelAndView gotoView() {
		ModelAndView mv = this.getModelAndView();
		mv.addObject("companyId", UserManager.getUser().getDeptId());
		mv.setViewName("/weixin/productType/list");
		return mv;
	}

	@RequestMapping("/add")
	public ModelAndView add() {
		ModelAndView mv = this.getModelAndView();
		String productTypeId = this.getPageData().getString("productTypeId");
		productTypeId = StringUtil.isEmpty(productTypeId) ? "productTypeId" : productTypeId;
		ProductType productType = new ProductType();
		productType.setParentId(productTypeId);
		mv.setViewName("/weixin/productType/edit");
		return mv;
	}

	@RequestMapping(value = "/edit")
	public ModelAndView edit() {
		ModelAndView mv = this.getModelAndView();
		String productTypeId = this.getPageData().getString("productTypeId");
		ProductType productType = productTypeService.getProductTypeById(productTypeId);
		mv.addObject("productType", productType);
		mv.setViewName("/weixin/productType/edit");
		return mv;
	}

	@RequestMapping("/img")
	public ModelAndView img() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("/weixin/productType/img");
		return mv;
	}

	@RequestMapping(value = "/query")
	@ResponseBody
	public PageData query() {
		PageData pd = this.getPageData();
		return productTypeService.queryPageList(pd);
	}

	@RequestMapping(value = "/queryList")
	@ResponseBody
	public List<ProductType> queryList() {
		PageData pd = this.getPageData();
		return productTypeService.queryList(pd);
	}
	
	
	@RequestMapping(value = "/save")
	@ResponseBody
	public PageData save(@ModelAttribute("productType") ProductType productType) {
		PageData pd;
		if (StringUtil.isEmpty(productType.getProductTypeId())) {
			pd = productTypeService.add(productType);
		} else {
			pd = productTypeService.update(productType);
		}
		return pd;
	}

	@RequestMapping(value = "/delete")
	@ResponseBody
	public PageData delete() {
		PageData pd = this.getPageData();
		pd = productTypeService.delete(pd.getString("productTypeId"));
		return pd;
	}

}
