package com.ifreework.controller.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ifreework.common.controller.BaseControllerSupport;
import com.ifreework.common.entity.PageData;
import com.ifreework.entity.system.Operation;
import com.ifreework.entity.system.Resource;
import com.ifreework.service.system.ResourceService;
import com.ifreework.util.StringUtil;



/**    
 *     
 * 类名称：ResourceController    
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
@RequestMapping("/system/resource")
public class ResourceController extends BaseControllerSupport {

	@Autowired
	private ResourceService resourceService ;

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
		mv.setViewName("/system/resource/list");
		return mv;
	}
	
	@RequestMapping("/query")
	@ResponseBody
	public PageData query() {
		PageData pd = this.getPageData();
		List<Resource> list = resourceService.queryResourceList(pd);
		pd = new PageData();
		pd.setPagination(list);
		return pd;
	}
	
	@RequestMapping(value = "/validate")
	@ResponseBody
	public PageData validate() {
		PageData pd = this.getPageData();
		pd = resourceService.validatePK(pd);
		return pd;
	}
	
	@RequestMapping("/add")
	public ModelAndView add(){
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		String resourceId = pd.getString("resourceId");
		Resource resource = new Resource();
		resource.setParentId(StringUtil.isEmpty(resourceId) ? "0" : resourceId);
		if(!StringUtil.isEmpty(resourceId)){
			Resource parent = resourceService.getResourceById(resourceId);
			resource.setPk(parent.getPk());
		}
		List<Operation> hasOpts = resourceService.selectOperationWithResourceId("0");
		List<Operation> notHasOpts = resourceService.selectNoOperationWithResourceId("0");
		mv.addObject("resource", resource);
		mv.addObject("hasOpts", hasOpts);
		mv.addObject("notHasOpts", notHasOpts);
		mv.setViewName("/system/resource/edit");
		return mv;
	}
	
	@RequestMapping("/update")
	public ModelAndView update(){
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		String resourceId = pd.getString("resourceId");
		Resource resource = resourceService.getResourceById(resourceId);
		List<Operation> hasOpts = resourceService.selectOperationWithResourceId(resourceId);
		List<Operation> notHasOpts = resourceService.selectNoOperationWithResourceId(resourceId);
		
		
		mv.addObject("resource", resource);
		mv.addObject("hasOpts", hasOpts);
		mv.addObject("notHasOpts", notHasOpts);
		mv.setViewName("/system/resource/edit");
		return mv;
	}
	
	@RequestMapping(value = "/save")
	@ResponseBody
	public PageData save(@ModelAttribute( "resource" )Resource resource,@RequestParam( "addArray" ) String addArray,@RequestParam( "deleteArray" ) String deleteArray){
		PageData pd;
		if(StringUtil.isEmpty(resource.getResourceId())){
			pd = resourceService.add(resource,addArray);
		}else{
			pd = resourceService.update(resource,addArray,deleteArray);
		}
		
		return pd;
	}
	
	@RequestMapping(value = "/delete")
	@ResponseBody
	public PageData delete(@ModelAttribute( "resource" )Resource resource){
		PageData pd;
		pd = resourceService.delete(resource);
		return pd;
	}
}
