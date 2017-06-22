package com.ifreework.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;

public class FTPUtil {

	private static Logger log = Logger.getLogger(FTPUtil.class);

	/**
	 * Description: 向FTP服务器上传文件到根目录，并以默认的文件名进行保存
	 * 
	 * @param url
	 *            FTP服务器hostname
	 * @param port
	 *            FTP服务器端口
	 * @param username
	 *            FTP登录账号
	 * @param password
	 *            FTP登录密码
	 * @param file
	 *            上传到FTP服务器上的文件
	 * @return 成功返回true，否则返回false
	 */
	public static boolean upload(String url, int port, String username, String password, String filePath) {
		File file = new File(filePath);
		if (!file.exists()) { // 如果文件不存在，直接返回上传失败
			log.error(filePath + "is not found!");
			return false;
		}
		try {
			FileInputStream in = new FileInputStream(file);
			return upload(url, port, username, password, file.getName(), "", in);
		} catch (FileNotFoundException e) {
			log.error(e);
			return false;
		}
	}

	/**
	 * Description: 向FTP服务器上传文件到根目录，并保存到remotePath中
	 * 
	 * @param url
	 *            FTP服务器hostname
	 * @param port
	 *            FTP服务器端口
	 * @param username
	 *            FTP登录账号
	 * @param password
	 *            FTP登录密码
	 * @param filePath
	 *            上传到FTP服务器上的文件
	 * @param fileName
	 *            上传到FTP服务器上的文件名
	 * @return 成功返回true，否则返回false
	 */
	public static boolean upload(String url, int port, String username, String password, String filePath,
			String fileName) {
		File file = new File(filePath);
		if (!file.exists()) { // 如果文件不存在，直接返回上传失败
			log.error(filePath + "is not found!");
			return false;
		}
		try {
			FileInputStream in = new FileInputStream(file);
			return upload(url, port, username, password, fileName, "", in);
		} catch (FileNotFoundException e) {
			log.error(e);
			return false;
		}
	}

	/**
	 * Description: 向FTP服务器上传文件到根目录，并保存到remotePath中
	 * 
	 * @param url
	 *            FTP服务器hostname
	 * @param port
	 *            FTP服务器端口
	 * @param username
	 *            FTP登录账号
	 * @param password
	 *            FTP登录密码
	 * @param filePath
	 *            上传到FTP服务器上的文件
	 * @param fileName
	 *            上传到FTP服务器上的文件名
	 * @param remotePath
	 *            FTP服务器保存路径
	 * @return 成功返回true，否则返回false
	 */
	public static boolean upload(String url, int port, String username, String password, String filePath,
			String fileName, String remotePath) {
		File file = new File(filePath);
		if (!file.exists()) { // 如果文件不存在，直接返回上传失败
			log.error(filePath + "is not found!");
			return false;
		}
		try {
			FileInputStream in = new FileInputStream(file);
			return upload(url, port, username, password, fileName, remotePath, in);
		} catch (FileNotFoundException e) {
			log.error(e);
			return false;
		}
	}

	/**
	 * Description: 向FTP服务器上传文件到根目录，并保存到remotePath中
	 * 
	 * @param url
	 *            FTP服务器hostname
	 * @param port
	 *            FTP服务器端口
	 * @param username
	 *            FTP登录账号
	 * @param password
	 *            FTP登录密码
	 * @param fileName
	 *            上传到FTP服务器上的文件名
	 * @param remotePath
	 *            FTP服务器保存路径
	 * @param in
	 *            上传的文件流
	 * @return 成功返回true，否则返回false
	 */
	public static boolean upload(String url, int port, String username, String password, String fileName,
			String remotePath, InputStream in) {
		boolean status = false;
		FTPClient ftp = new FTPClient();
		try {
			if (validate(url, port, username, password, remotePath, ftp, true)) {
				ftp.setFileType(FTP.BINARY_FILE_TYPE);
				status = ftp.storeFile(fileName, in);
				log.debug(status ? (fileName + " upload Success!") : (fileName + " upload Failed!"));
			}
			ftp.logout();
			return status;
		} catch (Exception e) {
			if (e instanceof ConnectException) {
				log.error("Remote " + url + ":" + port + " can't connect!");
			}
			log.error(e);
			return false;
		} finally {
			if (ftp.isConnected()) {
				try {
					in.close();
					ftp.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
	}

	/**
	 * 
	 * 描述：验证ftp是否登录成功
	 * @Title: validate
	 * @param 
	 * @return   
	 * @throws
	 */
	private static boolean validate(String url, int port, String username, String password, String remotePath,
			FTPClient ftp, boolean createDir) throws SocketException, IOException {
		int reply; // ftp状态码
		ftp.connect(url, port);// 连接FTP服务器,如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器

		if (!ftp.login(username, password)) {// 通过用户名密码登录
			log.error(username + " or " + password + "is wrong!");
			return false;
		}

		reply = ftp.getReplyCode();
		if (!FTPReply.isPositiveCompletion(reply)) {// 验证ftp状态码是否正确
			log.error("FTP error reply is " + reply);
			ftp.disconnect();
			return false;
		}

		if (!changeWorkingDirectory(remotePath, ftp, createDir)) {
			log.error("RemotePath " + remotePath + " is error!");
			return false;
		}
		return true;
	}

	/**
	 * 
	 * 描述：创建文件保存路径 @Title: changeWorkingDirectory @param remotePath
	 * ftp服务器端路径 @param ftp ftp服务 @param createDir
	 * 如果路径不存在，是否需要创建 @return @throws
	 */
	private static boolean changeWorkingDirectory(String remotePath, FTPClient ftp, boolean createDir)
			throws IOException {
		if (!StringUtil.isEmpty(remotePath)) {
			File remotePathFile = new File(remotePath);
			String path = remotePathFile.getPath();
			for (String dir : path.split("\\\\")) {
				if (!StringUtil.isEmpty(dir)) {
					if (createDir) {
						ftp.makeDirectory(dir);
					}
					if (!ftp.changeWorkingDirectory(dir)) {
						return false;
					}
				}
			}
		}
		return true;
	}

	/**
	 * Description: 从FTP服务器下载文件 @Version. Jul , :: PM by
	 * 崔红保（cuihongbao@d-heaven.com）创建
	 * 
	 * @param url
	 *            FTP服务器hostname
	 * @param port
	 *            FTP服务器端口
	 * @param username
	 *            FTP登录账号
	 * @param password
	 *            FTP登录密码
	 * @param remotePath
	 *            FTP服务器上的相对路径
	 * @param fileName
	 *            要下载的文件名
	 * @param localPath
	 *            下载后保存到本地的路径
	 * @return
	 */
	public static boolean download(String url, int port, String username, String password, String filePath,
			String localPath) {
		File file = new File(filePath);
		String fileName = file.getName();
		if (fileName.indexOf(".") < 0) { // 如果不是文件，则直接返回下载失败
			log.error(filePath + " is not found!");
			return false;
		}
		return download(url, port, username, password, file.getParent(), file.getName(), localPath);
	}

	/**
	 * Description: 从FTP服务器下载文件 @Version. Jul , :: PM by
	 * 崔红保（cuihongbao@d-heaven.com）创建
	 * 
	 * @param url
	 *            FTP服务器hostname
	 * @param port
	 *            FTP服务器端口
	 * @param username
	 *            FTP登录账号
	 * @param password
	 *            FTP登录密码
	 * @param remotePath
	 *            FTP服务器上的相对路径
	 * @param fileName
	 *            要下载的文件名
	 * @param localPath
	 *            下载后保存到本地的路径
	 * @return
	 */
	public static boolean download(String url, int port, String username, String password, String remotePath,
			String fileName, String localPath) {
		boolean status = false;
		FTPClient ftp = new FTPClient();
		try {
			if (validate(url, port, username, password, remotePath, ftp, false)) {
				File localFile = new File(localPath + "/" + fileName);
				OutputStream is = new FileOutputStream(localFile);
				status = ftp.retrieveFile(fileName, is);
				log.debug(status ? (fileName + " download Success!") : (fileName + " download Failed!"));
				is.close();
			}
			ftp.logout();
			return status;
		} catch (Exception e) {
			if (e instanceof ConnectException) {
				log.error("Remote " + url + ":" + port + " can't connect!");
			}
			log.error(e);
			return false;
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
	}
	
	
	/**
	 * 
	 * 描述：删除ftp文件
	 * @Title: delete
	 * @param 
	 * @return   
	 * @throws
	 */
	public static boolean delete(String url, int port, String username, String password, 
			String filePath) {
		File file = new File(filePath);
		return delete(url, port, username, password, file.getParent(), file.getName());
	}
	/**
	 * 
	 * 描述：删除ftp文件
	 * @Title: delete
	 * @param 
	 * @return   
	 * @throws
	 */
	public static boolean delete(String url, int port, String username, String password, String remotePath,
			String fileName) {
		boolean status = false;
		FTPClient ftp = new FTPClient();
		try {
			if (validate(url, port, username, password, remotePath, ftp, false)) {
				status = ftp.deleteFile(fileName);
				log.debug(status ? (fileName + " delete Success!") : (fileName + " delete Failed!"));
			}
			ftp.logout();
			return status;
		} catch (Exception e) {
			if (e instanceof ConnectException) {
				log.error("Remote " + url + ":" + port + " can't connect!");
			}
			log.error(e);
			return false;
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
	}

	public static void main(String[] args) {
		boolean flag = upload("192.168.116.129", 21, "ftp", "1qaz@wsx", "D:\\test.txt", "t1.txt", "z1");
		System.out.println(flag);
	}
}