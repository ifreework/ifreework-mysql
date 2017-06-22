package com.ifreework.common.email;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeUtility;
import javax.mail.search.SearchTerm;

import com.ifreework.common.email.entity.MailAuth;
import com.ifreework.common.email.entity.MailBean;
import com.ifreework.common.email.entity.MailBoxInfo;
import com.ifreework.common.email.entity.MailConfig;
import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.imap.IMAPMessage;

/**
 * 类说明：接收电子邮件
 * 
 * @author 作者: LiuJunGuang
 * @version 创建时间：2011-7-23 下午04:45:34
 */
public class MailReceive {

	private MailBoxInfo mailBoxInfo = null;
	private Store store = null;
	private IMAPFolder folder = null;

	private boolean saveAttachments = false;// 是否保存附件
	private String attachmentName = null;// 附件的名称
	private String folderName = null;// 邮件夹名称
	private String path = null;// 附件保存的位置
	private StringBuffer content = new StringBuffer();
	private StringBuffer filename = new StringBuffer();

	/**
	 * 根据用户名密码，初始化邮件接收store
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 */
	public MailReceive(String username, String password) {
		MailAuth smtpAuth = new MailAuth(username, password);
		mailBoxInfo = new MailBoxInfo();
		store = MailStore.getInstanse(smtpAuth).connect();
	}

	/**
	 * 采用数据库中的默认配置，初始化邮件接收store，此处用于公共邮箱发送邮件
	 * 创建一个新的实例 MailReceive.    
	 *
	 */
	public MailReceive() {
		mailBoxInfo = new MailBoxInfo();
		store = MailStore.getInstanse().connect();
	}

	/**
	 * 用户自定义创建邮件实例
	 * 创建一个新的实例 MailReceive.    
	 *    
	 * @throws Exception
	 */
	public MailReceive(MailConfig mailConfig) {
		mailBoxInfo = new MailBoxInfo();
		store = MailStore.getInstanse(mailConfig).connect();
	}

	/**
	 * @throws MessagingException 
	 * 描述：打开指定的收件夹folderName,model文件夹打开模式
	 * @Title: openFolder
	 * @param 
	 * 		folderName 文件夹名称
	 * @param 
	 * 		model 打开文件夹模式
	 * @return   
	 * @throws
	 */
	private IMAPFolder openFolder(String folderName, int model) throws MessagingException {
		this.folderName = folderName;
		folder = (IMAPFolder) store.getFolder(folderName);
		if (folder.exists())
			folder.open(model);
		else {
			folder.create(Folder.HOLDS_MESSAGES);
			folder.open(model);
		}
		return folder;
	}

	/**
	 * 描述：保存邮件到指定文件夹
	 * @Title: openFolder
	 * @param 
	 * 		message 要复制的邮件
	 * @param 
	 * 		folderName 要转移的邮件
	 * @return   
	 * @throws
	 */
	public boolean copyMessage(Message[] message, String folderName) {
		try {
			openFolder(folderName, Folder.READ_WRITE);// 打开指定的邮件夹
			folder.appendMessages(message);// 将邮件追加到指定的邮件夹中
			return true;
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 描述：移动邮件到指定文件夹
	 * @Title: openFolder
	 * @param 
	 * 		srcFolder 原邮件文件夹
	 * @param 
	 * 		folderName 目标文件夹
	 * @param 
	 * 		uid 要转移的邮件UID
	 * @return   
	 * @throws
	 */
	public void moveMessage(String srcFolder, String destFolder, long[] uid) throws MessagingException {
		IMAPFolder src = openFolder(srcFolder, Folder.READ_WRITE);
		IMAPFolder dest = openFolder(destFolder, Folder.READ_WRITE);
		Message[] messages = src.getMessagesByUID(uid);
		src.copyMessages(messages, dest);// 复制邮件到指定的邮件夹中
		src.setFlags(messages, Flag.DELETED.getFlag(), true);// 将源邮件夹中的邮件设置为删除
		if (dest.isOpen()) {// 关闭目标邮件夹
			dest.close(true);
		}
		if (src.isOpen()) {// 关闭目标邮件夹
			src.close(true);
		}
	}

	/**
	 * 
	 * 描述：删除邮件
	 * @Title: deleteMessage
	 * @param folderType 文件夹名称
	 * @param uid 要删除的邮件
	 * @return   
	 * @throws
	 */
	public void deleteMessage(String folderType, long[] uid) throws MessagingException {
		IMAPFolder folder = openFolder(folderType, Folder.READ_WRITE);
		Message[] messages = folder.getMessagesByUID(uid);
		folder.setFlags(messages, Flag.DELETED.getFlag(), true);// 将源邮件夹中的邮件设置为删除
		if (folder.isOpen()) {// 关闭目标邮件夹
			folder.close(true);
		}
	}

	/**
	 * 
	 * 描述：接收收件夹中的全部邮件,返回封装了邮件总数、新邮件数、未读邮件数、邮件列表的MailInfo
	 * 		 使用该方法之前需要先设置文件夹目录
	 * @Title: getMailList
	 * @param 
	 * @return   
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException 
	 */
	public MailBoxInfo getMailList() throws MessagingException, UnsupportedEncodingException {
		Message[] messages = folder.getMessages();
		setMailInfo();// 设置邮件的基本信息
		getMessageList(messages);// 将邮件封装成邮件列表
		return mailBoxInfo;
	}

	/**
	 * @throws UnsupportedEncodingException 
	 * 
	 * 描述：获取符合条件的邮件
	 * @Title: getMailList
	 * @param 
	 * @return   
	 * @throws
	 */
	public MailBoxInfo getMailList(SearchTerm term) throws MessagingException, UnsupportedEncodingException{
		Message[] messages = folder.search(term);
		setMailInfo();// 设置邮件的基本信息
		getMessageList(messages);// 设置邮件消息内容
		return mailBoxInfo;
	}


	/**
	 * 描述：得到某个邮件夹下的所有邮件的UID （返回long[]）
	 * @Title: getMessageUIDs
	 * @param 
	 * @return   
	 * @throws
	 */
	public long[] getMessageUIDs(IMAPFolder folder) throws MessagingException {
		long[] uids = new long[folder.getMessageCount()];
		Message[] messages = folder.getMessages();
		int length = messages.length;
		if (length > 0) {
			for (int i = 0; i < length; i++) {//
				uids[length - 1 - i] = folder.getUID(messages[i]);
			}
		}
		return uids;
	}

	/**
	 * 描述：得到符合条件的邮件Uid
	 * @Title: getMessageUIDs
	 * @param 
	 * @return   
	 * @throws
	 */
	public long[] getMessageUIDs(IMAPFolder folder, SearchTerm term) throws MessagingException {
		Message[] messages = folder.search(term);
		long[] uids = new long[messages.length];
		int length = messages.length;
		if (length > 0) {
			for (int i = 0; i < length; i++) {//
				uids[length - 1 - i] = folder.getUID(messages[i]);
			}
			return uids;
		}
		return null;
	}

	/**
	 * 得到从from开始到to之间的邮件，不包括to邮件
	 * 
	 * @throws Exception
	 */
	public MailBoxInfo getMailList(int from, int to, long[] uids) throws Exception {
		if (uids != null && uids.length > 0) {
			if (to > uids.length)
				to = uids.length;
			long[] subUids = Arrays.copyOfRange(uids, from, to);
			Message[] messages = folder.getMessagesByUID(subUids);
			setMailInfo();// 设置邮件的基本信息
			getMessageList(messages);// 设置邮件消息内容
			return mailBoxInfo;
		}
		return null;
	}

	// 关闭各种资源
	public void close() {
		try {
			if (folder != null && folder.isOpen())
				folder.close(true);
			if (store != null && store.isConnected())
				store.close();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 将邮件封装成一个邮件列表
	 * @throws MessagingException 
	 * @throws UnsupportedEncodingException 
	 * 
	 * @throws Exception
	 */
	private List<MailBean> getMessageList(Message[] messages) throws UnsupportedEncodingException, MessagingException  {
		List<MailBean> list = new LinkedList<MailBean>();
		for (Message message : messages) {
			IMAPMessage m = (IMAPMessage) message;
			list.add(setMailBean(m));// 将每个邮件体封装成一个MailBean，并将MailBean添加到list中
		}
		mailBoxInfo.setMailBeanList(list);
		return list;
	}

	/**
	 * 设置邮件基本信息(发送人、接收人、抄送、主题、大小、发送日期、标记、邮件id、邮件UID)
	 * @throws MessagingException 
	 * @throws UnsupportedEncodingException 
	 * 
	 * @throws Exception
	 */
	private MailBean setMailBean(IMAPMessage m) throws MessagingException, UnsupportedEncodingException {
		MailBean mail = new MailBean();
		mail.setSubject(m.getSubject());
		mail.setDate(m.getSentDate());
		mail.setFrom(MailUtil.decode(getFrom(m)));// 设置发件人
		mail.setCc(MailUtil.decode(InternetAddress.toString(m.getRecipients(Message.RecipientType.CC))));
		mail.setTo(MailUtil.decode(getTo(m)));
		mail.setMessageID(m.getMessageID());
		mail.setSize(m.getSize());
		mail.setMessageUID(folder.getUID(m));
		mail.setFlags(setFlags(m));
		mail.setFolderType(MailUtil.folderType(folderName));
		return mail;
	}

	// 封装邮件的标记
	private String setFlags(IMAPMessage m) throws MessagingException {
		Flags flags = m.getFlags();
		StringBuffer sb = new StringBuffer();
		Flags.Flag[] sf = flags.getSystemFlags(); // get the system flags
		for (int i = 0; i < sf.length; i++) {
			String s;
			Flags.Flag f = sf[i];
			if (f == Flags.Flag.ANSWERED)
				s = "Answered";
			else if (f == Flags.Flag.DELETED)
				s = "Deleted";
			else if (f == Flags.Flag.DRAFT)
				s = "Draft";
			else if (f == Flags.Flag.FLAGGED)
				s = "Flagged";
			else if (f == Flags.Flag.RECENT)
				s = "Recent";
			else if (f == Flags.Flag.SEEN)
				s = "Seen";
			else
				continue; // skip it
			sb.append(s);
			sb.append(',');
		}
		if (sb.length() > 0)
			sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}

	/** 设置邮件的公共信息(新邮件个数、邮件总数、未读邮件个数) */
	private void setMailInfo() throws MessagingException {
		mailBoxInfo.setNewMessageCount(folder.getNewMessageCount());
		mailBoxInfo.setMessageCount(folder.getMessageCount());
		mailBoxInfo.setUnreadMessageCount(folder.getUnreadMessageCount());
	}

	// 根据指定的uid找到邮件具体内容(不保存附件)
	public MailBean getMailBeanByUID(long uid) throws Exception {
		return getMailBeanByUID(uid, false, null, null);
	}

	// 根据指定的uid找到邮件具体内容
	public MailBean getMailBeanByUID(long uid, boolean saveAttachments, String attachmentName, String path)
			throws Exception {
		this.saveAttachments = saveAttachments;
		this.attachmentName = attachmentName;
		this.path = path;
		MailBean mailbean = new MailBean();
		IMAPMessage m = (IMAPMessage) folder.getMessageByUID(uid);
		m.setFlag(Flags.Flag.SEEN, true);// 设置邮件为已读
		mailbean = setMailBean(m);
		clear();// 清空缓存附件名与正文信息信息
		dumpPart(m);
		setContent(mailbean);// 设置邮件正文和附件信息
		return mailbean;
	}

	// 根据指定的uid找到邮件具体内容
	public IMAPMessage getMessageByUID(long uid) throws Exception {
		IMAPMessage m = (IMAPMessage) folder.getMessageByUID(uid);
		return m;
	}

	// 清空附件信息和正文信息
	private void clear() {
		content.delete(0, content.length());
		filename.delete(0, filename.length());
	}

	// 设置邮件正文和附件信息
	private void setContent(MailBean mailbean) throws UnsupportedEncodingException {
		mailbean.setContent(content.toString());
		if (filename.length() > 0) {
			mailbean.setFile(filename.deleteCharAt(filename.length() - 1).toString());
		}
	}

	// 设置邮件的标记
	public void setMessagesFlags(long[] uids, Flag flag, boolean value) throws MessagingException {
		Message[] messages = folder.getMessagesByUID(uids);
		folder.setFlags(messages, flag.getFlag(), value);
	}

	// 遍历正文内容
	private void dumpPart(Part p) throws Exception {
		String fn = MailUtil.decode(p.getFileName());
		if (fn != null && fn.length() > 0)
			filename.append(fn).append(",");
		if (p.isMimeType("text/plain")) {
			if (!saveAttachments)
				content.append((String) p.getContent());// 添加邮件内容
		} else if (p.isMimeType("multipart/*")) {
			Multipart mp = (Multipart) p.getContent();
			int count = mp.getCount();
			for (int i = 0; i < count; i++)
				dumpPart(mp.getBodyPart(i));// 循环遍历每个邮件体
		} else if (p.isMimeType("message/rfc822")) {
			dumpPart((Part) p.getContent());
		} else {
			if (!saveAttachments) {
				Object o = p.getContent();
				if (o instanceof String) {
					content.append((String) o);
				}
			}
		}
		if (saveAttachments && !p.isMimeType("multipart/*")) {
			String disp = p.getDisposition();
			if (disp == null || disp.equalsIgnoreCase(Part.ATTACHMENT)) {
				if (fn != null && fn.equals(attachmentName)) {
					try {// 保存附件
						File saveFile = new File(path + "/" + fn);
						if (!saveFile.getParentFile().exists())
							saveFile.getParentFile().mkdirs();
						((MimeBodyPart) p).saveFile(saveFile);// 保存附件到指定的路径中
						return;
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}
			}
		}
	}

	/**
	 * 对message文件头进行解析，获取发件人信息
	 * @param message
	 * @return
	 * @throws MessagingException
	 */
	public String getFrom(Message message) throws MessagingException {
		String[] from = message.getHeader("From") == null || message.getHeader("From").length == 0
				? message.getHeader("Sender") : message.getHeader("From");
		if (from != null && from.length != 0) {
			System.out.println(from[0]);
			return from[0].replaceAll("<", "&lt;").replaceAll(">", "&gt;");
		}
		return "";
	}

	/**
	 * 对message文件头进行解析，获取收件人信息
	 * @param message
	 * @return
	 * @throws MessagingException
	 */
	public String getTo(Message message) throws MessagingException {
		String[] to = message.getHeader("To");
		if (to != null && to.length != 0) {
			System.out.println(to[0]);
			return to[0].replaceAll("<", "&lt;").replaceAll(">", "&gt;");
		}
		return "";
	}

	/**
	 * 查找邮件中的附件，并进行下载
	 * @param part
	 * @return
	 * @throws Exception
	 */
	public InputStream saveAttachMent(Part part, String fileName) throws Exception {
		if (part.isMimeType("multipart/*")) {
			Multipart mp = (Multipart) part.getContent();
			for (int i = 0; i < mp.getCount(); i++) {
				BodyPart mpart = mp.getBodyPart(i);
				String disposition = mpart.getDisposition();
				if ((disposition != null)
						&& ((disposition.equals(Part.ATTACHMENT)) || (disposition.equals(Part.INLINE)))) {
					String mailFileName = mpart.getFileName();
					;
					if (mailFileName.toUpperCase().indexOf("?Q?") > 0) {
						mailFileName = mailFileName.replaceAll("\\+", "=20");
					}
					mailFileName = MimeUtility.decodeText(mailFileName);
					if (fileName.equals(mailFileName)) {
						return mpart.getInputStream();
					}
				}
			}
		}
		return null;
	}

	/**
	 * 查找邮件中的附件，并进行下载
	 * @param part
	 * @return
	 * @throws Exception
	 */
	public BodyPart getAttachMentPart(Part part, String fileName) throws Exception {
		if (part.isMimeType("multipart/*")) {
			Multipart mp = (Multipart) part.getContent();
			for (int i = 0; i < mp.getCount(); i++) {
				BodyPart mpart = mp.getBodyPart(i);
				String disposition = mpart.getDisposition();
				if ((disposition != null)
						&& ((disposition.equals(Part.ATTACHMENT)) || (disposition.equals(Part.INLINE)))) {
					String mailFileName = mpart.getFileName();
					;
					if (mailFileName.toUpperCase().indexOf("?Q?") > 0) {
						mailFileName = mailFileName.replaceAll("\\+", "=20");
					}
					mailFileName = MimeUtility.decodeText(mailFileName);
					if (fileName.equals(mailFileName)) {
						return mpart;
					}
				}
			}
		}
		return null;
	}
}
