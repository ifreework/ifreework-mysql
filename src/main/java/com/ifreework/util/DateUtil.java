package com.ifreework.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * 描述：日期处理工具类
 * 
 * @author：wangyh qq735789026 
 * 创建时间：2016年7月2日 下午3:17:10 
 * 修改人：wangyh 
 * 修改时间：2016年7月2日 下午3:17:10 
 * 修改备注：
 * @version 1.0
 */
public class DateUtil {

	private final static SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
	private final static SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
	private final static SimpleDateFormat sdfDays = new SimpleDateFormat("yyyyMMdd");
	private final static SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private final static SimpleDateFormat sdfTimes = new SimpleDateFormat("yyyyMMddHHmmss");

	/**
	 * 描述：获取当前系统时间，格式：yyyyMMddHHmmss
	 * 
	 * @return 当前系统时间，格式：yyyyMMddHHmmss
	 * @author：wangyh 
	 */
	public static String getSdfTimes() {
		return sdfTimes.format(new Date());
	}

	/**
	 * @Title: getSdfTimes
	 * @Description: TODO(格式化日期，格式：yyyyMMddHHmmss)
	 * @param
	 * @return
	 * @throws @author:
	 *             wangyh
	 */
	public static String getSdfTimes(Date date) {
		return sdfTimes.format(date);
	}

	/**
	 * 描述：获取当前年份，格式：yyyy
	 * 
	 * @return 当前年份，格式：yyyy
	 * @author：wangyh 
	 * @version 1.0
	 */
	public static String getYear() {
		return sdfYear.format(new Date());
	}

	/**
	 * 描述：获取当前日期，格式：yyyy-MM-dd
	 * 
	 * @return 当前日期，格式：yyyy-MM-dd
	 * @author：wangyh 
	 * @version 1.0
	 */
	public static String getDate() {
		return sdfDay.format(new Date());
	}
	
	
	/**
	 * 
	 * 描述：：获取当前日期，格式：format
	 * @param format 日期格式
	 * @return 当前日期，格式：format
	 * @return
	 */
	public static String getDate(String format) {
		SimpleDateFormat fmt = new SimpleDateFormat(format);
		return fmt.format(new Date());
	}
	
	/**
	 * 
	 * 描述：：格式化制定日期，格式：format
	 * @param date 需要格式化的时间
	 * @param format 日期格式
	 * @return 当前日期，格式：format
	 * @return
	 */
	public static String getDate(Date date ,String format) {
		SimpleDateFormat fmt = new SimpleDateFormat(format);
		return fmt.format(date);
	}

	/**
	 * @Title: getDate
	 * @Description: TODO(格式化日期，格式：yyyy-MM-dd)
	 * @param
	 * @return
	 * @throws @author:
	 *             wangyh
	 */
	public static String getDate(Date date) {
		return sdfDay.format(date);
	}

	/**
	 * 描述：获取当前日期，格式：yyyyMMdd
	 * 
	 * @return 当前日期，格式：yyyyMMdd
	 * Throwing 
	 * @version 1.0
	 */
	public static String getDays() {
		return sdfDays.format(new Date());
	}

	/**
	 * 描述：获取当前系统时间，格式：yyyy-MM-dd HH:mm:ss
	 * 
	 * @return 当前系统时间，格式：yyyy-MM-dd HH:mm:ss
	 * Throwing qq735789026 创建时间：2016年7月2日 下午3:21:42 修改人：wangyh
	 *                修改时间：2016年7月2日 下午3:21:42 修改备注：
	 * @version 1.0
	 */
	public static String getDateTime() {
		return sdfTime.format(new Date());
	}

	/**
	 * @Title: getDateTime
	 * @Description: TODO(格式化日期，格式：yyyy-MM-dd HH:mm:ss)
	 * @param
	 * @return
	 * @throws @author:
	 *             wangyh
	 */
	public static String getDateTime(Date date) {
		return sdfTime.format(date);
	}

	/**
	 * @Title: compareDate
	 * @Description: TODO(日期比较，如果s>=e 返回true 否则返回false)
	 * @param s
	 * @param e
	 * @return int
	 * @throws @author
	 *             wangyh
	 */
	public static int compareDate(String s, String e) {
		if (fomatDate(s) == null || fomatDate(e) == null) {
			return -1;
		}
		return fomatDate(s).getTime() > fomatDate(e).getTime() ? 1
				: fomatDate(s).getTime() == fomatDate(e).getTime() ? 0 : -1;
	}

	/**
	 * @Title: fomatDate
	 * @Description: TODO(字符串转为日期，格式：yyyy-MM-dd)
	 * @param String(yyyy-MM-dd)
	 * @return Date
	 * @throws @author: wangyh
	 */
	public static Date fomatDate(String date) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return fmt.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

}
