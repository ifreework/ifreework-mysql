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

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.ifreework.common.entity.PageData;

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
public interface AttachmentService {
	public abstract PageData fileUpload(MultipartFile[] files) throws IOException;
	public void download(String attachmentId) ;
	public void delete(String Attachment);
}