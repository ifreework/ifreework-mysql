package com.ifreework.service.system;


import com.ifreework.common.entity.PageData;

public interface LoginLogService {
	public PageData queryPageList(PageData pd);
	public void add(String username);
}
