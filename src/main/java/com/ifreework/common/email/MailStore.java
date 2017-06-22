package com.ifreework.common.email;

import java.util.Properties;

import javax.mail.Session;
import javax.mail.Store;

import org.apache.log4j.Logger;

import com.ifreework.common.email.entity.MailAuth;
import com.ifreework.common.email.entity.MailConfig;
import com.ifreework.common.manager.SysTemConfigManager;
import com.ifreework.entity.system.Config;



/**    
 * 获取链接邮件服务器store   
 * 类名称：MailStore    
 * 类描述：    
 * 创建人：王宜华    
 * 创建时间：2014-5-9 上午9:10:53    
 * 修改人：王宜华    
 * 修改时间：2014-5-9 上午9:10:53    
 * 修改备注：    
 * @version     
 *     
 */
public class MailStore {
	private static Logger log = Logger.getLogger(MailStore.class);
	
	private MailConfig mailConfig = null;
	
	public MailStore(MailConfig mailConfig) {
		super();
		this.mailConfig = mailConfig;
	}

	/**
	 * 默认的从数据库中读取邮件信息，此处获取的是服务器公共邮箱
	 * getInstanse(这里用一句话描述这个方法的作用)     
	 * @return
	 * @throws Exception 参数中文名
	 * @return 列出方法的返回值列表（如果需要返回值的话）
	 */
	public static MailStore getInstanse() {
		String smtpHost = SysTemConfigManager.get(Config.MAIL_SMTP_HOST);
		String smtpPort = SysTemConfigManager.get(Config.MAIL_SMTP_PORT);
		String smtpAuth = SysTemConfigManager.get(Config.MAIL_SMTP_AUTH);
		String storeProtocol = SysTemConfigManager.get(Config.MAIL_STORE_PROTOCOL);
		String pop3Host = SysTemConfigManager.get(Config.MAIL_STORE_HOST);
		String pop3Port = SysTemConfigManager.get(Config.MAIL_STORE_PORT);
		String userName =  SysTemConfigManager.get(Config.MAIL_USER);
		String password =  SysTemConfigManager.get(Config.MAIL_PASSWORD);
		return new MailStore(new MailConfig(smtpHost,smtpPort, smtpAuth, storeProtocol,
				pop3Host, pop3Port, new MailAuth( userName, password)));
	}
	   

	/**
	 * 通过用户配置的用户名和密码链接邮件服务器，其他链接信息为从数据库中读取的默认信息
	 * getInstanse(这里用一句话描述这个方法的作用)     
	 * @return
	 * @throws Exception 参数中文名
	 * @return 列出方法的返回值列表（如果需要返回值的话）
	 */
	public static MailStore getInstanse( MailAuth mailAuth){
		String smtpHost = SysTemConfigManager.get(Config.MAIL_SMTP_HOST);
		String smtpPort = SysTemConfigManager.get(Config.MAIL_SMTP_PORT);
		String smtpAuth = SysTemConfigManager.get(Config.MAIL_SMTP_AUTH);
		String storeProtocol = SysTemConfigManager.get(Config.MAIL_STORE_PROTOCOL);
		String pop3Host = SysTemConfigManager.get(Config.MAIL_STORE_HOST);
		String pop3Port = SysTemConfigManager.get(Config.MAIL_STORE_PORT);
		return new MailStore(new MailConfig(smtpHost,smtpPort, smtpAuth, storeProtocol,
				pop3Host, pop3Port, mailAuth));
	}
	
	/**
	 * 通过用户配置信息链接邮件服务器
	 * getInstanse(这里用一句话描述这个方法的作用)     
	 * @param smtpHost
	 * @param smtpPort
	 * @param smtpAuth
	 * @param storeProtocol
	 * @param storeHost
	 * @param storePort
	 * @param userName
	 * @param password
	 * @return
	 * @throws Exception 参数中文名
	 * @return 列出方法的返回值列表（如果需要返回值的话）
	 */
	public static MailStore getInstanse(MailConfig mailConfig) {
		return new MailStore(mailConfig);
	}
	
	
	/**
	 * 
	 * 描述：获取邮箱连接store
	 * @Title: connect
	 * @param 
	 * @return   
	 * @throws
	 */
	public Store connect(){
		Store store = null;
		try{
			Session session = session();
			
			// 创建IMAP协议的Store对象
			store = session.getStore(mailConfig.getStoreProtocol());
			// 连接邮件服务器
			store.connect();
		}catch(Exception e){
			log.error(e);
			e.printStackTrace();
			return null;
		} 
		return store;

		
	}
	
	/**
	 * 获取session信息
	 * session(这里用一句话描述这个方法的作用)     
	 * @return
	 * @throws Exception 参数中文名
	 * @return 列出方法的返回值列表（如果需要返回值的话）
	 */
	public Session session() {
		Properties props = new Properties();
		Session session = null;
		props.setProperty("mail.smtp.host", mailConfig.getSmtpHost());
		props.setProperty("mail.smtp.port", mailConfig.getSmtpPort());
		props.setProperty("mail.smtp.auth", mailConfig.getSmtpAuth());
		props.setProperty("mail.store.protocol", mailConfig.getStoreProtocol());
		if ("imap".equals(mailConfig.getStoreProtocol())) {
			props.setProperty("mail.imap.host", mailConfig.getStoreHost());
			props.setProperty("mail.imap.port", mailConfig.getStorePort());
			
		} else {
			props.setProperty("mail.pop3.host", mailConfig.getStoreHost());
			props.setProperty("mail.pop3.port", mailConfig.getStorePort());
		}
		session = Session.getInstance(props,mailConfig.getMailAuth());
		return session;

		
	}
	
	public static void main(String[] args) throws Exception {
//		 MailStore mrh =  MailStore.getInstanse("smtp.163.com", "true","25",
//		 "imap","imap.163.com", "143", new SmtpAuth("evdrm_q", "Evdrm_wyh0819"));
//		MailStore mrh = MailStore.getInstanse("smtp.163.com", "true","25",
//				"imap", "127.0.0.1", "143", new MailAuth("wangyh", "123456"));
//		Store store = mrh.connect();
//		Folder folder = store.getFolder("INBOX");
//		folder.open(Folder.READ_ONLY);
//		Message msgs[] = folder.getMessages();
//		List<Message> list = new ArrayList<Message>();
//		for (Message mm : msgs) {
//			list.add(mm);
//		}
//		System.out.println("Message Count:" + list.size());
//		;
//		folder.close(false);
//		store.close();
//		Store store1 = mrh.connect();
//		Folder folder1 = store1.getFolder("INBOX");
//		folder1.open(Folder.READ_ONLY);
//		Message msgs1[] = folder1.getMessages();
//		for(Message m : msgs1){
//			Enumeration<Object> a = m.getAllHeaders();
//			System.out.println("Message Count:" + m.getAllHeaders());
//			System.out.println("Message from:" + getFrom(m));
//			System.out.println("Message Count:" +MimeUtility.decodeText(InternetAddress.toString(m.getFrom())));
//		}
//		folder.close(false);
//		store.close();
	}
	
	/**
	 * 
	 * 描述：获取邮箱登录用户名
	 * @Title: getUserName
	 * @param 
	 * @return   
	 * @throws
	 */
	public String getUserName(){
		return mailConfig.getMailAuth().getUsername();
	}
	
}
