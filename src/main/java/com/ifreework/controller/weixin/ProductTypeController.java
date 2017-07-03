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
public class ProductTypeController extends BaseControllerSupport {
	@Autowired
	private ProductTypeService productTypeService;

	@RequestMapping("/weixin/productType")
	public ModelAndView gotoView() {
		ModelAndView mv = this.getModelAndView();
		mv.addObject("companyId", UserManager.getUser().getDeptId());
		mv.setViewName("/weixin/productType/list");
		return mv;
	}

	/**
	 * 描述：跳转到添加产品页面
	 */
	@RequestMapping(value = "/mobile/productType")
	public ModelAndView productType() {
		ModelAndView mv = new ModelAndView();
		PageData pd = this.getPageData();
		String userId = pd.getString("m");
		String companyId = pd.getString("c");
		pd.put("companyId", companyId);
		pd.put("parentId", "0");
		List<ProductType> productTypeList = productTypeService.queryList(pd);
		
		mv.addObject("userId", userId);
		mv.addObject("productTypeList", productTypeList);
		
		mv.setViewName("/mobile/product/productType");
		return mv;
	}
	
	/**
	 * 描述：跳转到添加产品页面
	 */
	@RequestMapping(value = "/mobile/productType/query")
	@ResponseBody
	public List<ProductType> mobileQuery() {
		PageData pd = this.getPageData();
		List<ProductType> productTypeList = productTypeService.queryList(pd);
		return productTypeList;
	}
	
	@RequestMapping("/weixin/productType/add")
	public ModelAndView add() {
		ModelAndView mv = this.getModelAndView();
		String productTypeId = this.getPageData().getString("productTypeId");
		
		productTypeId = StringUtil.isEmpty(productTypeId) ? "0" : productTypeId;
		ProductType productType = new ProductType();
		productType.setParentId(productTypeId);
		mv.addObject("productType", productType);
		mv.setViewName("/weixin/productType/edit");
		return mv;
	}

	@RequestMapping(value = "/weixin/productType/edit")
	public ModelAndView edit() {
		ModelAndView mv = this.getModelAndView();
		String productTypeId = this.getPageData().getString("productTypeId");
		ProductType productType = productTypeService.getProductTypeById(productTypeId);
		mv.addObject("productType", productType);
		mv.setViewName("/weixin/productType/edit");
		return mv;
	}

	@RequestMapping("/weixin/productType/img")
	public ModelAndView img() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("/weixin/productType/img");
		return mv;
	}

	@RequestMapping(value = "/weixin/productType/query")
	@ResponseBody
	public PageData query() {
		PageData pd = this.getPageData();
		return productTypeService.queryPageList(pd);
	}

	@RequestMapping(value = "/weixin/productType/queryList")
	@ResponseBody
	public List<ProductType> queryList() {
		PageData pd = this.getPageData();
		return productTypeService.queryList(pd);
	}
	
	
	@RequestMapping(value = "/weixin/productType/save")
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

	@RequestMapping(value = "/weixin/productType/delete")
	@ResponseBody
	public PageData delete() {
		PageData pd = this.getPageData();
		pd = productTypeService.delete(pd.getString("productTypeId"));
		return pd;
	}

}
