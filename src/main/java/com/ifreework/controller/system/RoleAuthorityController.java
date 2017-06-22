package com.ifreework.controller.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ifreework.common.controller.BaseControllerSupport;
import com.ifreework.common.entity.PageData;
import com.ifreework.entity.system.RoleAuthority;
import com.ifreework.service.system.RoleAuthorityService;

/**
 * 
 * 描述：    角色授权
 * @author：wangyh
 * @createDate：2017年4月28日
 * @modify：wangyh    
 * @modifyDate：2017年4月28日 
 * @version 1.0
 */
@Controller
@RequestMapping({ "/system/roleauthority" })
public class RoleAuthorityController extends BaseControllerSupport {

	@Autowired
	private RoleAuthorityService roleAuthorityService;

	
	
	/**
	 * 
	 * 描述：跳转到菜单授权界面
	 * @Title: gotoView
	 * @param 
	 * @return   
	 * @throws
	 */
	@RequestMapping("/menu")
	public ModelAndView menu() {
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		mv.addObject("roleId", pd.getString("roleId"));
		mv.setViewName("/system/roleauthority/menu");
		return mv;
	}
	
	
	/**
	 * 
	 * 描述：查询菜单权限树
	 * @Title: queryMenuList
	 * @param 
	 * @return   
	 * @throws
	 */
	@RequestMapping(value = "/queryMenuList")
	@ResponseBody
	public List<RoleAuthority> queryMenuList(){
		PageData pd = this.getPageData();
		return roleAuthorityService.queryMenuList(pd);
	}
	
	/**
	 * 
	 * 描述：保存新增和删除的角色权限
	 * @Title: queryMenuList
	 * @param 
	 * @return   
	 * @throws
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public PageData save(){
		PageData pd = this.getPageData();
		return roleAuthorityService.save(pd);
	}
}
