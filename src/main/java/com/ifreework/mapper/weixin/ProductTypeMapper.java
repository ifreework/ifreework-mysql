package com.ifreework.mapper.weixin;

import java.util.List;

import com.ifreework.common.entity.PageData;
import com.ifreework.entity.weixin.ProductType;


public interface ProductTypeMapper  {
	public List<ProductType> queryPageList(PageData pd);
	public ProductType getProductTypeById(String productTypeId);
	public void add(ProductType productType);
	public void update(ProductType productType);
}
