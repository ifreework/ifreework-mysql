package com.ifreework.service.weixin;




import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifreework.common.constant.Constant;
import com.ifreework.common.entity.PageData;
import com.ifreework.entity.weixin.CompanyIntroduction;
import com.ifreework.mapper.weixin.CompanyIntroductionMapper;
import com.ifreework.util.NumberUtil;
import com.ifreework.util.StringUtil;


@Service("companyIntroductionService")
public class CompanyIntroductionServiceImpl  implements CompanyIntroductionService {
	@Autowired
	private CompanyIntroductionMapper companyIntroductionMapper;
	
	@Override
	public PageData queryPageList(PageData pd) {
		List<CompanyIntroduction> list = companyIntroductionMapper.queryPageList(pd);
		pd.setPagination(list);
		return pd;
	}

	@Override
	public CompanyIntroduction getCompanyIntroductionById(String companyIntroductionId) {
		return companyIntroductionMapper.getCompanyIntroductionById(companyIntroductionId);
	}

	@Override
	public PageData add(CompanyIntroduction companyIntroduction) {
		String introductionId = StringUtil.uuid();
		companyIntroduction.setIntroductionId(introductionId);
		companyIntroduction.setPageView(NumberUtil.random(20000,30000));
		companyIntroductionMapper.add(companyIntroduction);
		PageData pd = new PageData();
		pd.setResult(Constant.SUCCESS);
		return pd;
	}

	@Override
	public PageData update(CompanyIntroduction companyIntroduction) {
		companyIntroductionMapper.update(companyIntroduction);
		PageData pd = new PageData();
		pd.setResult(Constant.SUCCESS);
		return pd;
	}

	@Override
	public PageData delete(String companyIntroductionId) {
		companyIntroductionMapper.delete(companyIntroductionId);
		PageData pd = new PageData();
		pd.setResult(Constant.SUCCESS);
		return pd;
	}
	
	
}