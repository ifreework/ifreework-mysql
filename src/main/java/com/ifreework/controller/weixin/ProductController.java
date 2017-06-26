package com.ifreework.controller.weixin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ifreework.common.controller.BaseControllerSupport;
import com.ifreework.common.entity.PageData;
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
@RequestMapping(value = "/weixin/product")
public class ProductController extends BaseControllerSupport {
	@Autowired
	private ProductService productService;

	@RequestMapping()
	public ModelAndView gotoView() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("/weixin/product/list");
		return mv;
	}

	@RequestMapping("/add")
	public ModelAndView add() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("/weixin/product/edit");
		return mv;
	}

	@RequestMapping(value = "/edit")
	public ModelAndView edit() {
		ModelAndView mv = this.getModelAndView();
		String productId = this.getPageData().getString("productId");
		Product product = productService.getProductById(productId);
		mv.addObject("product", product);
		mv.setViewName("/weixin/product/edit");
		return mv;
	}

	@RequestMapping("/img")
	public ModelAndView img() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("/weixin/product/img");
		return mv;
	}

	@RequestMapping(value = "/query")
	@ResponseBody
	public PageData query() {
		PageData pd = this.getPageData();
		return productService.queryPageList(pd);
	}

	@RequestMapping(value = "/save")
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

	@RequestMapping(value = "/delete")
	@ResponseBody
	public PageData delete() {
		PageData pd = this.getPageData();
		pd = productService.delete(pd.getString("productId"));
		return pd;
	}

}
