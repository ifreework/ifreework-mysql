package com.ifreework.mapper.db;
import java.util.List;
import java.util.Map;

import com.ifreework.common.entity.PageData;

public interface CountyMapper  {
	public List<Map<String,String>> queryCountyListMap(PageData pd);
}
