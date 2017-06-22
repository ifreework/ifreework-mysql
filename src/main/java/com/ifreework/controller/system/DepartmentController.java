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
import com.ifreework.entity.system.Department;
import com.ifreework.service.system.DepartmentService;
import com.ifreework.util.StringUtil;



/**    
 *     
 * 类名称：DepartmentController    
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
@RequestMapping("/system/department")
public class DepartmentController extends BaseControllerSupport {

	@Autowired
	private DepartmentService departmentService ;

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
		mv.setViewName("/system/department/view");
		return mv;
	}
	
	@RequestMapping("/query")
	@ResponseBody
	public List<Department> query() {
		PageData pd = this.getPageData();
		List<Department> list = departmentService.queryDepartmentList(pd);
		return list;
	}
	
	@RequestMapping("/add")
	public ModelAndView add(){
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		String treeId = pd.getString("treeId");
		Department department = new Department();
		department.setParentId(treeId);
		mv.addObject("department", department);
		mv.setViewName("/system/department/edit");
		return mv;
	}
	
	@RequestMapping("/update")
	public ModelAndView update(){
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		Department department = departmentService.getDepartmentById(pd.getString("treeId"));
		mv.addObject("department", department);
		mv.setViewName("/system/department/edit");
		return mv;
	}
	
	@RequestMapping(value = "/save")
	@ResponseBody
	public PageData save(@ModelAttribute( "department" )Department department){
		PageData pd;
		if(StringUtil.isEmpty(department.getDepartmentId())){
			pd = departmentService.add(department);
		}else{
			pd = departmentService.update(department);
		}
		
		return pd;
	}
	
	@RequestMapping(value = "/delete")
	@ResponseBody
	public PageData delete(@ModelAttribute( "department" )Department department){
		PageData pd;
		pd = departmentService.delete(department);
		return pd;
	}
}
