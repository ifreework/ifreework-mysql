package com.ifreework.service.weixin;




import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifreework.common.constant.Constant;
import com.ifreework.common.entity.PageData;
import com.ifreework.common.manager.UserManager;
import com.ifreework.entity.weixin.ProductType;
import com.ifreework.mapper.weixin.ProductTypeMapper;
import com.ifreework.util.StringUtil;


@Service("productTypeService")
public class ProductTypeServiceImpl  implements ProductTypeService {
	@Autowired
	private ProductTypeMapper productTypeMapper;
	
	@Override
	public PageData queryPageList(PageData pd) {
		pd.put("companyId", UserManager.getUser().getDeptId());
		List<ProductType> list = productTypeMapper.queryPageList(pd);
		pd.setPagination(list);
		return pd;
	}
	
	@Override
	public List<ProductType> queryList(PageData pd) {
		pd.put("companyId", UserManager.getUser().getDeptId());
		List<ProductType> list = productTypeMapper.queryPageList(pd);
		return list;
	}
	

	@Override
	public ProductType getProductTypeById(String productTypeId) {
		return productTypeMapper.getProductTypeById(productTypeId);
	}

	@Override
	public PageData add(ProductType productType) {
		String productTypeId = StringUtil.uuid();
		productType.setProductTypeId(productTypeId);
		productType.setCompanyId(UserManager.getUser().getDeptId());
		productType.setIsLeaf("1");
		productType.setStatus("1");
		productTypeMapper.add(productType);
		productType = getProductTypeById(productType.getParentId());
		if(productType != null && "1".equals(productType.getIsLeaf())){ //如果上级节点为子节点，改上级节点为非子节点
			productType.setIsLeaf("0");
			update(productType);
		}
		PageData pd = new PageData();
		pd.setResult(Constant.SUCCESS);
		return pd;
	}

	@Override
	public PageData update(ProductType productType) {
		productTypeMapper.update(productType);
		PageData pd = new PageData();
		pd.setResult(Constant.SUCCESS);
		return pd;
	}

	@Override
	public PageData delete(String productTypeId) {
		ProductType productType = new ProductType();
		productType.setProductTypeId(productTypeId);
		productType.setStatus("0");
		return update(productType);
	}
	
	
}