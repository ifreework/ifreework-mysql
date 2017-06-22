/**    
 * 文件名：LoginController.java    
 *    
 * 版本信息：    
 * 日期：2014-11-19    
 * Copyright  Corporation 2014     
 * 版权所有    
 *    
 */
package com.ifreework.controller.system;



import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ifreework.common.controller.BaseControllerSupport;
import com.ifreework.common.entity.PageData;
import com.ifreework.common.manager.SysTemConfigManager;
import com.ifreework.common.manager.UserManager;
import com.ifreework.entity.system.Config;
import com.ifreework.entity.system.User;
import com.ifreework.service.system.UserService;
import com.ifreework.util.FileUtil;
import com.ifreework.util.StringUtil;

/**
 * 
 * 类名称：LoginController 
 * 类描述： 
 * 创建人：王宜华 
 * 创建时间：2014-11-19 下午12:40:35 
 * 修改人：王宜华
 * 修改时间：2014-11-19 下午12:40:35 
 * 修改备注：
 * 
 * @version
 * 
 */
@Controller
@RequestMapping({ "/" })
public class LoginController extends BaseControllerSupport {

	@Autowired
	UserService userService;
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
  
	/**
	 * 
	 * @Title: gotoIndexView 
	 * @Description:
	 * @TODO(用户登录界面跳转，如果已经登录，则跳转到Main页面) 
	 * @param @return @throws
	 */
	@RequestMapping()
	public ModelAndView gotoIndexView() {
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		String sysName = SysTemConfigManager.get(Config.SYSTEM_NAME);
		pd.put("SYSNAME", sysName); // 读取系统名称
		mv.addObject("pd", pd);
		User user = UserManager.getUser();
		if (user != null) {
			return gotoMainView();
		} else {
			mv.setViewName("/system/login/login");
		}
		return mv;
	}

	/**
	 * 
	 * @Title: login 
	 * @Description: TODO(用户登录验证) 
	 * @param @return @throws
	 */
	@RequestMapping(value = "/login", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object login() throws Exception {
		PageData pd = getPageData();
		return userService.validateUserByNameAndPwd(pd);
	}

	/**
	 * 
	 * @Title: gotoIndexView @Description: TODO(用户首页跳转) @param @return @throws
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/main")
	public ModelAndView gotoMainView() {
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		
		
		User user = UserManager.getUser();
		
		List menuList = userService.queryMenuByUserId();
		
		String sysName = SysTemConfigManager.get(Config.SYSTEM_NAME);
		pd.put("SYSNAME", sysName); // 读取系统名称
		
		mv.addObject("user",user);
		mv.addObject("pd", pd);
		mv.addObject("menuList",menuList);
		mv.setViewName("/system/main/main");
		return mv;
	}


	@RequestMapping(value = "/main/userChangeImg")
	public ModelAndView userChangeImg() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("/system/main/user/img");
		return mv;
	}
	
	@RequestMapping(value = "/main/userImgUpload")
	@ResponseBody
	public PageData userImgUpload(@RequestParam(value="file",required=false) MultipartFile file,
			double width,double height,double sw,double sh,double sx,double sy){
		PageData pd = new PageData();
		pd.put("width", width);
		pd.put("height", height);
		pd.put("sw", sw);
		pd.put("sh", sh);
		pd.put("sx", sx);
		pd.put("sy", sy);
		pd = userService.userImgUpload(file, width, height, sw, sh, sx, sy);
		return pd;
	}
	
	@RequestMapping(value = "/main/userImgDownload")
	@ResponseBody
	public String userImgDownload() throws Exception{
		User user = UserManager.getUser();
		HttpServletResponse res = this.getHttpServletResponse();
		String imgPath = user.getImgPath();
		if(StringUtil.isEmpty(imgPath) || !new File(imgPath).exists() ){
			imgPath = FileUtil.getRootPath() + "assets/img/main/defeat.jpg";
		}
		FileUtil.fileDownload(res, imgPath);
		return null;
	}

	
	@RequestMapping(value = "/main/contractImgDownload")
	@ResponseBody
	public String contractImgDownload() throws Exception{
		PageData pd = this.getPageData();
		HttpServletResponse res = this.getHttpServletResponse();
		String file = pd.getString("imgPath");
		String isOnLine  = pd.getString("isOnline");
		if(StringUtil.isEmpty(file)){
			file = FileUtil.getRootPath() + "resources/img/main/defeat.jpg";
		}
		if(StringUtil.isEmpty(isOnLine) || "0".equals(isOnLine)){
			file += ".offLine";
		}
		FileUtil.fileDownload(res, file);
		return null;
	}

	
	
	@RequestMapping(value = "/main/skinChoose")
	@ResponseBody
	public PageData skinChoose(){
		PageData pd = this.getPageData();
		User user = UserManager.getUser();
		String skin = pd.getString("skin");
		user.setSkin(skin);
		pd = userService.update(user);
		return pd;
	}
	
	@RequestMapping(value = "/main/message")
	public ModelAndView message() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("/system/main/message/message");
		return mv;
	}
	
	/**
	 * 
	 * @Title: queryContacts
	 * @Description: TODO(获取联系人信息)
	 * @param 
	 * @return   
	 * @throws
	 */
	@RequestMapping(value = "/main/queryContacts")
	@ResponseBody
	public Map<String, Object> queryContacts( ){
		PageData pd = this.getPageData();
		return userService.queryContacts(pd);
	}
	
	/**
	 * 用户注销
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/logout")
	public ModelAndView logout(){
//		if(UserManager.getUser() == null ){
//			return gotoIndexView();                                                                             
//		}
//		String USERNAME = UserManager.getUsername();	//当前登录的用户名
//		logger.debug( USERNAME+"退出系统");
		//shiro销毁登录
		Subject subject = SecurityUtils.getSubject(); 
		subject.logout();
		return gotoIndexView();
	}
}
