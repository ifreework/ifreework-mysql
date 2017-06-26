package com.ifreework.mapper.weixin;

import java.util.List;

import com.ifreework.common.entity.PageData;
import com.ifreework.entity.weixin.Product;


public interface ProductMapper  {
	public List<Product> queryPageList(PageData pd);
	public Product getProductById(String productId);
	public void add(Product product);
	public void update(Product product);
	public void delete(String productId);
}
