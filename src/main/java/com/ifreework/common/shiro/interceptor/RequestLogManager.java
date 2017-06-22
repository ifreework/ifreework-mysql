package com.ifreework.common.shiro.interceptor;

import com.ifreework.entity.system.RequestLog;

public interface RequestLogManager {
	public RequestLog getRequestLog(String url);
	public void saveRequestLog(RequestLog requestLog);
}
