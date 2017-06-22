
/**    
 * 文件名：UserService.java    
 *    
 * 版本信息：    
 * 日期：2016年7月6日    
 * Copyright  Corporation 2016     
 * 版权所有    
 *    
 */
package com.ifreework.service.system;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.ifreework.common.entity.PageData;
import com.ifreework.entity.system.User;


/**        
 * 描述：    
 * @author：wangyh qq735789026  
 * @创建时间：2016年7月6日 下午4:25:00    
 * @修改人：wangyh    
 * @修改时间：2016年7月6日 下午4:25:00    
 * @version 1.0      
 */
public interface UserService {
	
	public User getUserByUserName(String userName);
	/**
	 * 
	 * @Title: validateUserByNameAndPwd
	 * @Description: TODO()
	 * @param 
	 * @return   
	 * @throws
	 */
	public PageData validateUserByNameAndPwd(PageData pd);
	
	public List<User> queryUserList(PageData pd );
	/**
	 * 文件上传后图片保存位置
	 * @param file
	 * @param pd 
	 * @return
	 */
	public PageData userImgUpload(MultipartFile file, double width, double height, double sw, double sh, double sx,
			double sy);
	/**
	 * 描述：根据用户id，重置用户密码
	 * @Title: resetPwd
	 * @param 
	 * @return   
	 * @throws
	 */
	public PageData resetPwd(PageData pd);
	
	public PageData update(User user);
	
	public PageData add(User user);
	
	/**
	 * 
	 * @Title: queryContacts
	 * @Description: TODO(获取所用联系人和在线联系人信息)
	 * @param 
	 * @return   
	 * @throws
	 */
	public Map<String,Object> queryContacts(PageData pd);
	
	/**
	 * 
	 * 描述：通过用户名查询用户所拥有的菜单
	 * @Title: queryMenuByUserId
	 * @param 
	 * @return   
	 * @throws
	 */
	@SuppressWarnings("rawtypes")
	public List queryMenuByUserId();
	
	/**
	 * 
	 * 描述：重置密码
	 * @Title: reset
	 * @param 
	 * @return   
	 * @throws
	 */
	public PageData changePwdSave(PageData pd) ;
	
	/**
	 * 
	 * 描述：通过用户ID查询用户
	 * @param userId
	 */
	public User getUserById(String userId);
	
	/**
	 * 描述：验证用户名是否已经存在
	 * @param userName
	 * @return 
	 * @return
	 */
	public PageData validateUserName(PageData pd);
}
