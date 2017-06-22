package com.ifreework.service.db;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifreework.common.entity.PageData;
import com.ifreework.mapper.db.MunicipalityMapper;

/**
 * 
 * 描述：    
 * @author：wangyh qq735789026  
 * @创建时间：2016年9月2日 下午2:52:42    
 * @修改人：wangyh    
 * @修改时间：2016年9月2日 下午2:52:42    
 * @version 1.0
 */
@Service("municipalityService")
public class MunicipalityServiceImpl implements MunicipalityService {

	@Autowired
	private MunicipalityMapper municipalityMapper;

	
	public List<Map<String,String>> queryMunicipalityListMap(PageData pd){
		return municipalityMapper.queryMunicipalityListMap(pd);
	}
}
