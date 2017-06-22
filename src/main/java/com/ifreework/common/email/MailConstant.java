package com.ifreework.common.email;

/**
 * 类说明：邮件常用常量
 * 
 * @author 作者: LiuJunGuang
 * @version 创建时间：2011-8-3 上午12:38:23
 */
public class MailConstant {
	/** 收件夹 */
	public static final String FOLDER_INBOX = "INBOX";

	/** 已发送 */
	public static final String FOLDER_SENDED = "SENDED";

	/** 草稿箱 */
	public static final String FOLDER_DRAFT = "DRAFT";

	/** 已删除 */
	public static final String FOLDER_DELETE = "DELETE";

	/** 垃圾邮件夹 */
	public static final String FOLDER_SPAM = "SPAM";

	/** 邮件夹类型 */
	public static final int INBOX = 1;// 收件夹
	public static final int DRAFT = 2;// 草稿箱
	public static final int SENDED = 3;// 已发送
	public static final int DELETE = 4;// 已删除
	public static final int SPAM = 5;// 垃圾邮件

	/** 邮件列表页面每页显示的邮件数 **/
	public static final int MAXRESULT = 15;
	/** 邮件页面导航要显示的页码数量 */
	public static final int PAGE_COUNT = 8;
}
