package com.ifreework.service.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifreework.common.entity.PageData;
import com.ifreework.entity.system.Authority;
import com.ifreework.mapper.system.AuthorityMapper;

@Service("authorityService")
public class AuthorityServiceImpl implements AuthorityService {

	@Autowired
	private AuthorityMapper authorityMapper;
	
	
	@Override
	public PageData queryPageList(PageData pd) {
		List<Authority> list = authorityMapper.queryPageList(pd);
		pd = new PageData();
		pd.setPagination(list);
		return pd;
	}

}
