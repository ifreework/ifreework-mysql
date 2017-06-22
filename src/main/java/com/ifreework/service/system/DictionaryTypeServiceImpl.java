/**    
 * 文件名：FileUploadServiceImpl.java    
 *    
 * 版本信息：    
 * 日期：2014-12-16    
 * Copyright  Corporation 2014     
 * 版权所有    
 *    
 */
package com.ifreework.service.system;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifreework.common.constant.Constant;
import com.ifreework.common.entity.PageData;
import com.ifreework.entity.system.DictionaryType;
import com.ifreework.mapper.system.DictionaryTypeMapper;

/**
 * 
 * 描述：    附件上传
 * @author：wangyh
 * @createDate：2017年5月8日
 * @modify：wangyh    
 * @modifyDate：2017年5月8日 
 * @version 1.0
 */
@Service("dictionaryTypeService")
public class DictionaryTypeServiceImpl implements DictionaryTypeService {
	
	@Autowired
	private DictionaryTypeMapper dictionaryTypeMapper;
	
	public DictionaryType getDictionaryTypeById(String dictionaryTypeId){
		return dictionaryTypeMapper.getDictionaryTypeById(dictionaryTypeId);
	}
	
	@Override
	public PageData add(DictionaryType dictionaryType) {
		PageData pd = new PageData();
		dictionaryTypeMapper.add(dictionaryType);
		pd.setResult(Constant.SUCCESS);
		return pd;
	}
	
	
	@Override
	public PageData update(DictionaryType dictionaryType) {
		PageData pd = new PageData();
		dictionaryTypeMapper.update(dictionaryType);
		pd.setResult(Constant.SUCCESS);
		return pd;
	}
}
