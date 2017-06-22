package com.ifreework.service.db;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifreework.common.entity.PageData;
import com.ifreework.entity.db.Province;
import com.ifreework.mapper.db.ProvinceMapper;

/**
 * 
 * 描述：    
 * @author：wangyh qq735789026  
 * @创建时间：2016年9月2日 下午2:52:42    
 * @修改人：wangyh    
 * @修改时间：2016年9月2日 下午2:52:42    
 * @version 1.0
 */
@Service("provinceService")
public class ProvinceServiceImpl implements ProvinceService {

	@Autowired
	private ProvinceMapper provinceMapper;

	/**
	 * 查询全国省份
	 */
	@Override
	public List<Province> queryProvinceList(PageData pd) {
		return provinceMapper.queryProvinceList(pd);
	}
	
	public List<Map<String,String>> queryProvinceListMap(PageData pd){
		return provinceMapper.queryProvinceListMap(pd);
	}
}
