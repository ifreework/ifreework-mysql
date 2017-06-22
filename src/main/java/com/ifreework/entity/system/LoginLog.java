package com.ifreework.entity.system;

import java.io.Serializable;

public class LoginLog implements Serializable{
	
	
	/**    
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）    
	 *    
	 * @version 1.0    
	 */    
	
	private static final long serialVersionUID = -7815272296449329695L;
	//columns START
	private java.lang.String loginLogId;
	private java.lang.String username;
	private java.util.Date loginTime;
	private java.util.Date logoutTime;
	private java.lang.String ip;
	private java.lang.String browser;
	private java.lang.String browserVersion;
	private java.lang.String deviceName;
	private java.lang.String os;
	private java.lang.String deviceType;
	private java.lang.String osInfo;
	//columns END
	public java.lang.String getLoginLogId() {
		return loginLogId;
	}
	public void setLoginLogId(java.lang.String loginLogId) {
		this.loginLogId = loginLogId;
	}
	public java.lang.String getUsername() {
		return username;
	}
	public void setUsername(java.lang.String username) {
		this.username = username;
	}
	public java.util.Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(java.util.Date loginTime) {
		this.loginTime = loginTime;
	}
	public java.util.Date getLogoutTime() {
		return logoutTime;
	}
	public void setLogoutTime(java.util.Date logoutTime) {
		this.logoutTime = logoutTime;
	}
	public java.lang.String getIp() {
		return ip;
	}
	public void setIp(java.lang.String ip) {
		this.ip = ip;
	}
	public java.lang.String getBrowser() {
		return browser;
	}
	public void setBrowser(java.lang.String browser) {
		this.browser = browser;
	}
	public java.lang.String getBrowserVersion() {
		return browserVersion;
	}
	public void setBrowserVersion(java.lang.String browserVersion) {
		this.browserVersion = browserVersion;
	}
	public java.lang.String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(java.lang.String deviceName) {
		this.deviceName = deviceName;
	}
	public java.lang.String getOs() {
		return os;
	}
	public void setOs(java.lang.String os) {
		this.os = os;
	}
	public java.lang.String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(java.lang.String deviceType) {
		this.deviceType = deviceType;
	}
	public java.lang.String getOsInfo() {
		return osInfo;
	}
	public void setOsInfo(java.lang.String osInfo) {
		this.osInfo = osInfo;
	}

	
}


