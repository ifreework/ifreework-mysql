package com.ifreework.mapper.system;
import java.util.List;

import com.ifreework.common.entity.PageData;
import com.ifreework.entity.system.UserRole;

public interface UserRoleMapper  {
	public List<UserRole> queryRoleList(PageData pd);
	public void add(UserRole userRole);
	public void delete(UserRole userRole);
}
