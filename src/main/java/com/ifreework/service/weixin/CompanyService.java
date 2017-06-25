package com.ifreework.service.weixin;


import com.ifreework.common.entity.PageData;
import com.ifreework.entity.weixin.Company;

public interface CompanyService {
	public PageData queryPageList(PageData pd);
	public Company getCompanyByUserId();
	public Company getCompanyById(String companyId);
	public PageData add(Company company);
	public PageData update(Company company);
	public PageData delete(String companyId);

}