package com.ifreework.entity.system;


import java.io.Serializable;

public class RequestLog implements Serializable{
	
	
	/**    
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）    
	 *    
	 * @version 1.0    
	 */    
	
	private static final long serialVersionUID = -7211684431445523719L;
	//columns START
	private java.lang.String requestLogId;
	private java.lang.String sessionId;
	private java.lang.String username;
	private java.lang.String url;
	private java.util.Date requestTime;
	private Long responseTime;
	private java.lang.String requestIp;
	private java.lang.String browser;
	private java.lang.String browserVersion;
	private java.lang.String deviceType;
	private java.lang.String osInfo;
	private java.lang.String os;
	private java.lang.String responseDevice;
	private java.lang.String responseIp;
	private java.lang.String responseNode;
	
	private Resource resource;
	//columns END

	public java.lang.String getRequestLogId() {
		return requestLogId;
	}

	public void setRequestLogId(java.lang.String requestLogId) {
		this.requestLogId = requestLogId;
	}

	public java.lang.String getSessionId() {
		return sessionId;
	}

	public void setSessionId(java.lang.String sessionId) {
		this.sessionId = sessionId;
	}

	public java.lang.String getUsername() {
		return username;
	}

	public void setUsername(java.lang.String username) {
		this.username = username;
	}

	public java.lang.String getUrl() {
		return url;
	}

	public void setUrl(java.lang.String url) {
		this.url = url;
	}

	public java.util.Date getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(java.util.Date requestTime) {
		this.requestTime = requestTime;
	}

	public Long getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(Long responseTime) {
		this.responseTime = responseTime;
	}

	public java.lang.String getRequestIp() {
		return requestIp;
	}

	public void setRequestIp(java.lang.String requestIp) {
		this.requestIp = requestIp;
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

	public java.lang.String getOs() {
		return os;
	}

	public void setOs(java.lang.String os) {
		this.os = os;
	}

	public java.lang.String getResponseDevice() {
		return responseDevice;
	}

	public void setResponseDevice(java.lang.String responseDevice) {
		this.responseDevice = responseDevice;
	}

	public java.lang.String getResponseIp() {
		return responseIp;
	}

	public void setResponseIp(java.lang.String responseIp) {
		this.responseIp = responseIp;
	}

	public java.lang.String getResponseNode() {
		return responseNode;
	}

	public void setResponseNode(java.lang.String responseNode) {
		this.responseNode = responseNode;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}
	
	

}


