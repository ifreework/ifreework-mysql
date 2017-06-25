package com.ifreework.mapper.weixin;

import java.util.List;

import com.ifreework.common.entity.PageData;
import com.ifreework.entity.weixin.Company;


public interface CompanyMapper  {
	public List<Company> queryPageList(PageData pd);
	public Company getCompanyById(String companyId);
	public Company getCompanyByUserId(String userId);
	public void add(Company company);
	public void update(Company company);
	public void delete(String companyId);
}
