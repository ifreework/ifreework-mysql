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



import com.ifreework.common.entity.PageData;
import com.ifreework.entity.system.DictionaryType;

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
public interface DictionaryTypeService {
	public DictionaryType getDictionaryTypeById(String dictionaryTypeId);
	public PageData add( DictionaryType  dictionaryType);
	public PageData update( DictionaryType  dictionaryType);
}