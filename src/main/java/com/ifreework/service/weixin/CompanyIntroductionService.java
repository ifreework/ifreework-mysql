package com.ifreework.service.weixin;


import com.ifreework.common.entity.PageData;
import com.ifreework.entity.weixin.CompanyIntroduction;

public interface CompanyIntroductionService {
	public PageData queryPageList(PageData pd);
	public CompanyIntroduction getCompanyIntroductionById(String companyIntroductionId);
	public PageData add(CompanyIntroduction companyIntroduction);
	public PageData update(CompanyIntroduction companyIntroduction);
	public PageData delete(String companyIntroductionId);

}