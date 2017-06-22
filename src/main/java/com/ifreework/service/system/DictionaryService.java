/**    
 * 文件名：FileUploadService.java    
 *    
 * 版本信息：    
 * 日期：2014-12-16    
 * Copyright  Corporation 2014     
 * 版权所有    
 *    
 */
package com.ifreework.service.system;

import java.util.List;

import com.ifreework.common.entity.PageData;
import com.ifreework.entity.system.Dictionary;

/**    
 *     
 * 类名称：FileUploadService    
 * 类描述：    
 * 创建人：王宜华    
 * 创建时间：2014-12-16 上午11:48:20    
 * 修改人：王宜华    
 * 修改时间：2014-12-16 上午11:48:20    
 * 修改备注：    
 * @version     
 *     
 */
public interface DictionaryService {
	public List<Object> queryDictionaryList(PageData pd);
	public Dictionary getDictionaryById(String dictionaryId);
	public PageData add(Dictionary dictionary);
	public PageData update(Dictionary dictionary);
	public PageData delete(String dictionaryId);
	
	public Dictionary getDictionaryByCode(PageData pd);
	public List<Dictionary> queryDictionaryByType(PageData pd);
}