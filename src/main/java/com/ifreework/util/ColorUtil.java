package com.ifreework.util;

import java.util.Random;

public class ColorUtil {

	public static String getColor() {
		// 红色
		String red;
		// 绿色
		String green;
		// 蓝色
		String blue;
		// 生成随机对象
		Random random = new Random();
		// 生成红色颜色代码
		red = Integer.toHexString(random.nextInt(256)).toUpperCase();
		// 生成绿色颜色代码
		green = Integer.toHexString(random.nextInt(256)).toUpperCase();
		// 生成蓝色颜色代码
		blue = Integer.toHexString(random.nextInt(256)).toUpperCase();

		// 判断红色代码的位数
		red = red.length() == 1 ? "0" + red : red;
		// 判断绿色代码的位数
		green = green.length() == 1 ? "0" + green : green;
		// 判断蓝色代码的位数
		blue = blue.length() == 1 ? "0" + blue : blue;
		// 生成十六进制颜色值
		String color = "#" + red + green + blue;
		return color;
	}
	
	/**
	 * 随机生成rgb色码
	 * @param alpha 透明度 0 - 1之间的数字
	 * @return
	 */
    public static String getColorRGB(double alpha) {
    		int max=255;
            int min=0;
            Random random = new Random();
            int r = random.nextInt(max)%(max-min+1) + min;
            int g = random.nextInt(max)%(max-min+1) + min;
            int b = random.nextInt(max)%(max-min+1) + min;
            StringBuffer rgb = new StringBuffer();
            rgb.append("rgba(")
            	.append(r).append(",")
            	.append(g).append(",")
            	.append(b).append(",")
            	.append(alpha)
            	.append(")");
            return rgb.toString();
    }
    
    
    public static String getColorRGB(){
    	return getColorRGB(1);
    }
    public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			System.out.println(getColorRGB());
		}
	}
}
