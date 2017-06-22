package com.ifreework.mapper.db;
import java.util.List;
import java.util.Map;

import com.ifreework.common.entity.PageData;

public interface MunicipalityMapper  {
	public List<Map<String,String>> queryMunicipalityListMap(PageData pd);
}
