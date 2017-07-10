package com.ifreework.common.manager;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.ifreework.entity.system.Config;
import com.ifreework.util.FTPUtil;
import com.ifreework.util.FileUtil;

/**
 * 
 * 描述：文件上传管理类    
 * @author：wangyh
 * @createDate：2017年7月10日
 * @modify：wangyh    
 * @modifyDate：2017年7月10日 
 * @version 1.0
 */
public class FileManager {
	private static Logger logger = LoggerFactory.getLogger(FileManager.class);

	public static String htmlToStr(String filePath) throws IOException {
		if ("1".equals(SystemConfigManager.get(Config.FTP_ENABLE))) { // 如果文件保存在ftp服务器，则先将文件下载到本地
			FTPUtil.download(SystemConfigManager.get(Config.FTP_ADDRESS),
					Integer.parseInt(SystemConfigManager.get(Config.FTP_PORT)),
					SystemConfigManager.get(Config.FTP_USERNAME), SystemConfigManager.get(Config.FTP_PASSWORD),
					filePath, SystemConfigManager.get(Config.FILE_TEMP_PATH) + filePath);
			filePath = SystemConfigManager.get(Config.FILE_TEMP_PATH) + filePath;
		}

		File file = new File(filePath);
		byte bytes[] = FileUtil.BigFiletoByteArray(file);
		String str = new String(bytes);
		return str;
	}

	/**
	 * 描述：将字符串转换为html文件进行存储
	 * @param src
	 * @param remotePath
	 * @return 
	 * @throws IOException 
	 */
	public static String strToHtml(String src, String remotePath) throws IOException {
		byte[] bytes = src.getBytes();
		InputStream in = new ByteArrayInputStream(bytes);
		return upload(in, "html", remotePath);
	}

	/**
	 * 描述：文件上传
	 * @param file 要上传的文件
	 * @param remotePath 文件保存路径支线
	 * @return
	 * @throws IOException 
	 */
	public static String upload(InputStream in, String fileType, String remotePath) throws IOException {
		String filePath = "";
		if ("1".equals(SystemConfigManager.get(Config.FTP_ENABLE))) { // 是否启用ftp上传
			filePath = ftpUpload(in, fileType, remotePath);
		} else { // 未启用ftp的话，将文件保存在本地
			filePath = localDiskUpload(in, fileType, remotePath);
		}
		return filePath;
	}

	/**
	 * 
	 * 描述：文件上传
	 * @param file 要上传的文件
	 * @param remotePath 文件保存路径支线
	 * @return
	 * @throws IOException 
	 */
	public static String upload(MultipartFile file, String remotePath) throws IOException {
		String filePath = "";
		if ("1".equals(SystemConfigManager.get(Config.FTP_ENABLE))) { // 是否启用ftp上传
			filePath = ftpUpload(file, remotePath);
		} else { // 未启用ftp的话，将文件保存在本地
			filePath = localDiskUpload(file, remotePath);
		}
		return filePath;
	}

	/**
	 * 
	 * 描述：本地磁盘上传
	 * @Title: localDiskUpload
	 * @param 
	 * @return   
	 * @throws
	 */
	private static String localDiskUpload(InputStream in, String fileType, String remotePath) throws IOException {
		String filePath;
		filePath = SystemConfigManager.get(Config.FILE_PATH) + remotePath;
		try {
			filePath = FileUtil.fileUpload(in, fileType, filePath);
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
	private static String ftpUpload(InputStream in, String fileType, String remotePath) throws IOException {
		String filePath;
		String fileName = FileUtil.getUUIDName(fileType);
		FTPUtil.upload(SystemConfigManager.get(Config.FTP_ADDRESS),
				Integer.parseInt(SystemConfigManager.get(Config.FTP_PORT)),
				SystemConfigManager.get(Config.FTP_USERNAME), SystemConfigManager.get(Config.FTP_PASSWORD), fileName,
				remotePath, in);

		filePath = remotePath + "/" + fileName;
		return filePath;
	}

	/**
	 * 
	 * 描述：本地磁盘上传
	 * @Title: localDiskUpload
	 * @param 
	 * @return   
	 * @throws
	 */
	private static String localDiskUpload(MultipartFile file, String remotePath) throws IOException {
		String filePath;
		filePath = SystemConfigManager.get(Config.FILE_PATH) + remotePath;
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
	private static String ftpUpload(MultipartFile file, String remotePath) throws IOException {
		String filePath;
		String fileName = FileUtil.getUUIDName(file.getOriginalFilename());
		FTPUtil.upload(SystemConfigManager.get(Config.FTP_ADDRESS),
				Integer.parseInt(SystemConfigManager.get(Config.FTP_PORT)),
				SystemConfigManager.get(Config.FTP_USERNAME), SystemConfigManager.get(Config.FTP_PASSWORD), fileName,
				remotePath, file.getInputStream());

		filePath = remotePath + "/" + fileName;
		return filePath;
	}
}
