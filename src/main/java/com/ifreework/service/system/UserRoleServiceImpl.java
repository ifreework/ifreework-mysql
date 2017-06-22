package com.ifreework.service.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.ifreework.common.constant.Constant;
import com.ifreework.common.entity.PageData;
import com.ifreework.entity.system.UserRole;
import com.ifreework.mapper.system.UserRoleMapper;

@Service("userRoleService")
public class UserRoleServiceImpl implements UserRoleService {

	@Autowired
	private UserRoleMapper userRoleMapper;
	
	public List<UserRole> queryRoleList(PageData pd) {
		return userRoleMapper.queryRoleList(pd);
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
		List<UserRole> addList = JSON.parseArray(addStr, UserRole.class);
		List<UserRole> deleteList = JSON.parseArray(deleteStr, UserRole.class);
		for (UserRole userRole : addList) {
			userRoleMapper.add(userRole);
		}
		
		for (UserRole userRole : deleteList) {
			userRoleMapper.delete(userRole);
		}
		pd.setResult(Constant.SUCCESS);
		return pd;
	}
}
