package com.ifreework.mapper.system;

import java.util.List;
import java.util.Map;

import com.ifreework.common.entity.PageData;
import com.ifreework.entity.system.Authority;

public interface AuthorityMapper  {
	
	public List<Authority> queryPageList(PageData pd);
	public void add(Authority authority);
	public void update(Authority authority);
	public void delete(Map<String,Object> map);
	
	
}
