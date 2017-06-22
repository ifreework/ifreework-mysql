package com.ifreework.common.email;

import java.io.File;
import java.io.UnsupportedEncodingException;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import com.ifreework.common.email.entity.MailAuth;
import com.ifreework.common.email.entity.MailBean;
import com.ifreework.common.email.entity.MailConfig;



/**
 * 
 * 描述：    邮件发送工具类
 * @author：wangyh qq735789026  
 * @createTime：2017年4月19日 下午10:03:16    
 * @editer：wangyh    
 * @editTime：2017年4月19日 下午10:03:16    
 * @version 1.0
 */
public class MailSend {
	private MailBean mailBean = null;
	private Session session = null;
	private String sender = null;
	
	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	/**
	 * 根据用户名密码，初始化邮件接收store
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 */
	public MailSend(String username, String password,MailBean mailBean) {
		this.mailBean = mailBean;
		MailAuth smtpAuth = new MailAuth(username, password);
		session = MailStore.getInstanse(smtpAuth).session();
		this.sender = username;
	}

	/**
	 * 采用数据库中的默认配置，初始化邮件接收store，此处用于公共邮箱发送邮件
	 * 创建一个新的实例 MailReceive.    
	 *
	 */
	public MailSend(MailBean mailBean) {
		this.mailBean = mailBean;
		MailStore store = MailStore.getInstanse();
		this.session =store.session();
		this.sender = store.getUserName();
	}

	/**
	 * 用户自定义创建邮件实例
	 * 创建一个新的实例 MailReceive.    
	 *    
	 * @throws Exception
	 */
	public MailSend(MailConfig mailConfig,MailBean mailBean) {
		this.mailBean = mailBean;
		session = MailStore.getInstanse(mailConfig).session();
		this.sender = mailConfig.getMailAuth().getUsername();
	}

	/** 创建邮件 
	 * @throws MessagingException 
	 * @throws UnsupportedEncodingException 
	 * @throws Exception */
	public MimeMessage createMimeMessage() throws MessagingException, UnsupportedEncodingException {
		MimeMessage message = new MimeMessage(session);// 创建整体邮件
		// 设置邮件基本信息
		setMimeMessageInfo(message);
		MimeMultipart multipart = null;
		// 创建什么都不含的邮件体（alternative）
		if (mailBean.getContent() != null && mailBean.getContent().length() > 0)
			multipart = createAlternative(multipart);
		// 创建含有内嵌资源的邮件体(related)
		if (mailBean.getResource() != null && mailBean.getResource().length() > 0)
			multipart = createRelated(multipart);
		// 创建含有附件的邮件体（mixed）
		if (mailBean.getFile() != null && mailBean.getFile().length() > 0)
			multipart = createMixed(multipart);

		// 添加multipart到邮件内容上
		if (multipart == null)
			multipart = createAlternative(multipart);
		message.setContent(multipart);
		message.saveChanges();
		return message;
	}

	// 创建什么都不含的邮件体（alternative）
	private MimeMultipart createAlternative(MimeMultipart multipart)
			throws MessagingException {
		multipart = new MimeMultipart("alternative");
		MimeBodyPart html = new MimeBodyPart();
		html.setContent(mailBean.getContent(), "text/html;charset=UTF-8");
		multipart.addBodyPart(html);
		return multipart;
	}

	// 创建含有内嵌资源的邮件体(related)
	private MimeMultipart createRelated(MimeMultipart multipart)
			throws MessagingException {
		MimeBodyPart alternative = new MimeBodyPart();
		alternative.setContent(multipart);
		MimeMultipart related = new MimeMultipart("related");
		related.addBodyPart(alternative);
		// 添加内嵌资源
		related = addResource(related);
		return related;
	}

	// 添加内嵌资源
	private MimeMultipart addResource(MimeMultipart related)
			throws MessagingException {
		String resources[] = mailBean.getResource().split(",");
		for (String res : resources) {
			MimeBodyPart img = new MimeBodyPart();
			FileDataSource fds = new FileDataSource(res);
			img.setDataHandler(new DataHandler(fds));
			img.setContentID(System.currentTimeMillis() + fds.getName());
			related.addBodyPart(img);
		}
		return related;
	}

	// 创建含有附件的邮件体（mixed）
	private MimeMultipart createMixed(MimeMultipart multipart)
			throws MessagingException, UnsupportedEncodingException {
		MimeBodyPart related = new MimeBodyPart();
		
		// 添加multipart到邮件内容上
		if (multipart == null)
			multipart = createAlternative(multipart);
		related.setContent(multipart);
		MimeMultipart mixed = new MimeMultipart("mixed");
		mixed.addBodyPart(related);
		// 添加附件
		mixed = addAttachment(mixed);
		return mixed;
	}

	// 添加附件
	private MimeMultipart addAttachment(MimeMultipart mixed)
			throws MessagingException, UnsupportedEncodingException {
		String files[] = mailBean.getFile().split(",");
		for (String file : files) {
			if(new File(file).exists()){
				MimeBodyPart attachment = new MimeBodyPart();
				FileDataSource fds = new FileDataSource(file);
				attachment.setDataHandler(new DataHandler(fds));
				attachment.setFileName(MimeUtility.encodeText(fds.getName(),
						"UTF-8", "Q"));
				mixed.addBodyPart(attachment);
			}
		}
		if(mailBean.getBodyPart() != null && mailBean.getBodyPart().length != 0){
			for(BodyPart attachment : mailBean.getBodyPart()){
				mixed.addBodyPart(attachment);
			}
		}
		return mixed;
	}

	// 设置邮件具体信息
	private void setMimeMessageInfo(MimeMessage message)
			throws AddressException, MessagingException,
			UnsupportedEncodingException {
		if (mailBean.getFrom() != null && !"".equals(mailBean.getFrom())) {
			message.setFrom(new InternetAddress(MailUtil.encode(mailBean.getFrom() +" <"+ sender +">" )));// 设置发件人
		}
		if(sender != null && !"".equals(sender)){
			message.setSender(new InternetAddress(MailUtil.encode(sender)));
		}
		if (mailBean.getTo() != null && !"".equals(mailBean.getTo()))
			message.setRecipients(RecipientType.TO,
					InternetAddress.parse(MailUtil.encode(mailBean.getTo())));// 设置收件人
		if (mailBean.getCc() != null && !"".equals(mailBean.getCc()))
			message.setRecipients(RecipientType.CC,
					InternetAddress.parse(MailUtil.encode(mailBean.getCc())));// 设置抄送人
		if (mailBean.getBcc() != null && !"".equals(mailBean.getBcc()))
			message.setRecipients(RecipientType.BCC,
					InternetAddress.parse(MailUtil.encode(mailBean.getBcc())));// 设置密送人
		if (mailBean.getSubject() != null && !"".equals(mailBean.getSubject()))
			message.setSubject(mailBean.getSubject(), "UTF-8");
		// 是否紧急
		if (mailBean.isExigence()) {// 指定邮件的优先级，1：紧急，3：普通，5：缓慢
			message.setHeader("X-Priority", "1");
		} else {
			message.setHeader("X-Priority", "3");
		}
		message.setSentDate(mailBean.getDate());
	}

	/**
	 * 发送邮件
	 * @throws Exception 
	 */
	public boolean sendMail() {
		try {
			MimeMessage message = createMimeMessage();
			Transport.send(message);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 发送指定邮件体的邮件
	 */
	public void sendMail(MimeMessage message) throws AddressException,
			MessagingException, UnsupportedEncodingException {
		// 发送消息
		Transport.send(message);
	}
	
	
	public static void main(String[] args) throws Exception {
		MailBean mailBean = new MailBean("cd_wangyh@163.com","wyh198910",
				"cd_wangyh@163.com", "735789026@qq.com",
				"王宜华工作周报", "111",
				"", "");
		MailSend h = new MailSend(mailBean);
		h.sendMail();
		
	}
}
