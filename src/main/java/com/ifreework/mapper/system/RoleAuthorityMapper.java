package com.ifreework.mapper.system;
import java.util.List;

import com.ifreework.common.entity.PageData;
import com.ifreework.entity.system.RoleAuthority;

public interface RoleAuthorityMapper  {
	public List<RoleAuthority> queryMenuList(PageData pd);
	public void add(RoleAuthority roleAuthority);
	public void delete(RoleAuthority roleAuthority);
}
