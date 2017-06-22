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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifreework.common.constant.Constant;
import com.ifreework.common.entity.PageData;
import com.ifreework.entity.system.Dictionary;
import com.ifreework.mapper.system.DictionaryMapper;
import com.ifreework.mapper.system.DictionaryTypeMapper;
import com.ifreework.util.StringUtil;

/**
 * 
 * 描述：    附件上传
 * @author：wangyh
 * @createDate：2017年5月8日
 * @modify：wangyh    
 * @modifyDate：2017年5月8日 
 * @version 1.0
 */
@Service("dictionaryService")
public class DictionaryServiceImpl implements DictionaryService {
	
	@Autowired
	private DictionaryMapper dictionaryMapper;
	
	@Autowired
	private DictionaryTypeMapper dictionaryTypeMapper;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List queryDictionaryList(PageData pd) {
		String dictionaryTypeId = pd.getString("dictionaryTypeId");
		if(StringUtil.isEmpty(dictionaryTypeId)){
			List list = dictionaryTypeMapper.queryDictionaryTypeList(pd);
			return list;
		}
		
		List list = dictionaryMapper.queryDictionaryList(pd);
		
		return list;
	}

	public Dictionary getDictionaryById(String dictionaryId){
		return dictionaryMapper.getDictionaryById(dictionaryId);
	}
	
	@Override
	public PageData add(Dictionary dictionary) {
		PageData pd = new PageData();
		setDictionaryOrder(dictionary);
		dictionary.setStatus("1");
		dictionary.setIsLeaf("1");
		dictionaryMapper.add(dictionary);
		setParentNotLeaf(dictionary);
		pd.setResult(Constant.SUCCESS);
		return pd;
	}
	
	/**
	 * 
	 * 描述：设置部门ID，默认格式 parent_order + 100
	 * @Title: setDictionaryId
	 * @param 
	 * @return   
	 * @throws
	 */
	private void setDictionaryOrder(Dictionary dictionary) {
		Dictionary dict = dictionaryMapper.getMaxDictionaryOrder(dictionary.getParentId());
		if(dict != null){
			dictionary.setOrderNum(dict.getOrderNum() + 1);		
		}else{
			dictionary.setOrderNum(0);
		}
	}
	
	/**
	 * 
	 * 描述：如果父节点为子节点的话，修改父节点为非子节点
	 * @Title: setDictionaryId
	 * @param 
	 * @return   
	 * @throws
	 */
	private void setParentNotLeaf(Dictionary dictionary){
		Dictionary dept = dictionaryMapper.getDictionaryByIdAndIsLeaf(dictionary.getParentId());
		if(dept != null){
			dept.setIsLeaf("0");
			dictionaryMapper.update(dept);
		}
	}
	
	@Override
	public PageData update(Dictionary dictionary) {
		PageData pd = new PageData();
		dictionaryMapper.update(dictionary);
		pd.setResult(Constant.SUCCESS);
		return pd;
	}

	@Override
	public PageData delete(String dictionaryId) {
		return null;
	}

	@Override
	public Dictionary getDictionaryByCode(PageData pd) {
		return dictionaryMapper.getDictionaryByCode(pd);
	}

	@Override
	public List<Dictionary> queryDictionaryByType(PageData pd) {
		return dictionaryMapper.queryDictionaryByType(pd);
	}
}
