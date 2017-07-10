package com.ifreework.common.manager;

import com.ifreework.entity.system.Config;

/**
 * 描述：附件下载工具类
 * @author：wangyh
 * @createDate：2017年7月10日
 * @modify：wangyh    
 * @modifyDate：2017年7月10日 
 * @version 1.0
 */
public class AttachmentManager {

	/**
	 * 描述：根据数据库配置，生成文件下载路径
	 * @param attachmentId
	 * @return 
	 * @return
	 */
	public static String getDownloadUrl(String attachmentId){
		String attachmentUrl = SystemConfigManager.get(Config.FILE_DOWNLOAD_URL);
		attachmentUrl = attachmentUrl.replace("[ATTACHMENTID]", attachmentId);
		return attachmentUrl;
	}
	
}
