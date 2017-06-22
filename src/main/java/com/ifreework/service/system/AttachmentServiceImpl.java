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

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ifreework.common.constant.Constant;
import com.ifreework.common.entity.PageData;
import com.ifreework.common.manager.ServletRequestManager;
import com.ifreework.common.manager.SysTemConfigManager;
import com.ifreework.entity.system.Attachment;
import com.ifreework.entity.system.Config;
import com.ifreework.mapper.system.AttachmentMapper;
import com.ifreework.util.FTPUtil;
import com.ifreework.util.FileUtil;
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
@Service("fileUploadService")
public class AttachmentServiceImpl implements AttachmentService {
	private static Logger logger = LoggerFactory.getLogger(AttachmentServiceImpl.class);
	private static final String REMOTEPATH = "/attachment"; // 文件保存支线

	@Autowired
	private AttachmentMapper attachmentMapper;

	/**
	 * 
	 * 描述：
	 * @Title: fileUpload
	 * @param 
	 * @return   
	 * @throws
	 */
	public PageData fileUpload(MultipartFile[] files) throws IOException {
		String attachmentId = "";
		PageData pd = new PageData();

		for (MultipartFile file : files) {

			String filePath = "";
			if ("1".equals(SysTemConfigManager.get(Config.FTP_ENABLE))) { // 是否启用ftp上传
				filePath = ftpUpload(file);
			} else { // 未启用ftp的话，将文件保存在本地
				filePath = localDiskUpload(file);
			}
			Attachment attachment = new Attachment();
			attachment.setAttachmentPath(filePath);
			attachment.setAttachmentName(file.getOriginalFilename());
			attachment.setAttachmentSize(file.getSize());
			attachment.setAttachmentFormatSize(FileUtil.convertFileSize(file.getSize()));
			add(attachment);
			
			attachmentId += StringUtil.isEmpty(attachmentId) ? attachment.getAttachmentId()
					: "," + attachment.getAttachmentId();
		}
		pd.put("attachmentId", attachmentId);
		pd.setResult(Constant.SUCCESS);
		return pd;
	}

	/**
	 * 
	 * 描述：本地磁盘上传
	 * @Title: localDiskUpload
	 * @param 
	 * @return   
	 * @throws
	 */
	private String localDiskUpload(MultipartFile file) throws IOException {
		String filePath;
		filePath = SysTemConfigManager.get(Config.FILE_PATH) + REMOTEPATH;
		try {
			filePath = FileUtil.fileUpload(file, filePath);
		} catch (IOException e) {
			logger.error("file upload error is {}", e);
			FileUtil.fileDelete(filePath);
			throw e;
		}
		return filePath;
	}

	/**
	 * 
	 * 描述：ftp文件上传
	 * @Title: ftpUpload
	 * @param 
	 * @return   
	 * @throws
	 */
	private String ftpUpload(MultipartFile file) throws IOException {
		String filePath;
		String fileName = FileUtil.getUUIDName(file.getOriginalFilename());

		FTPUtil.upload(SysTemConfigManager.get(Config.FTP_ADDRESS), Integer.parseInt(SysTemConfigManager.get(Config.FTP_PORT)),
				SysTemConfigManager.get(Config.FTP_USERNAME), SysTemConfigManager.get(Config.FTP_PASSWORD), fileName, REMOTEPATH,
				file.getInputStream());

		filePath = REMOTEPATH + "/" + fileName;
		return filePath;
	}

	/**
	 * 
	 * 描述：保存
	 * @Title: add
	 * @param 
	 * @return   
	 * @throws
	 */
	public void add(Attachment attachment) {
		attachmentMapper.add(attachment);
	}

	/**
	 * 
	 * 描述：文件下载，如果文件保存在ftp上，则先将文件下载到本地服务器上
	 * @Title: download
	 * @param 
	 * @return   
	 * @throws
	 */
	public void download(String attachmentId) {
		Attachment attachment = attachmentMapper.getAttachmentById(attachmentId);
		HttpServletResponse response = ServletRequestManager.getHttpServletResponse();
		try {
			if (attachment == null) {
				logger.debug("file {} is not found!", attachmentId);
				printHtml(attachmentId, response);
			} else {
				String filePath = attachment.getAttachmentPath();

				if ("1".equals(SysTemConfigManager.get(Config.FTP_ENABLE))) { // 如果文件保存在ftp服务器，则先将文件下载到本地
					FTPUtil.download(SysTemConfigManager.get(Config.FTP_ADDRESS),
							Integer.parseInt(SysTemConfigManager.get(Config.FTP_PORT)),
							SysTemConfigManager.get(Config.FTP_USERNAME), SysTemConfigManager.get(Config.FTP_PASSWORD), filePath,
							SysTemConfigManager.get(Config.FILE_TEMP_PATH) + filePath);
					filePath = SysTemConfigManager.get(Config.FILE_TEMP_PATH) + filePath;
				}

				FileUtil.fileDownload(response, filePath, attachment.getAttachmentName());

			}
		} catch (IOException e) {
			logger.error("download file error {}" ,e);
			printHtml(attachmentId, response);
		}
	}

	/**
	 * 
	 * 描述：下载错误提示
	 * @Title: printHtml
	 * @param 
	 * @return   
	 * @throws
	 */
	private void printHtml(String attachmentId, HttpServletResponse response) {
		try {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter prw = response.getWriter();
			prw.print("未能找到文件" + attachmentId + ",文件下载失败！");
			prw.flush();
			prw.close();
		} catch (IOException e) {
			logger.error("download file error {}" ,e);
			printHtml(attachmentId, response);
		}
	}

	/**
	 * 
	 * 描述：删除文件操作
	 * @Title: delete
	 * @param 
	 * @return   
	 * @throws
	 */
	public void delete(String attachmentId) {
		Attachment attachment = attachmentMapper.getAttachmentById(attachmentId);
		attachmentMapper.delete(attachmentId);

		if ("1".equals(SysTemConfigManager.get(Config.FTP_ENABLE))) { // 是否启用ftp上传
			FTPUtil.delete(SysTemConfigManager.get(Config.FTP_ADDRESS), Integer.parseInt(SysTemConfigManager.get(Config.FTP_PORT)),
					SysTemConfigManager.get(Config.FTP_USERNAME), SysTemConfigManager.get(Config.FTP_PASSWORD),
					attachment.getAttachmentPath());

		} else { // 未启用ftp的话，将文件保存在本地
			FileUtil.fileDelete(attachment.getAttachmentPath());
		}

	}
}
