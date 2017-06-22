package com.ifreework.service.system;

import java.util.List;

import com.ifreework.common.entity.PageData;
import com.ifreework.entity.system.UserRole;


public interface UserRoleService {
	

	/**
	 * 
	 * 描述：查询菜单树
	 * @Title: queryMenuList
	 * @param 
	 *         pd 包涵parentId和roleId
	 * @return   list
	 * @throws
	 */
	public List<UserRole> queryRoleList(PageData pd);
	public PageData save(PageData pd);
}