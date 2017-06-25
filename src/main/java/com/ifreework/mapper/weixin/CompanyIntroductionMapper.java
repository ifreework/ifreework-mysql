package com.ifreework.mapper.weixin;

import java.util.List;

import com.ifreework.common.entity.PageData;
import com.ifreework.entity.weixin.CompanyIntroduction;


public interface CompanyIntroductionMapper  {
	public List<CompanyIntroduction> queryPageList(PageData pd);
	public CompanyIntroduction getCompanyIntroductionById(String companyIntroductionId);
	public void add(CompanyIntroduction companyIntroduction);
	public void update(CompanyIntroduction companyIntroduction);
	public void delete(String companyIntroductionId);
}
