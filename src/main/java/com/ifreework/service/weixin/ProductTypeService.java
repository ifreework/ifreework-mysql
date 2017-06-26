package com.ifreework.service.weixin;


import java.util.List;

import com.ifreework.common.entity.PageData;
import com.ifreework.entity.weixin.ProductType;

public interface ProductTypeService {
	public PageData queryPageList(PageData pd);
	public ProductType getProductTypeById(String productTypeId);
	public PageData add(ProductType productType);
	public PageData update(ProductType productType);
	public PageData delete(String productTypeId);
	public List<ProductType> queryList(PageData pd);

}