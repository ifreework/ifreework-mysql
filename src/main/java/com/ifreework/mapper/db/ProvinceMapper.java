package com.ifreework.mapper.db;
import java.util.List;
import java.util.Map;

import com.ifreework.common.entity.PageData;
import com.ifreework.entity.db.Province;

public interface ProvinceMapper  {
	public List<Province> queryProvinceList(PageData pd);
	public List<Map<String,String>> queryProvinceListMap(PageData pd);
}
