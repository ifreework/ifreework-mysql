
/**    
 * 文件名：ImageUtil.java    
 *    
 * 版本信息：    
 * 日期：2016年7月4日    
 * Copyright  Corporation 2016     
 * 版权所有    
 *    
 */
package com.ifreework.util;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.color.ColorSpace;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.apache.log4j.Logger;

import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;

import com.sun.image.codec.jpeg.JPEGCodec;  
import com.sun.image.codec.jpeg.JPEGImageEncoder;  

/**
 * 描述： 图片处理工具类
 * 
 * @author：wangyh qq735789026
 * @创建时间：2016年7月4日 下午2:12:09
 * @修改人：wangyh
 * @修改时间：2016年7月4日 下午2:12:09
 * @version 1.0
 */
@SuppressWarnings("restriction")
public class ImageUtil {
	static Logger logger = Logger.getLogger(ImageUtil.class);

	/**
	 * @Title: getImageStr
	 * @Description: TODO(将图片转化为BASE64编码处理的字符串)
	 * @param imgSrcPath
	 *            图片路径
	 * @return String @throws
	 */
	public static String getImageStr(String imgSrcPath) {
		InputStream in = null;
		byte[] data = null;

		// 读取图片字节数组
		try {
			in = new FileInputStream(imgSrcPath);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 对字节数组Base64编码
		BASE64Encoder encoder = new BASE64Encoder();

		return encoder.encode(data);// 返回Base64编码过的字节数组字符串
	}

	public static Map<String, Integer> getImgXY(String src) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		try {
			Iterator<ImageReader> iterator = ImageIO.getImageReadersByFormatName("jpg");
			ImageReader reader = (ImageReader) iterator.next();
			InputStream in;

			in = new FileInputStream(src);

			ImageInputStream iis = ImageIO.createImageInputStream(in);
			reader.setInput(iis, true);
			int imageIndex = 0;
			int width = reader.getWidth(imageIndex);
			int height = reader.getHeight(imageIndex);
			map.put("width", width);
			map.put("height", height);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("图片" + src + "不存在。", e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 
	 * @Title: generateImage @Description: TODO(将字符串经过BASE64解码后转化为图片) @param
	 *         imgStr 图片字符串 @param imgCreatePath 转化后图片将要保存的路径 @return boolean
	 *         图片是否保存成功 @throws
	 */
	public static boolean generateImage(String imgStr, String imgCreatePath) {
		if (imgStr == null) // 图像数据为空
			return false;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			byte[] b = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			OutputStream out = new FileOutputStream(imgCreatePath);
			out.write(b);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 居中裁剪文件
	 * 
	 * @param src
	 *            文件路径
	 * @param dest
	 *            裁剪后文件保存路径
	 * @param w
	 *            裁剪后的宽度
	 * @param h
	 *            裁剪后的高度
	 * @throws IOException
	 */
	public static String cutCenterImage(String src, String dest, int w, int h) throws IOException {
		String fileName = src.substring(src.lastIndexOf(".") + 1);
		dest += "/" + UUID.randomUUID().toString().replace("-", "") + "." + fileName;
		File file = FileUtil.mkdirsmy(dest);
		Iterator<ImageReader> iterator = ImageIO.getImageReadersByFormatName(fileName);
		ImageReader reader = (ImageReader) iterator.next();
		InputStream in = new FileInputStream(src);
		ImageInputStream iis = ImageIO.createImageInputStream(in);
		reader.setInput(iis, true);
		ImageReadParam param = reader.getDefaultReadParam();
		int imageIndex = 0;
		Rectangle rect = new Rectangle((reader.getWidth(imageIndex) - w) / 2, (reader.getHeight(imageIndex) - h) / 2, w,
				h);
		param.setSourceRegion(rect);
		BufferedImage bi = reader.read(0, param);
		ImageIO.write(bi, fileName, file);
		return dest;

	}

	/**
	 * 图片裁剪左上角1/4
	 * 
	 * @param src
	 *            文件路径
	 * @param dest
	 *            裁剪后文件保存位置
	 * @throws IOException
	 */
	public static String cutHalfImage(String src, String dest) throws IOException {
		String fileName = src.substring(src.lastIndexOf(".") + 1);
		dest += "/" + UUID.randomUUID().toString().replace("-", "") + "." + fileName;
		File file = FileUtil.mkdirsmy(dest);
		Iterator<ImageReader> iterator = ImageIO.getImageReadersByFormatName(fileName);
		ImageReader reader = (ImageReader) iterator.next();
		InputStream in = new FileInputStream(src);
		ImageInputStream iis = ImageIO.createImageInputStream(in);
		reader.setInput(iis, true);
		ImageReadParam param = reader.getDefaultReadParam();
		int imageIndex = 0;
		int width = reader.getWidth(imageIndex) / 2;
		int height = reader.getHeight(imageIndex) / 2;
		Rectangle rect = new Rectangle(width / 2, height / 2, width, height);
		param.setSourceRegion(rect);
		BufferedImage bi = reader.read(0, param);
		ImageIO.write(bi, fileName, file);
		return dest;
	}

	/**
	 * 图片裁剪通用接口
	 * 
	 * @param src
	 *            图片路径
	 * @param dest
	 *            裁剪后图片保存路径
	 * @param x
	 *            裁剪开始x轴坐标
	 * @param y
	 *            裁剪开始y轴坐标
	 * @param w
	 *            裁剪后图片宽度
	 * @param h
	 *            裁剪后图片高度
	 * @throws IOException
	 */
	public static String cutImage(String src, String dest, int x, int y, int w, int h) throws IOException {
		String fileName = src.substring(src.lastIndexOf(".") + 1);
		dest += "/" + UUID.randomUUID().toString().replace("-", "") + "." + fileName;
		File file = FileUtil.mkdirsmy(dest);
		Iterator<ImageReader> iterator = ImageIO.getImageReadersByFormatName(fileName);
		ImageReader reader = (ImageReader) iterator.next();
		InputStream in = new FileInputStream(src);
		ImageInputStream iis = ImageIO.createImageInputStream(in);
		reader.setInput(iis, true);
		ImageReadParam param = reader.getDefaultReadParam();
		Rectangle rect = new Rectangle(x, y, w, h);
		param.setSourceRegion(rect);
		BufferedImage bi = reader.read(0, param);
		ImageIO.write(bi,fileName, file);
		return dest;

	}

	/**
	 * 图片裁剪通用接口
	 * 
	 * @param src
	 *            图片路径
	 * @param dest
	 *            裁剪后图片保存路径
	 * @param x
	 *            裁剪开始x轴坐标
	 * @param y
	 *            裁剪开始y轴坐标
	 * @param w
	 *            裁剪后图片宽度
	 * @param h
	 *            裁剪后图片高度
	 * @throws IOException
	 */
	public static String cutImage(InputStream in, String dest, String fileName ,int x, int y, int w, int h) throws IOException {
		
		fileName = fileName.substring(fileName.lastIndexOf(".") + 1);
		dest += "/" + UUID.randomUUID().toString().replace("-", "") + "." + fileName;
		
		File file = FileUtil.mkdirsmy(dest);
		Iterator<ImageReader> iterator = ImageIO.getImageReadersByFormatName(fileName);
		ImageReader reader =  iterator.next();
		ImageInputStream iis = ImageIO.createImageInputStream(in);
		reader.setInput(iis, true);
		ImageReadParam param = reader.getDefaultReadParam();
		Rectangle rect = new Rectangle(x, y, w, h);
		param.setSourceRegion(rect);
		BufferedImage bi = reader.read(0, param);
		ImageIO.write(bi, fileName, file);
		return dest;

	}

	/**
	 * 图片缩放
	 * 
	 * @param src
	 *            图片路径
	 * @param dest
	 *            缩放后保存文件路径
	 * @param w
	 *            缩放后的宽度
	 * @param h
	 *            缩放后的高度
	 * @throws Exception
	 */
	public static String zoomImage(String src, String dest, int w, int h) throws Exception {
		double wr = 0, hr = 0;
		File srcFile = new File(src);
		dest += "/" + UUID.randomUUID().toString().replace("-", "");
		File destFile = FileUtil.mkdirsmy(dest);
		BufferedImage bufImg = ImageIO.read(srcFile);
		Image itemp = bufImg.getScaledInstance(w, h, Image.SCALE_SMOOTH);
		wr = w * 1.0 / bufImg.getWidth();
		hr = h * 1.0 / bufImg.getHeight();
		AffineTransformOp ato = new AffineTransformOp(AffineTransform.getScaleInstance(wr, hr), null);
		itemp = ato.filter(bufImg, null);
		try {
			ImageIO.write((BufferedImage) itemp, src.substring(dest.lastIndexOf(".") + 1), destFile);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return dest;
	}

	public static String zoomImageTo100(InputStream in, String dest,String fileName) {
		fileName = fileName.substring(fileName.lastIndexOf(".") + 1);
		dest += "/" + UUID.randomUUID().toString().replace("-", "") + "." + fileName;
		
		double wr = 0;
		try {
			dest += "/" + UUID.randomUUID().toString().replace("-", "");
			File destFile = FileUtil.mkdirsmy(dest);
			BufferedImage bufImg = ImageIO.read(in);
			wr = 100.0 / (bufImg.getWidth() > bufImg.getHeight() ? bufImg.getWidth() : bufImg.getHeight());

			Image itemp = bufImg.getScaledInstance((int) (bufImg.getWidth() * wr), (int) (bufImg.getHeight() * wr),
					Image.SCALE_SMOOTH);
			AffineTransformOp ato = new AffineTransformOp(AffineTransform.getScaleInstance(wr, wr), null);
			itemp = ato.filter(bufImg, null);
			ImageIO.write((BufferedImage) itemp, dest.substring(dest.lastIndexOf(".") + 1), destFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dest;
	}

	public static Map<String, Integer> getImgXY(InputStream in) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		BufferedImage bufImg;
		try {
			bufImg = ImageIO.read(in);
			int width = bufImg.getWidth();
			int height = bufImg.getHeight();
			map.put("width", width);
			map.put("height", height);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 
	 * @Title: changeImge
	 * @Description: TODO(将彩色图片转换为黑白图片)
	 * @param 
	 * @return   
	 * @throws
	 */
	public static void changeImge(File img,File offLineImg) {  
        try {  
        	FileUtil.mkdirsmy(offLineImg);
            Image image = ImageIO.read(img);  
            int srcH = image.getHeight(null);  
            int srcW = image.getWidth(null);  
            BufferedImage bufferedImage = new BufferedImage(srcW, srcH,BufferedImage.TYPE_3BYTE_BGR);  
            bufferedImage.getGraphics().drawImage(image, 0,0, srcW, srcH, null);  
            bufferedImage=new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY),null).filter (bufferedImage,null);   
            FileOutputStream fos = new FileOutputStream(offLineImg);  
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(fos);  
            encoder.encode(bufferedImage);  
            fos.close();  
            // System.out.println("转换成功...");  
        } catch (IOException e) {  
            e.printStackTrace();  
            throw new IllegalStateException("图片转换出错！", e);  
        }  
    }  
    
	/**
	 * 
	 * @Title: changeImge
	 * @Description: TODO(将彩色图片转换为黑白图片)
	 * @param 
	 * @return   
	 * @throws
	 */
	public static void changeImge(String img,String offLineImg) {  
    	File file = new File(img);
    	File offLineFile = new File(offLineImg);
    	changeImge(file, offLineFile);
    }  

	public static void main(String[] args) throws Exception {
		File file = new File("F:/图片/IMG_3141.png");
		changeImge(file,new File("F:/图片/IMG_3141-offLine.png"));
	}
}
