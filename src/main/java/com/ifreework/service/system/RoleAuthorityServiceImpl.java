package com.ifreework.service.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.ifreework.common.constant.Constant;
import com.ifreework.common.entity.PageData;
import com.ifreework.entity.system.RoleAuthority;
import com.ifreework.mapper.system.RoleAuthorityMapper;

@Service("roleAuthorityService")
public class RoleAuthorityServiceImpl implements RoleAuthorityService {

	@Autowired
	private RoleAuthorityMapper roleAuthorityMapper;
	
	public List<RoleAuthority> queryMenuList(PageData pd) {
		return roleAuthorityMapper.queryMenuList(pd);
	}

	/**
	 * 
	 * 描述：
	 * @Title: save
	 * @param 
	 * @return   
	 * @throws
	 */
	public PageData save(PageData pd) {
		String addStr = pd.getString("addStr");
		String deleteStr = pd.getString("deleteStr");
		List<RoleAuthority> addList = JSON.parseArray(addStr, RoleAuthority.class);
		List<RoleAuthority> deleteList = JSON.parseArray(deleteStr, RoleAuthority.class);
		for (RoleAuthority roleAuthority : addList) {
			roleAuthorityMapper.add(roleAuthority);
		}
		
		for (RoleAuthority roleAuthority : deleteList) {
			roleAuthorityMapper.delete(roleAuthority);
		}
		pd.setResult(Constant.SUCCESS);
		return pd;
	}
}
