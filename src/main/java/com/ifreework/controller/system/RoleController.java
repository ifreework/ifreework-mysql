package com.ifreework.controller.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ifreework.common.controller.BaseControllerSupport;
import com.ifreework.common.entity.PageData;
import com.ifreework.entity.system.Role;
import com.ifreework.service.system.RoleService;
import com.ifreework.util.StringUtil;



/**    
 *     
 * 类名称：RoleController    
 * 类描述：    
 * 创建人：王宜华    
 * 创建时间：2014-11-26 下午4:46:18    
 * 修改人：王宜华    
 * 修改时间：2014-11-26 下午4:46:18    
 * 修改备注：    
 * @version     
 *     
 */
@Controller
@RequestMapping("/system/role")
public class RoleController extends BaseControllerSupport {

	@Autowired
	private RoleService roleService ;

	/**
	 * 
	 * 描述：跳转到系统设置首页
	 * @Title: gotoView
	 * @param 
	 * @return   
	 * @throws
	 */
	@RequestMapping()
	public ModelAndView gotoView() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("/system/role/view");
		return mv;
	}
	
	/**
	 * 
	 * 描述：跳转到角色授权界面
	 * @Title: authorization
	 * @param 
	 * @return   
	 * @throws
	 */
	@RequestMapping("/authorization")
	public ModelAndView authorization() {
		PageData pd = this.getPageData();
		ModelAndView mv = this.getModelAndView();
		mv.addObject("roleId",pd.get("treeId"));
		mv.setViewName("/system/role/authorization");
		return mv;
	}
	
	
	@RequestMapping("/query")
	@ResponseBody
	public List<Role> query() {
		PageData pd = this.getPageData();
		List<Role> list = roleService.queryRoleList(pd);
		return list;
	}
	

	
	
	@RequestMapping("/add")
	public ModelAndView add(){
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		String treeId = pd.getString("treeId");
		Role role = new Role();
		role.setParentId(treeId);
		mv.addObject("role", role);
		mv.setViewName("/system/role/edit");
		return mv;
	}
	
	@RequestMapping("/update")
	public ModelAndView update(){
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		Role role = roleService.getRoleById(pd.getString("treeId"));
		mv.addObject("role", role);
		mv.setViewName("/system/role/edit");
		return mv;
	}
	
	@RequestMapping(value = "/save")
	@ResponseBody
	public PageData save(@ModelAttribute( "role" )Role role){
		PageData pd;
		if(StringUtil.isEmpty(role.getRoleId())){
			pd = roleService.add(role);
		}else{
			pd = roleService.update(role);
		}
		
		return pd;
	}
	
	@RequestMapping(value = "/delete")
	@ResponseBody
	public PageData delete(@ModelAttribute( "role" )Role role){
		PageData pd;
		pd = roleService.delete(role);
		return pd;
	}
}
