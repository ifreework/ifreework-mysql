package com.ifreework.service.weixin;




import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifreework.common.constant.Constant;
import com.ifreework.common.entity.PageData;
import com.ifreework.common.manager.UserManager;
import com.ifreework.entity.system.User;
import com.ifreework.entity.weixin.Company;
import com.ifreework.mapper.weixin.CompanyMapper;
import com.ifreework.service.system.UserService;
import com.ifreework.util.StringUtil;


@Service("companyService")
public class CompanyServiceImpl  implements CompanyService {
	@Autowired
	private CompanyMapper companyMapper;
	
	@Autowired
	private UserService userService;
	
	@Override
	public PageData queryPageList(PageData pd) {
		List<Company> list = companyMapper.queryPageList(pd);
		pd.setPagination(list);
		return pd;
	}

	@Override
	public Company getCompanyById(String companyId) {
		return companyMapper.getCompanyById(companyId);
	}
	public Company getCompanyByUserId(){
		String userId = UserManager.getUser().getUserId();
		return companyMapper.getCompanyByUserId(userId);
	}

	@Override
	public PageData add(Company company) {
		String companyId = StringUtil.uuid();
		company.setCompanyId(companyId);
		company.setCreater(UserManager.getUser().getUserId());
		company.setCreateTime(new Date());
		companyMapper.add(company);
		User user = new User();
		user.setUserId(UserManager.getUser().getUserId());
		user.setDeptId(companyId);
		userService.update(user);
		PageData pd = new PageData();
		pd.setResult(Constant.SUCCESS);
		return pd;
	}

	@Override
	public PageData update(Company company) {
		companyMapper.update(company);
		PageData pd = new PageData();
		pd.setResult(Constant.SUCCESS);
		return pd;
	}

	@Override
	public PageData delete(String companyId) {
		companyMapper.delete(companyId);
		PageData pd = new PageData();
		pd.setResult(Constant.SUCCESS);
		return pd;
	}
	
	
}