package com.ifreework.mapper.system;
import java.util.List;

import com.ifreework.common.entity.PageData;
import com.ifreework.entity.system.Role;

public interface RoleMapper  {
	public List<Role> queryRoleList(PageData pd);
	public Role getRoleByIdAndIsLeaf(String roleId);
	public Role getRoleById(String roleId);
	
	public void add(Role role);
	public void update(Role role);
	public void delete(String roleId);
	public void deleteChildren(String roleId);
}
