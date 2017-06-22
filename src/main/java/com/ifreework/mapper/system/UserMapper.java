package com.ifreework.mapper.system;

import java.util.List;
import java.util.Map;
import java.util.Set;


import com.ifreework.common.entity.PageData;
import com.ifreework.entity.system.User;

public interface UserMapper {
	
	public User getUserById(String userId);
	
	public User getUserByUserName(String userName);
	
	public List<User> queryUserList(PageData pd);
	
	
	/**
	 * 
	 * 描述：通过用户名查询用户所拥有的菜单
	 * @Title: queryMenuByUserId
	 * @param 
	 * @return   
	 * @throws
	 */
	public List<Map<String,Object>> queryMenuByUserId(Map<String,Object> map);
	
	public List<String> queryRoleTreeByUserId(PageData pd);
	/**
	 * 
	 * 描述：查询当前用户具有的所有权限
	 * @Title: queryAuthorityByUserId
	 * @param 
	 * 			userId 
	 * @return   
	 * @throws
	 */
	public Set<String> queryAuthorityByUserName(List<String> roleList);
	
	/**
	 * 
	 * 描述：根据当前请求路径，获取该请求所需的权限
	 * @Title: queryAuthorityByUrl
	 * @param 
	 * @return   
	 * @throws
	 */
	public List<String> queryAuthorityByResourceId(String resourceId);
	
	public void update(User user);
	
	public void add(User user);
}
