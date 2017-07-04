
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

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.color.ColorSpace;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.apache.log4j.Logger;

import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;

/**
 * 描述： 图片处理工具类
 * 
 * @author：wangyh qq735789026
 * @创建时间：2016年7月4日 下午2:12:09
 * @修改人：wangyh
 * @修改时间：2016年7月4日 下午2:12:09
 * @version 1.0
 */
public class ImageUtil {
	static Logger logger = Logger.getLogger(ImageUtil.class);

	/**
	 * 
	 * 描述：从URL地址下载图片
	 * @param urlStr
	 * @return
	 * @throws IOException 
	 * @return
	 */
	public static BufferedImage readImageByUrl(String urlStr) throws IOException {
		URL url = new URL(urlStr);
		BufferedImage image = ImageIO.read(url);
		return image;
	}

	/**
	 * 
	 * 描述：将BufferedImage转换为输入流
	 * @param bi BufferedImage
	 * @return
	 * @throws IOException 
	 * @return
	 */
	public static InputStream bufferedImageToInputStream(BufferedImage bi) throws IOException {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		ImageIO.write(bi, "png", os);
		InputStream is = new ByteArrayInputStream(os.toByteArray());
		return is;
	}

	/**
	 * 
	 * 描述：将图片转化为base64编码的字符串
	 * @param imgSrcPath 图片路径
	 * @return  base64编码后的字符串
	 * @throws IOException 
	 */
	public static String encode(String imgSrcPath) throws IOException {
		InputStream in = null;
		in = new FileInputStream(imgSrcPath);
		return encode(in);// 返回Base64编码过的字节数组字符串
	}

	/**
	 * 
	 * 描述：将图片转化为base64编码的字符串
	 * @param imgSrcPath 图片路径
	 * @return  base64编码后的字符串
	 * @throws IOException 
	 */
	public static String encode(InputStream in) throws IOException {
		byte[] data = null;

		data = new byte[in.available()];
		in.read(data);
		in.close();
		// 对字节数组Base64编码
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);// 返回Base64编码过的字节数组字符串
	}

	/**
	 * 
	 * 描述：将base64编码的字符串转换为图片
	 * @param imgStr 经过base64编码后的字符串
	 * @return 
	 * @throws IOException 
	 */
	public static BufferedImage decode(String imgStr) throws IOException {
		BASE64Decoder decoder = new BASE64Decoder();
		// Base64解码
		byte[] b = decoder.decodeBuffer(imgStr);
		for (int i = 0; i < b.length; ++i) {
			if (b[i] < 0) {// 调整异常数据
				b[i] += 256;
			}
		}
		InputStream in = new ByteArrayInputStream(b);
		BufferedImage bi = ImageIO.read(in);
		return bi;
	}

	/**
	 * 图片裁剪通用接口
	 * 
	 * @param src
	 *            图片路径
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
	public static BufferedImage cutImage(String src, int x, int y, int w, int h) throws IOException {
		String imageType = src.substring(src.lastIndexOf(".") + 1);
		InputStream in = new FileInputStream(new File(src));
		return cutImage(in, imageType, x, y, w, h);
	}

	/**
	 * 图片裁剪通用接口
	 * 
	 * @param in
	 *            图片文件流
	 * @param imageType
	 *            文件类型 jpg|png|bpm等
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
	public static BufferedImage cutImage(InputStream in, String imageType, int x, int y, int w, int h)
			throws IOException {

		Iterator<ImageReader> iterator = ImageIO.getImageReadersByFormatName(imageType);
		ImageReader reader = iterator.next();
		ImageInputStream iis = ImageIO.createImageInputStream(in);
		reader.setInput(iis, true);
		ImageReadParam param = reader.getDefaultReadParam();
		Rectangle rect = new Rectangle(x, y, w, h);
		param.setSourceRegion(rect);
		BufferedImage bi = reader.read(0, param);
		return bi;

	}

	/**
	 * 描述：获取图片的宽度和高度，单位px
	 * @param in 图片流文件
	 * @return  {width:int 宽度，heiight:int 高度}
	 * @throws IOException 
	 */
	public static Map<String, Integer> getImgXY(InputStream in) throws IOException {
		Map<String, Integer> map = new HashMap<String, Integer>();
		BufferedImage bufImg;
		bufImg = ImageIO.read(in);
		int width = bufImg.getWidth();
		int height = bufImg.getHeight();
		map.put("width", width);
		map.put("height", height);
		return map;
	}

	/**
	 * 描述：将图片转换为灰白色彩输出
	 * @param img 转换前图片
	 * @param offLineImg 转换后图片
	 * @return
	 * @throws IOException 
	 */
	public static BufferedImage changeImge(File img) throws IOException {
		Image image = ImageIO.read(img);
		int srcH = image.getHeight(null);
		int srcW = image.getWidth(null);
		BufferedImage bufferedImage = new BufferedImage(srcW, srcH, BufferedImage.TYPE_3BYTE_BGR);
		bufferedImage.getGraphics().drawImage(image, 0, 0, srcW, srcH, null);
		bufferedImage = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null).filter(bufferedImage,
				null);
		return bufferedImage;
		// System.out.println("转换成功...");
	}

	/**
	 * 
	 * 描述：将图片转换为灰白色彩输出
	 * @param img 转换前图片路径
	 * @param offLineImg 转换后图片保存路径
	 * @return
	 * @throws IOException 
	 */
	public static BufferedImage changeImge(String img) throws IOException {
		File file = new File(img);
		return changeImge(file);
	}

	/** 
	 * 把传入的原始图像按高度和宽度进行缩放，生成符合要求的图标 
	 *  
	 * @param in 
	 *            源文件流
	 * @param width 
	 *            目标高度 
	 * @param height 
	 *            目标宽度 
	 * @param hasFiller  
	 *            比例不对时是否需要补白：true为补白; false为不补白; 
	 * @throws IOException 
	 */
	public static BufferedImage scale(InputStream in, int width, int height, boolean hasFiller) throws IOException {
		BufferedImage srcImage = ImageIO.read(in);
		return scale(srcImage, height, width, hasFiller);
	}

	/** 
	 * 把传入的原始图像按高度和宽度进行缩放，生成符合要求的图标 
	 *  
	 * @param srcImageFile 
	 *            源文件地址 
	 * @param width 
	 *            目标高度 
	 * @param height 
	 *            目标宽度 
	 * @param hasFiller  
	 *            比例不对时是否需要补白：true为补白; false为不补白; 
	 * @throws IOException 
	 */
	public static BufferedImage scale(String srcImageFile, int width, int height, boolean hasFiller)
			throws IOException {
		File file = new File(srcImageFile);
		BufferedImage srcImage = ImageIO.read(file);
		return scale(srcImage, height, width, hasFiller);
	}

	/**
	 * 
	 * 描述：图片压缩
	 * @param height 目标高度
	 * @param width 目标宽度
	 * @param hasFiller 比例不对时是否需要补白：true为补白; false为不补白; 
	 * @param srcImage 需要压缩的图片
	 * @return 
	 * @return
	 */
	private static BufferedImage scale(BufferedImage srcImage, int width, int height, boolean hasFiller) {
		double ratio = 0.0; // 缩放比例
		Image destImage = srcImage.getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH);

		// 计算比例
		if ((srcImage.getHeight() > height) || (srcImage.getWidth() > width)) {
			if (srcImage.getHeight() > srcImage.getWidth()) {
				ratio = (new Integer(height)).doubleValue() / srcImage.getHeight();
			} else {
				ratio = (new Integer(width)).doubleValue() / srcImage.getWidth();
			}
			AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(ratio, ratio), null);
			destImage = op.filter(srcImage, null);
		}
		if (hasFiller) {// 补白
			BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics2D graphic = image.createGraphics();
			graphic.setColor(Color.white);
			graphic.fillRect(0, 0, width, height);
			if (width == destImage.getWidth(null))
				graphic.drawImage(destImage, 0, (height - destImage.getHeight(null)) / 2, destImage.getWidth(null),
						destImage.getHeight(null), Color.white, null);
			else
				graphic.drawImage(destImage, (width - destImage.getWidth(null)) / 2, 0, destImage.getWidth(null),
						destImage.getHeight(null), Color.white, null);
			graphic.dispose();
			destImage = image;
		}
		return (BufferedImage) destImage;
	}

	/**
	 * 描述：添加图片水印
	 * @param targetImg 原图片路径，如：C://myPictrue//1.jpg
	 * @param waterImg 水印图片路径，如：C://myPictrue//logo.png
	 * @param x 水印图片距离目标图片左侧的偏移量，如果x<0, 则在正中间
	 * @param y 水印图片距离目标图片上侧的偏移量，如果y<0, 则在正中间
	 * @param alpha 透明度(0.0 -- 1.0, 0.0为完全透明，1.0为完全不透明)
	 * @throws IOException 
	 */
	public final static BufferedImage pressImage(String targetImg, String waterImg, int x, int y, float alpha)
			throws IOException {
		Image waterImage = ImageIO.read(new File(waterImg)); // 水印文件
		return pressImage(targetImg, waterImage, x, y, alpha);
	}

	/**
	 * 描述：添加图片水印
	 * @param targetImg 原图片路径，如：C://myPictrue//1.jpg
	 * @param in 水印图片流
	 * @param x 水印图片距离目标图片左侧的偏移量，如果x<0, 则在正中间
	 * @param y 水印图片距离目标图片上侧的偏移量，如果y<0, 则在正中间
	 * @param alpha 透明度(0.0 -- 1.0, 0.0为完全透明，1.0为完全不透明)
	 * @throws IOException 
	 */
	public final static BufferedImage pressImage(String targetImg, InputStream in, int x, int y, float alpha)
			throws IOException {
		Image waterImage = ImageIO.read(in); // 水印文件
		return pressImage(targetImg, waterImage, x, y, alpha);
	}

	/**
	 * 
	 * 描述：
	 * @param targetImg 原文件
	 * @param x 水印图片距离目标图片左侧的偏移量，如果x<0, 则在正中间
	 * @param y 水印图片距离目标图片上侧的偏移量，如果y<0, 则在正中间
	 * @param alpha 透明度(0.0 -- 1.0, 0.0为完全透明，1.0为完全不透明)
	 * @param waterImage 水印文件
	 * @return
	 * @throws IOException 
	 * @return
	 */
	public static BufferedImage pressImage(String targetImg, Image waterImage, int x, int y, float alpha)
			throws IOException {
		File file = new File(targetImg);
		Image image = ImageIO.read(file);
		int width = image.getWidth(null);
		int height = image.getHeight(null);
		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = bufferedImage.createGraphics();
		g.drawImage(image, 0, 0, width, height, null);

		int width_1 = waterImage.getWidth(null);
		int height_1 = waterImage.getHeight(null);
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));

		int widthDiff = width - width_1;
		int heightDiff = height - height_1;
		if (x < 0) {
			x = widthDiff / 2;
		} else if (x > widthDiff) {
			x = widthDiff;
		}
		if (y < 0) {
			y = heightDiff / 2;
		} else if (y > heightDiff) {
			y = heightDiff;
		}
		g.drawImage(waterImage, x, y, width_1, height_1, null); // 水印文件结束
		g.dispose();
		return bufferedImage;
	}
	
	public static BufferedImage pressText(String targetImg, String pressText,int x, int y, float alpha) throws IOException  {
		return pressText(targetImg, pressText, Font.SANS_SERIF, Font.BOLD, 16, Color.BLACK, x, y, alpha);
	}

	/**
	 * 添加文字水印
	 * @param targetImg 目标图片路径，如：C://myPictrue//1.jpg
	 * @param pressText 水印文字， 如：中国证券网
	 * @param fontName 字体名称，    如：宋体
	 * @param fontStyle 字体样式，如：粗体和斜体(Font.BOLD|Font.ITALIC)
	 * @param fontSize 字体大小，单位为像素
	 * @param color 字体颜色
	 * @param x 水印文字距离目标图片左侧的偏移量，如果x<0, 则在正中间
	 * @param y 水印文字距离目标图片上侧的偏移量，如果y<0, 则在正中间
	 * @param alpha 透明度(0.0 -- 1.0, 0.0为完全透明，1.0为完全不透明)
	 * @return 
	 */
	public static BufferedImage pressText(String targetImg, String pressText, String fontName, int fontStyle,
			int fontSize, Color color, int x, int y, float alpha) throws IOException  {
		File file = new File(targetImg);

		Image image = ImageIO.read(file);
		int width = image.getWidth(null);
		int height = image.getHeight(null);
		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = bufferedImage.createGraphics();
		g.drawImage(image, 0, 0, width, height, null);
		g.setFont(new Font(fontName, fontStyle, fontSize));
		g.setColor(color);
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));

		int width_1 = fontSize * getLength(pressText);
		int height_1 = fontSize;
		int widthDiff = width - width_1;
		int heightDiff = height - height_1;
		if (x < 0) {
			x = widthDiff / 2;
		} else if (x > widthDiff) {
			x = widthDiff;
		}
		if (y < 0) {
			y = heightDiff / 2;
		} else if (y > heightDiff) {
			y = heightDiff;
		}

		g.drawString(pressText, x, y + height_1);
		g.dispose();
		return bufferedImage;
	}

	/**
	 * 获取字符长度，一个汉字作为 1 个字符, 一个英文字母作为 0.5 个字符
	 * @param text
	 * @return 字符长度，如：text="中国",返回 2；text="test",返回 2；text="中国ABC",返回 4.
	 */
	private static int getLength(String text) {
		int textLength = text.length();
		int length = textLength;
		for (int i = 0; i < textLength; i++) {
			if (String.valueOf(text.charAt(i)).getBytes().length > 1) {
				length++;
			}
		}
		return (length % 2 == 0) ? length / 2 : length / 2 + 1;
	}

	public static void main(String[] args) throws IOException {
		System.out.println(encode("D:\\card-bg1.JPG"));
	}
}
