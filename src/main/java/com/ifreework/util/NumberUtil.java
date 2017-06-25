package com.ifreework.util;

import java.util.Random;

/**
 * 数字处理工具类
 * @author wangyh
 *
 */
public class NumberUtil {
	/**
	 * 随机生成大于min，小于max的正整数
	 * @param min 最小值
	 * @param max 最大值
	 * @return
	 */
    public static int random(int min,int max) {
        Random random = new Random();
        int s = random.nextInt(max)%(max-min+1) + min;
        return s;
    }
}
