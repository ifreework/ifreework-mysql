package com.ifreework.mapper.weixin;

import java.util.List;
import java.util.Map;

import com.ifreework.common.entity.PageData;
import com.ifreework.entity.weixin.Product;


public interface ProductMapper  {
	public List<Product> queryPageList(PageData pd);
	public Product getProductById(String productId);
	public List<Map<String,Object>> queryProductMobileList(String companyId);
	public List<Product> queryProductList(PageData pd);
	
	public void add(Product product);
	public void update(Product product);
	public void delete(String productId);
}
