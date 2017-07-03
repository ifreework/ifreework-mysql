package com.ifreework.service.weixin;


import java.util.List;
import java.util.Map;

import com.ifreework.common.entity.PageData;
import com.ifreework.entity.weixin.Product;

public interface ProductService {
	public PageData queryPageList(PageData pd);
	public Product getProductById(String productId);
	public List<Map<String,Object>> queryProductMobileList(String companyId);
	public List<Product> queryProductList(PageData pd);
	/**
	 * 
	 * 描述：页面访问量修改
	 * @param companyIntroductionId 
	 * @return
	 */
	public void pageView(Product product) ;
	public PageData add(Product product);
	public PageData update(Product product);
	public PageData delete(String productId);

}