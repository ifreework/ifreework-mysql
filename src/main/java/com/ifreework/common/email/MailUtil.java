/**    
 * 文件名：MailHelper.java    
 *    
 * 版本信息：    
 * 日期：2014-5-9    
 * Copyright  Corporation 2014     
 * 版权所有    
 *    
 */
package com.ifreework.common.email;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.internet.MimeUtility;


/**
 * 
 * 类名称：MailHelper 类描述： 创建人：王宜华 创建时间：2014-5-9 上午10:26:22 修改人：王宜华 修改时间：2014-5-9
 * 上午10:26:22 修改备注：
 * 
 * @version
 * 
 */
public class MailUtil {
	// 根据邮件夹类型设置邮件夹的名称
	public static String folderType(int type) {
		switch (type) {
		case MailConstant.INBOX:
			return MailConstant.FOLDER_INBOX;
		case MailConstant.DRAFT:
			return MailConstant.FOLDER_DRAFT;
		case MailConstant.DELETE:
			return MailConstant.FOLDER_DELETE;
		case MailConstant.SENDED:
			return MailConstant.FOLDER_SENDED;
		case MailConstant.SPAM:
			return MailConstant.FOLDER_SPAM;
		}
		return MailConstant.FOLDER_INBOX;
	}

	// 根据邮件夹名称返回邮件夹类型
	public static int folderType(String folderName) {
		if (folderName.equals(MailConstant.FOLDER_INBOX)) {
			return MailConstant.INBOX;
		} else if (folderName.equals(MailConstant.FOLDER_DRAFT)) {
			return MailConstant.DRAFT;
		} else if (folderName.equals(MailConstant.FOLDER_DELETE)) {
			return MailConstant.DELETE;
		} else if (folderName.equals(MailConstant.FOLDER_SENDED)) {
			return MailConstant.SENDED;
		} else if (folderName.equals(MailConstant.FOLDER_SPAM)) {
			return MailConstant.SPAM;
		}
		return MailConstant.INBOX;
	}

	// 根据前台string类型的数据返回邮件夹类型
	public static String getMailFolderName(String type) {
		if ("moveDraft".equalsIgnoreCase(type)) {
			return MailConstant.FOLDER_DRAFT;
		} else if ("moveSended".equalsIgnoreCase(type)) {
			return MailConstant.FOLDER_SENDED;
		} else if ("moveDelete".equalsIgnoreCase(type)) {
			return MailConstant.FOLDER_DELETE;
		} else if ("moveInbox".equalsIgnoreCase(type)) {
			return MailConstant.FOLDER_INBOX;
		} else if ("moveSpam".equalsIgnoreCase(type)) {
			return MailConstant.FOLDER_SPAM;
		}
		return MailConstant.FOLDER_DELETE;
	}

	// 对中文进行编码
	public static String encode(String mailadd)
			throws UnsupportedEncodingException {
		Pattern p = Pattern.compile("((.*?) ?<)");// 查找所有("中文"<)的格式
		StringBuffer sb = new StringBuffer();
		Matcher matcher = p.matcher(mailadd);
		if (matcher.find()) {
			
			matcher.appendReplacement(sb,
					MimeUtility.encodeText(matcher.group(2), "UTF-8", "B")
							+ " <");
		}
		matcher.appendTail(sb);
		return sb.toString();
	}

	// 解码
	// 解码
	public static String decode(String text) throws UnsupportedEncodingException {
		if (text == null)
			return null;
		if (text.toLowerCase().startsWith("=?gb")
				|| text.toLowerCase().startsWith("=?utf-8")){
			  if(text.toUpperCase().indexOf("?Q?") > 0){
				  text = text.replaceAll("\\+",  "=20");
             }
			text = MimeUtility.decodeText(text);
		}else
			text = new String(text.getBytes("utf-8"));
		return text;
	}

}
