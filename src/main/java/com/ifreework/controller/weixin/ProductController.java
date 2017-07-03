package com.ifreework.controller.weixin;

import java.util.List;
import java.util.Map;

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
import com.ifreework.entity.weixin.Product;
import com.ifreework.service.weixin.ProductService;
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
public class ProductController extends BaseControllerSupport {
	@Autowired
	private ProductService productService;

	@RequestMapping("/weixin/product")
	public ModelAndView gotoView() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("/weixin/product/list");
		return mv;
	}

	
	@RequestMapping("/mobile/product")
	public ModelAndView product() {
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		String userId = pd.getString("m");
		User user = UserManager.getUser(userId);
		List<Map<String,Object>> productList = productService.queryProductMobileList(user.getCompanyId());
		mv.addObject("user", user);
		mv.addObject("productList", productList);
		mv.setViewName("/mobile/product/product");
		return mv;
	}
	
	
	/**
	 * 描述：跳转到产品列表页面
	 */
	@RequestMapping(value = "/mobile/productList")
	public ModelAndView productList() {
		ModelAndView mv = new ModelAndView();
		PageData pd = this.getPageData();
		
		String userId = pd.getString("m");
		User user = UserManager.getUser(userId);
		pd.put("companyId", user.getCompanyId());
		
		List<Product> productList = productService.queryProductList(pd);
		mv.addObject("pd", pd);
		mv.addObject("user", user);
		mv.addObject("productList", productList);
		mv.setViewName("/mobile/product/productList");
		return mv;
	}
	
	
	@RequestMapping("/mobile/productInfo")
	public ModelAndView productInfo() {
		ModelAndView mv = new ModelAndView();
		PageData pd = this.getPageData();
		String userId = pd.getString("m");
		String productId = pd.getString("p");
		User user = UserManager.getUser(userId);
		
		Product product = productService.getProductById(productId);
		productService.pageView(product);
		mv.addObject("user", user);
		mv.addObject("product", product);
		mv.setViewName("/mobile/product/productInfo");
		return mv;
	}
	
	
	@RequestMapping("/weixin/product/add")
	public ModelAndView add() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("/weixin/product/edit");
		return mv;
	}

	@RequestMapping(value = "/weixin/product/edit")
	public ModelAndView edit() {
		ModelAndView mv = this.getModelAndView();
		String productId = this.getPageData().getString("productId");
		Product product = productService.getProductById(productId);
		mv.addObject("product", product);
		mv.setViewName("/weixin/product/edit");
		return mv;
	}

	@RequestMapping("/weixin/product/img")
	public ModelAndView img() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("/weixin/product/img");
		return mv;
	}

	@RequestMapping(value = "/weixin/product/query")
	@ResponseBody
	public PageData query() {
		PageData pd = this.getPageData();
		return productService.queryPageList(pd);
	}

	@RequestMapping(value = "/weixin/product/save")
	@ResponseBody
	public PageData save(@ModelAttribute("product") Product product) {
		PageData pd;
		if (StringUtil.isEmpty(product.getProductId())) {
			pd = productService.add(product);
		} else {
			pd = productService.update(product);
		}
		return pd;
	}
 
	@RequestMapping(value = "/weixin/product/delete")
	@ResponseBody
	public PageData delete() {
		PageData pd = this.getPageData();
		pd = productService.delete(pd.getString("productId"));
		return pd;
	}

}
