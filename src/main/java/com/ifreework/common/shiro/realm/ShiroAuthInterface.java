package com.ifreework.common.shiro.realm;

import java.util.List;
import java.util.Set;

import com.ifreework.entity.system.Resource;
import com.ifreework.entity.system.User;

public interface ShiroAuthInterface {
	
	public User login(String userName,String password);
	/**
	 * 
	 * 描述：查询当前用户具有的所有权限
	 * @Title: queryAuthorityByUserId
	 * @param 
	 * 			userId 
	 * @return   
	 * @throws
	 */
	public Set<String> queryAuthorityByUserName(String userName);
	
	/**
	 * 
	 * 描述：获取当前资源所拥有的全部权限
	 * @Title: queryAuthorityByUrl
	 * @param 
	 * @return   
	 * @throws
	 */
	public List<String> queryAuthorityByResourceId(String resourceId);
	
	/**
	 * 
	 * 描述：根据用户id，获取当前用户
	 * @Title: getUserById
	 * @param 
	 * @return   
	 * @throws
	 */
	public User getUserById(String userId);
	
	/**
	 * 
	 * 描述：根据资源属性及类型，获取资源
	 * @Title: getResource
	 * @param 
	 * @return   
	 * @throws
	 */
	public Resource getResource(String url,String flag);
}
