package com.ifreework.service.db;

import java.util.List;
import java.util.Map;

import com.ifreework.common.entity.PageData;

public interface MunicipalityService {

	
	public List<Map<String,String>> queryMunicipalityListMap(PageData pd);
}