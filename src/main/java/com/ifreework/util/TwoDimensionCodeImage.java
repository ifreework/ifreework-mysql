package com.ifreework.util;

import java.awt.image.BufferedImage;

import jp.sourceforge.qrcode.data.QRCodeImage;

/**
 * 
 * 描述：    
 * @author：wangyh qq735789026  
 * @创建时间：2016年7月4日 下午3:40:23    
 * @修改人：wangyh    
 * @修改时间：2016年7月4日 下午3:40:23    
 * @version 1.0
 */
public class TwoDimensionCodeImage implements QRCodeImage {

	BufferedImage bufImg;

	public TwoDimensionCodeImage(BufferedImage bufImg) {
		this.bufImg = bufImg;
	}

	@Override
	public int getHeight() {
		return bufImg.getHeight();
	}

	@Override
	public int getPixel(int x, int y) {
		return bufImg.getRGB(x, y);
	}

	@Override
	public int getWidth() {
		return bufImg.getWidth();
	}

}
