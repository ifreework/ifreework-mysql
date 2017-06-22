package com.ifreework.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;


/**
 * @描述： 文件操作工具类，包涵读取文件大小，文件上传、下载、删除等
 * 
 * @author：wangyh qq735789026
 * @创建时间：2016年7月2日 下午3:50:24
 * @修改人：wangyh
 * @修改时间：2016年7月2日 下午3:50:24 @修改备注：
 * @version 1.0
 */
public class FileUtil {

	private static Logger log = LoggerFactory.getLogger(FileUtil.class);

	public static String getClassPath() {
		String rootPath = FileUtil.class.getResource("/").getFile().toString();
		return rootPath;
	}

	/**
	 * 
	 * 描述：获取项目根路径
	 * @Title: getRootPath
	 * @param 
	 * @return   
	 * @throws
	 */
	public static String getRootPath() {
		String rootPath = FileUtil.class.getResource("/").getFile().toString();
		rootPath = rootPath.substring(0, rootPath.indexOf("WEB-INF"));
		return rootPath;
	}

	/**
	 * 
	 * 描述：获取文件大小，单位kb，进制1024
	 * @Title: getFileSize
	 * @param 
	 * @return   
	 * @throws
	 */
	public static Double getFileSize(File file) {
		return Double.valueOf(file.length()) / 1024.000;
	}

	/**
	 * 将文件大小转换为kb,mb,gb
	 * @param size
	 * @return
	 */
	public static String convertFileSize(long size) {
        long kb = 1024;
        long mb = kb * 1024;
        long gb = mb * 1024;
 
        if (size >= gb) {
            return String.format("%.1f GB", (float) size / gb);
        } else if (size >= mb) {
            float f = (float) size / mb;
            return String.format(f > 100 ? "%.0f MB" : "%.1f MB", f);
        } else if (size >= kb) {
            float f = (float) size / kb;
            return String.format(f > 100 ? "%.0f KB" : "%.1f KB", f);
        } else
            return String.format("%d B", size);
    }
	
	/**
	 * 
	 * @Description:文件复制将文件名称转换为UUID名称
	 * @Title: getUUIDName
	 * @param  fileStr 文件名
	 * @return   
	 * @throws
	 */
	public static String getUUIDName(String fileStr){
		String fileName = UUID.randomUUID().toString().replace("-", ""); // 扩展名格式：
		if (fileStr.lastIndexOf(".") >= 0) {
			fileName += fileStr.substring(fileStr.lastIndexOf("."));
		}
		return fileName;
	}
	/**
	 * 
	 * 描述：将文件转换为字节流返回
	 * @Title: toByteArray
	 * @param 
	 * @return   
	 * @throws
	 */
	public static byte[] toByteArray(File file) throws IOException {

		if (!file.exists()) {
			throw new FileNotFoundException(file.getPath());
		}

		ByteArrayOutputStream bos = new ByteArrayOutputStream((int) file.length());
		BufferedInputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(file));
			int buf_size = 1024;
			byte[] buffer = new byte[buf_size];
			int len = 0;
			while (-1 != (len = in.read(buffer, 0, buf_size))) {
				bos.write(buffer, 0, len);
			}
			return bos.toByteArray();
		} catch (IOException e) {
			log.error("File to byte has error{}", e);
			throw e;
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			bos.close();
		}
	}

	/**
	 * 
	 * 描述：大文件转换为字节流文件，可提高处理速度
	 * @Title: BigFiletoByteArray
	 * @param 
	 * @return   
	 * @throws
	 */
	public static byte[] BigFiletoByteArray(File file) throws IOException {

		if (!file.exists()) {
			throw new FileNotFoundException(file.getPath());
		}

		FileChannel fc = null;
		RandomAccessFile rf = null;
		try {
			rf = new RandomAccessFile(file.getPath(), "r");
			fc = rf.getChannel();
			MappedByteBuffer byteBuffer = fc.map(MapMode.READ_ONLY, 0, fc.size()).load();
			byte[] result = new byte[(int) fc.size()];
			if (byteBuffer.remaining() > 0) {
				byteBuffer.get(result, 0, byteBuffer.remaining());
			}
			return result;
		} catch (IOException e) {
			log.error("File to byte has error{}", e);
			throw e;
		} finally {
			try {
				rf.close();
				fc.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * 描述：页面文件上传
	 * @Title: fileUpload
	 * @param file 页面上传上来的文件
	 * @param filePath 文件保存路径
	 * @return   文件保存路径及文件名称
	 * @throws
	 */
	public static String fileUpload(MultipartFile file, String filePath) throws IOException {
		String fileName = getUUIDName(file.getOriginalFilename());
		log.debug("Upload file save path is {}/{}",filePath,fileName);
		return copyFile(file.getInputStream(), filePath, fileName);
	}

	/**
	 * 
	 * 描述：文件上传
	 * @Title: fileUpload
	 * @param  filePath 文件路径
	 * @param  savePath 文件保存路径
	 * @return   文件保存路径及文件名称
	 * @throws IOException 
	 */
	public static String fileUpload(String filePath, String savePath) throws IOException {
		String fileName = getUUIDName(filePath);
		File file = new File(filePath);
		InputStream in = new FileInputStream(file);
		
		return copyFile(in, savePath, fileName);
	}


	/**
	 * 
	 * @Description:文件复制
	 * @Title: copyFile
	 * @param 
	 * @return   
	 * @throws
	 */
	private static String copyFile(InputStream in, String dir, String realName) throws IOException {
		File file = mkdirsmy(dir, realName);
		FileUtils.copyInputStreamToFile(in, file);
		return file.getPath();
	}

	/**
	 *判断文件是否存在，否，则创建此文件
	 * 
	 * @param dir
	 *            文件路径
	 * @param realName
	 *            文件名
	 * @throws IOException
	 */
	public static File mkdirsmy(String dir, String realName) throws IOException {
		File file = new File(dir, realName);
		return mkdirsmy(file);
	}

	/**
	 * 判断路径是否存在，否：创建此路径
	 * 
	 * @param dir
	 *            文件路径
	 * @param realName
	 *            文件名
	 * @throws IOException
	 */
	public static File mkdirsmy(String file) throws IOException {
		File f = new File(file);
		return mkdirsmy(f);
	}

	/**
	 * 判断路径是否存在，否：创建此路径
	 * 
	 * @param dir
	 *            文件路径
	 * @param realName
	 *            文件名
	 * @throws IOException
	 */
	public static File mkdirsmy(File file) throws IOException {
		if (!file.exists()) {
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			file.createNewFile();
		}
		return file;
	}

	
	/**
	 * @Title: getHtmlPicture
	 * @Description: TODO(下载网络图片上传到服务器上)
	 * @param httpUrl
	 *            图片网络地址
	 * @param filePath
	 *            图片保存路径
	 * @param myFileName
	 *            图片文件名(null时用网络图片原名)
	 * @return 返回图片名称
	 */
	public static String getHtmlPicture(String httpUrl, String filePath, String myFileName) {

		URL url; // 定义URL对象url
		BufferedInputStream in; // 定义输入字节缓冲流对象in
		FileOutputStream file; // 定义文件输出流对象file
		try {
			String fileName = null == myFileName ? httpUrl.substring(httpUrl.lastIndexOf("/")).replace("/", "")
					: myFileName; // 图片文件名(null时用网络图片原名)
			url = new URL(httpUrl); // 初始化url对象
			in = new BufferedInputStream(url.openStream()); // 初始化in对象，也就是获得url字节流
			file = new FileOutputStream(mkdirsmy(filePath, fileName));
			int t;
			while ((t = in.read()) != -1) {
				file.write(t);
			}
			file.close();
			in.close();
			return fileName;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}


	/**
	 * 
	 * 描述：文件下载
	 * @Title: fileDownload
	 * @param response
	 * @param filePath 将要下载的文件路径
	 * @param fileName 下载后保存的文件名称
	 * @return   
	 * @throws
	 */
	public static void fileDownload(HttpServletResponse response, String filePath, String fileName)
			throws IOException {

		File file = new File(filePath);
		if (!StringUtil.isEmpty(filePath) && file.exists()) {
			byte[] data = FileUtil.toByteArray(file);
			fileName = URLEncoder.encode(fileName, "UTF-8");
			response.reset();
			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
			response.addHeader("Content-Length", "" + data.length);
			response.setContentType("application/octet-stream;charset=UTF-8");
			OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
			outputStream.write(data);
			outputStream.flush();
			outputStream.close();
		} else {
			log.debug("file {} is deleted!",filePath);
			response.setContentType("text/html;charset=utf-8");
			PrintWriter prw = response.getWriter();
			prw.print("文件" + fileName + "已被删除,文件下载失败！");
			prw.flush();
			prw.close();
		}
		response.flushBuffer();
	}


	/**
	 * 
	 * 描述：文件下载，下载后使用默认名称保存
	 * @Title: fileDownload
	 * @param response
	 * @param filePath 将要下载的文件路径
	 * @param fileName 下载后保存的文件名称
	 * @return   
	 * @throws
	 */
	public static void fileDownload(HttpServletResponse response, String filePath) throws IOException {
		File file = new File(filePath);
		fileDownload(response, filePath, file.getName());
	}

	
	
	public static boolean fileDelete(String filePath){
		if(StringUtil.isEmpty(filePath)){
			return true;
		}
		File file = new File(filePath);
		return file.delete();
	}

	/**
	 * 
	 * 描述：将文件夹下全部文件压缩成zip
	 * @Title: zip
	 * @param inputFileName
	 *            要压缩的文件夹(整个完整路径)
	 * @param zipFileName
	 *            压缩后的文件(整个完整路径)
	 * @return Boolean
	 * @return   
	 * @throws
	 */
	public static Boolean zip(String inputFileName, String zipFileName) throws Exception {
		zip(new File(inputFileName),zipFileName);
		return true;
	}

	/**
	 * 
	 * 描述：将文件夹下全部文件压缩成zip
	 * @Title: zip
	 * @param inputFileName
	 *            要压缩的文件夹(整个完整路径)
	 * @param zipFileName
	 *            压缩后的文件(整个完整路径)
	 * @return Boolean
	 * @return   
	 * @throws
	 */
	public static void zip( File inputFile,String zipFileName) throws Exception {
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFileName));
		zip(out, inputFile, "");
		out.flush();
		out.close();
	}

	
	/**
	 * 
	 * 描述：将文件夹下全部文件压缩成zip下载
	 * @Title: zip
	 * @param 
	 * @return   
	 * @throws
	 */
	private static void zip(ZipOutputStream out, File f, String base) throws Exception {
		if (f.isDirectory()) {
			File[] fl = f.listFiles();
			out.putNextEntry(new ZipEntry(base + "/"));
			base = base.length() == 0 ? "" : base + "/";
			for (int i = 0; i < fl.length; i++) {
				zip(out, fl[i], base + fl[i].getName());
			}
		} else {
			out.putNextEntry(new ZipEntry(base));
			FileInputStream in = new FileInputStream(f);
			int b;
			// System.out.println(base);
			while ((b = in.read()) != -1) {
				out.write(b);
			}
			in.close();
		}
	}

	public static void main(String[] temp) {
		try {
			System.out.println(getRootPath());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}