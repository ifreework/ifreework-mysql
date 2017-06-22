package com.ifreework.mapper.system;
import java.util.List;

import com.ifreework.common.entity.PageData;
import com.ifreework.entity.system.LoginLog;


public interface LoginLogMapper  {
	public List<LoginLog> queryPageList(PageData pd);
	public void add(LoginLog loginLog);
	public void update(String username);
}
