package com.ifreework.mapper.system;

import java.util.List;

import com.ifreework.common.entity.PageData;
import com.ifreework.entity.system.Dictionary;

public interface DictionaryMapper  {
	public List<Dictionary> queryDictionaryList(PageData pd);
	
	public Dictionary getMaxDictionaryOrder(String parentId);
	public Dictionary getDictionaryByIdAndIsLeaf(String dictionaryId);
	public Dictionary getDictionaryById(String dictionaryId);
	public Dictionary getDictionaryByCode(PageData pd);
	public List<Dictionary> queryDictionaryByType(PageData pd);
	
	public void add(Dictionary dictionary);
	public void update(Dictionary dictionary);
	public void delete(String dictionaryId);
}
