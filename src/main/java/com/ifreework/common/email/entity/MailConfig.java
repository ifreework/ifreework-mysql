package com.ifreework.common.email.entity;

public class MailConfig {
	private String smtpHost = null;
	private String smtpPort = null;
	private String smtpAuth = null;
	private String storeProtocol = null;
	private String storeHost = null;
	private String storePort = null;

	private MailAuth mailAuth = null;
	
	public MailConfig(){
		
	}

	
	public MailConfig(String smtpHost, String smtpPort, String smtpAuth, String storeProtocol, String storeHost,
			String storePort, MailAuth mailAuth) {
		super();
		this.smtpHost = smtpHost;
		this.smtpPort = smtpPort;
		this.smtpAuth = smtpAuth;
		this.storeProtocol = storeProtocol;
		this.storeHost = storeHost;
		this.storePort = storePort;
		this.mailAuth = mailAuth;
	}


	public String getSmtpHost() {
		return smtpHost;
	}
	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}
	public String getSmtpPort() {
		return smtpPort;
	}
	public void setSmtpPort(String smtpPort) {
		this.smtpPort = smtpPort;
	}
	public String getSmtpAuth() {
		return smtpAuth;
	}
	public void setSmtpAuth(String smtpAuth) {
		this.smtpAuth = smtpAuth;
	}
	public String getStoreProtocol() {
		return storeProtocol;
	}
	public void setStoreProtocol(String storeProtocol) {
		this.storeProtocol = storeProtocol;
	}
	public String getStoreHost() {
		return storeHost;
	}
	public void setStoreHost(String storeHost) {
		this.storeHost = storeHost;
	}
	public String getStorePort() {
		return storePort;
	}
	public void setStorePort(String storePort) {
		this.storePort = storePort;
	}


	public MailAuth getMailAuth() {
		return mailAuth;
	}


	public void setMailAuth(MailAuth mailAuth) {
		this.mailAuth = mailAuth;
	}
	
	
}
