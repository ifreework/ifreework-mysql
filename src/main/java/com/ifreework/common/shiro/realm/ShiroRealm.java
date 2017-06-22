package com.ifreework.common.shiro.realm;


import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;



/**
 * 
 * 描述：    Shiro认证控制核心类
 * @author：wangyh
 * @createDate：2017年4月29日
 * @modify：wangyh    
 * @modifyDate：2017年4月29日 
 * @version 1.0
 */
public class ShiroRealm extends AuthorizingRealm {

	private static Logger log = Logger.getLogger(ShiroRealm.class);
	
	private ShiroAuthInterface shiroAuth;

	public ShiroAuthInterface getShiroAuth() {
		return shiroAuth;
	}

	public void setShiroAuth(ShiroAuthInterface shiroAuth) {
		this.shiroAuth = shiroAuth;
	}


	/**
	 * 
	 * 描述：用户登录认证
	 * @Title: doGetAuthenticationInfo
	 * @param 
	 * @return   
	 * @throws
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername().trim();
        String password = "";
        if (upToken.getPassword() != null) {
            password = new String(upToken.getPassword());
            shiroAuth.login(username, password);
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, password, getName());
            return info;
        }
        throw new UnknownAccountException();
	}
	

	/**
	 * 
	 * 描述：用于获取当前用户所有的权限
	 * @Title: doGetAuthorizationInfo
	 * @param 
	 * @return   
	 * @throws
	 */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String) principals.getPrimaryPrincipal();

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Set<String> set = shiroAuth.queryAuthorityByUserName(username);
        log.debug("User " + username + " has the auth is " + set);
        authorizationInfo.setStringPermissions(set);

        return authorizationInfo;
    }

}
