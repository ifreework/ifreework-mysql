/**    
 * 文件名：FileUploadServiceImpl.java    
 *    
 * 版本信息：    
 * 日期：2014-12-16    
 * Copyright  Corporation 2014     
 * 版权所有    
 *    
 */
package com.ifreework.service.system;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifreework.common.entity.PageData;
import com.ifreework.common.manager.ServletRequestManager;
import com.ifreework.common.shiro.interceptor.RequestLogManager;
import com.ifreework.entity.system.RequestLog;
import com.ifreework.mapper.system.RequestLogMapper;
import com.ifreework.util.ServerUtil;

import eu.bitwalker.useragentutils.UserAgent;

/**
 * 
 * 描述：    请求日志记录
 * @author：wangyh
 * @createDate：2017年5月8日
 * @modify：wangyh    
 * @modifyDate：2017年5月8日 
 * @version 1.0
 */
@Service("requestLogService")
public class RequestLogServiceImpl implements RequestLogService, RequestLogManager {
	@Autowired
	private RequestLogMapper requestLogMapper;

	/**
	 * 
	 * 描述：分页查询
	 * @Title: queryPageList
	 * @param 
	 * @return   
	 * @throws
	 */
	@Override
	public PageData queryPageList(PageData pd) {
		List<RequestLog> list = requestLogMapper.queryPageList(pd);
		pd = new PageData();
		pd.setPagination(list);
		return pd;
	}

	@Override
	public RequestLog getRequestLog(String path) {
		HttpServletRequest request = ServletRequestManager.getHttpServletRequest();
		String ip = request.getRemoteAddr();// 访问者IP
		String agentStr = request.getHeader("User-Agent");
		UserAgent agent = UserAgent.parseUserAgentString(agentStr);
		String hostName = null;
		String hostIp = null;
		String nodeName = null;
		
		
		try {
			hostName = InetAddress.getLocalHost().getHostName();
			hostIp = InetAddress.getLocalHost().getHostAddress();
			if(ServerUtil.isWebLogic()){
				nodeName = System.getProperty("weblogic.Name");
			}else{
				nodeName = ServerUtil.getServerId();
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		RequestLog requestLog = new RequestLog();
		requestLog.setSessionId(SecurityUtils.getSubject().getSession().getId().toString());
		requestLog.setUsername((String)SecurityUtils.getSubject().getPrincipal());
		requestLog.setUrl(path);
		requestLog.setRequestTime(new Date());
		
		requestLog.setRequestIp(ip);
		requestLog.setBrowser(agent.getBrowser().getName());
		requestLog.setBrowserVersion(agent.getBrowserVersion().getVersion());
		
		requestLog.setOs(agent.getOperatingSystem().getGroup().getName());
		requestLog.setOsInfo(agent.getOperatingSystem().getName());
		requestLog.setDeviceType(agent.getOperatingSystem().getDeviceType().getName());
		
		requestLog.setResponseDevice(hostName);
		requestLog.setResponseIp(hostIp);
		requestLog.setResponseNode(nodeName);
		
		return requestLog;
	}

	@Override
	public void saveRequestLog(RequestLog requestLog) {
		requestLogMapper.add(requestLog);
	}

}
