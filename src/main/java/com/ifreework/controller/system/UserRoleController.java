package com.ifreework.controller.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ifreework.common.controller.BaseControllerSupport;
import com.ifreework.common.entity.PageData;
import com.ifreework.entity.system.UserRole;
import com.ifreework.service.system.UserRoleService;

/**
 * 
 * 描述：    用户关联角色
 * @author：wangyh
 * @createDate：2017年4月28日
 * @modify：wangyh    
 * @modifyDate：2017年4月28日 
 * @version 1.0
 */
@Controller
@RequestMapping({ "/system/userrole" })
public class UserRoleController extends BaseControllerSupport {

	@Autowired
	private UserRoleService userRoleService;

	
	
	/**
	 * 
	 * 描述：跳转到关联角色界面
	 * @Title: gotoView
	 * @param 
	 * @return   
	 * @throws
	 */
	@RequestMapping
	public ModelAndView gotoView() {
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		mv.addObject("userId", pd.getString("userId"));
		mv.setViewName("/system/userrole/view");
		return mv;
	}
	
	
	/**
	 * 
	 * 描述：查询角色树
	 * @Title: queryMenuList
	 * @param 
	 * @return   
	 * @throws
	 */
	@RequestMapping(value = "/queryRoleList")
	@ResponseBody
	public List<UserRole> queryRoleList(){
		PageData pd = this.getPageData();
		return userRoleService.queryRoleList(pd);
	}
	
	/**
	 * 
	 * 描述：保存新增和删除的用户角色
	 * @Title: queryMenuList
	 * @param 
	 * @return   
	 * @throws
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public PageData save(){
		PageData pd = this.getPageData();
		return userRoleService.save(pd);
	}
}
