package com.ifreework.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QRCodeUtil {

	// 图片宽度的一般
	private static final int FRAME_WIDTH = 2;

	// 二维码写码器
	private static MultiFormatWriter mutiWriter = new MultiFormatWriter();

	
	/**
	 * 
	 * 描述：将文字信息保存为二维码
	 * @param content 文字信息
	 * @param width 二维码宽度
	 * @param height 二维码高度
	 * @return
	 * @throws IOException
	 * @throws WriterException 
	 * @return
	 */
	public static BufferedImage encode(String content, int width, int height) throws IOException, WriterException {
		byte[] imagesStream = null;
		ByteArrayOutputStream os = null;
		try {
			String qrcodeFormat = "png";
			Map<EncodeHintType, String> hints = new HashMap<EncodeHintType, String>();
			hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
			BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
			os = new ByteArrayOutputStream();

			MatrixToImageWriter.writeToStream(bitMatrix, qrcodeFormat, os);
			imagesStream = os.toByteArray();
			ByteArrayInputStream in = new ByteArrayInputStream(imagesStream);
			BufferedImage bi = ImageIO.read(in);
			return bi;
		} finally {
			if (os != null) {
				os.close();
			}
		}
	}

	/**
	 * 
	 * 描述：将文字信息生成带图片的二维码图片
	 * @param content 将要生成二维码的内容
	 * @param width 生成后二维码宽度
	 * @param height 生成后二维码高度
	 * @param srcImagePath 要插入二维码的图片地址
	 * @param destImagePath 生成的图片保存地址
	 * @return
	 * @throws WriterException 
	 * @throws IOException 
	 */
	public static void encode(String content, int width, int height, String srcImagePath, String destImagePath) throws IOException, WriterException {
		ImageIO.write(encode(content, width, height, srcImagePath), "jpg", new File(destImagePath));
	}
	
	public static void encode(String content, int width, int height, InputStream in, String destImagePath) throws IOException, WriterException {
		ImageIO.write(encode(content, width, height, in), "jpg", new File(destImagePath));
	}

	/**
	 * 
	 * 描述：将文字信息生成带图片的二维码图片
	 * @param content 将要生成二维码的内容
	 * @param width 生成后二维码宽度
	 * @param height 生成后二维码高度
	 * @param srcImagePath 要插入二维码的图片地址
	 * @return 二维码图片
	 * @throws WriterException
	 * @throws IOException 
	 * @return
	 */
	public static BufferedImage encode(String content, int width, int height, String srcImagePath)
			throws WriterException, IOException {
		int image_width = width / 3;
		int image_height = height / 3;
		int image_half_width = image_width / 2;

		// 读取源图像 ,并进行压缩，压缩大小为
		BufferedImage scaleImage = ImageUtil.scale(srcImagePath, image_width, image_height, true);
		return genBarcode(content, width, height, image_width, image_height, image_half_width, scaleImage);
	}

	/**
	 * 
	 * 描述：将文字信息生成带图片的二维码图片
	 * @param content 将要生成二维码的内容
	 * @param width 生成后二维码宽度
	 * @param height 生成后二维码高度
	 * @param srcImagePath 要插入二维码的图片地址
	 * @return 二维码图片
	 * @throws WriterException
	 * @throws IOException 
	 * @return
	 */
	public static BufferedImage encode(String content, int width, int height, InputStream in)
			throws WriterException, IOException {
		int image_width = width / 3;
		int image_height = height / 3;
		int image_half_width = image_width / 2;

		// 读取源图像 ,并进行压缩，压缩大小为
		BufferedImage scaleImage = ImageUtil.scale(in, image_width, image_height, true);
		return genBarcode(content, width, height, image_width, image_height, image_half_width, scaleImage);
	}

	private static BufferedImage genBarcode(String content, int width, int height, int image_width, int image_height,
			int image_half_width, BufferedImage scaleImage) throws WriterException {
		int[][] srcPixels = new int[image_width][image_height];
		for (int i = 0; i < scaleImage.getWidth(); i++) {
			for (int j = 0; j < scaleImage.getHeight(); j++) {
				srcPixels[i][j] = scaleImage.getRGB(i, j);
			}
		}

		Map<EncodeHintType, Object> hint = new HashMap<EncodeHintType, Object>();
		hint.put(EncodeHintType.CHARACTER_SET, "utf-8");
		hint.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		// 生成二维码
		BitMatrix matrix = mutiWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hint);

		// 二维矩阵转为一维像素数组
		int halfW = matrix.getWidth() / 2;
		int halfH = matrix.getHeight() / 2;
		int[] pixels = new int[width * height];

		for (int y = 0; y < matrix.getHeight(); y++) {
			for (int x = 0; x < matrix.getWidth(); x++) {
				// 读取图片
				if (x > halfW - image_half_width && x < halfW + image_half_width && y > halfH - image_half_width
						&& y < halfH + image_half_width) {
					pixels[y * width + x] = srcPixels[x - halfW + image_half_width][y - halfH + image_half_width];
				}
				// 在图片四周形成边框
				else if ((x > halfW - image_half_width - FRAME_WIDTH && x < halfW - image_half_width + FRAME_WIDTH
						&& y > halfH - image_half_width - FRAME_WIDTH && y < halfH + image_half_width + FRAME_WIDTH)
						|| (x > halfW + image_half_width - FRAME_WIDTH && x < halfW + image_half_width + FRAME_WIDTH
								&& y > halfH - image_half_width - FRAME_WIDTH
								&& y < halfH + image_half_width + FRAME_WIDTH)
						|| (x > halfW - image_half_width - FRAME_WIDTH && x < halfW + image_half_width + FRAME_WIDTH
								&& y > halfH - image_half_width - FRAME_WIDTH
								&& y < halfH - image_half_width + FRAME_WIDTH)
						|| (x > halfW - image_half_width - FRAME_WIDTH && x < halfW + image_half_width + FRAME_WIDTH
								&& y > halfH + image_half_width - FRAME_WIDTH
								&& y < halfH + image_half_width + FRAME_WIDTH)) {
					pixels[y * width + x] = 0xfffffff;
				} else {
					// 此处可以修改二维码的颜色，可以分别制定二维码和背景的颜色；
					pixels[y * width + x] = matrix.get(x, y) ? 0xff000000 : 0xfffffff;
				}
			}
		}

		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		image.getRaster().setDataElements(0, 0, width, height, pixels);

		return image;
	}

}
