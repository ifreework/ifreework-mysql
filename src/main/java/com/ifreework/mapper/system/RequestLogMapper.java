package com.ifreework.mapper.system;
import java.util.List;

import com.ifreework.common.entity.PageData;
import com.ifreework.entity.system.RequestLog;


public interface RequestLogMapper  {
	public List<RequestLog> queryPageList(PageData pd);
	public void add(RequestLog requestLog);
}
