package com.ifreework.mapper.system;

import java.util.List;

import com.ifreework.common.entity.PageData;
import com.ifreework.entity.system.DictionaryType;


public interface DictionaryTypeMapper  {
	public List<DictionaryType> queryDictionaryTypeList(PageData pd);
	public DictionaryType getDictionaryTypeById(String dictionaryTypeId);
	public void add(DictionaryType dictionaryType);
	public void update(DictionaryType dictionaryType);
	public void delete(String dictionaryTypeId);
}
