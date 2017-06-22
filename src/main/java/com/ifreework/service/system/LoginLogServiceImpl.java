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

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifreework.common.entity.PageData;
import com.ifreework.common.manager.ServletRequestManager;
import com.ifreework.common.shiro.listener.LoginLogManager;
import com.ifreework.entity.system.LoginLog;
import com.ifreework.mapper.system.LoginLogMapper;

import eu.bitwalker.useragentutils.UserAgent;

/**
 * 
 * 描述：    附件上传
 * @author：wangyh
 * @createDate：2017年5月8日
 * @modify：wangyh    
 * @modifyDate：2017年5月8日 
 * @version 1.0
 */
@Service("loginLogService")
public class LoginLogServiceImpl implements LoginLogService, LoginLogManager {
	@Autowired
	private LoginLogMapper loginLogMapper;

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
		List<LoginLog> list = loginLogMapper.queryPageList(pd);
		pd = new PageData();
		pd.setPagination(list);
		return pd;
	}

	@Override
	public void add(String username) {
		HttpServletRequest request = ServletRequestManager.getHttpServletRequest();
		String ip = request.getRemoteAddr();// 访问者IP
		String agentStr = request.getHeader("User-Agent");
		UserAgent agent = UserAgent.parseUserAgentString(agentStr);
		
		
		LoginLog loginLog = new LoginLog();
		loginLog.setLoginLogId(SecurityUtils.getSubject().getSession().getId().toString());
		loginLog.setUsername(username);
		loginLog.setBrowser(agent.getBrowser().getName());
		loginLog.setBrowserVersion(agent.getBrowserVersion().getVersion());
		loginLog.setIp(ip);
		loginLog.setOs(agent.getOperatingSystem().getGroup().getName());
		loginLog.setOsInfo(agent.getOperatingSystem().getName());
		loginLog.setDeviceType(agent.getOperatingSystem().getDeviceType().getName());
		loginLogMapper.add(loginLog);
	}

	@Override
	public void logoutLog(String username) {
		loginLogMapper.update(username);
	}

}
