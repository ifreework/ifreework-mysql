package com.ifreework.service.weixin;




import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifreework.common.constant.Constant;
import com.ifreework.common.entity.PageData;
import com.ifreework.entity.weixin.Product;
import com.ifreework.mapper.weixin.ProductMapper;
import com.ifreework.util.NumberUtil;
import com.ifreework.util.StringUtil;


@Service("productService")
public class ProductServiceImpl  implements ProductService {
	@Autowired
	private ProductMapper productMapper;
	
	@Override
	public PageData queryPageList(PageData pd) {
		List<Product> list = productMapper.queryPageList(pd);
		pd.setPagination(list);
		return pd;
	}

	@Override
	public Product getProductById(String productId) {
		return productMapper.getProductById(productId);
	}
	
	/**
	 * 描述：微信产品首页查询
	 * @return
	 */
	public List<Map<String,Object>> queryProductMobileList(String companyId){
		return productMapper.queryProductMobileList(companyId);
	}
	
	public List<Product> queryProductList(PageData pd){
		return productMapper.queryProductList(pd);
	}

	/**
	 * 
	 * 描述：页面访问量修改
	 * @param companyIntroductionId 
	 * @return
	 */
	@Override
	public void pageView(Product product) {
		int pageView = NumberUtil.random(1, 10);
		product.setPageView(product.getPageView() + pageView);
		update(product);
	}
	
	@Override
	public PageData add(Product product) {
		String productId = StringUtil.uuid();
		product.setProductId(productId);
		product.setPageView(NumberUtil.random(20000,30000));
		productMapper.add(product);
		PageData pd = new PageData();
		pd.setResult(Constant.SUCCESS);
		return pd;
	}

	@Override
	public PageData update(Product product) {
		productMapper.update(product);
		PageData pd = new PageData();
		pd.setResult(Constant.SUCCESS);
		return pd;
	}

	@Override
	public PageData delete(String productId) {
		productMapper.delete(productId);
		PageData pd = new PageData();
		pd.setResult(Constant.SUCCESS);
		return pd;
	}
	
	
}