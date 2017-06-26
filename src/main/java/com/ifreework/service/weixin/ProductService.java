package com.ifreework.service.weixin;


import com.ifreework.common.entity.PageData;
import com.ifreework.entity.weixin.Product;

public interface ProductService {
	public PageData queryPageList(PageData pd);
	public Product getProductById(String productId);
	public PageData add(Product product);
	public PageData update(Product product);
	public PageData delete(String productId);

}