package com.ifreework.controller.system;


import java.io.IOException;
import java.util.List;

import org.activiti.engine.repository.Deployment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ifreework.common.constant.Constant;
import com.ifreework.common.controller.BaseControllerSupport;
import com.ifreework.common.entity.PageData;
import com.ifreework.service.system.WorkFlowService;

/**
 * 
 * 描述：    
 * @author：wangyh qq735789026  
 * @createTime：2017年4月11日 下午4:16:41    
 * @editer：wangyh    
 * @editTime：2017年4月11日 下午4:16:41    
 * @version 1.0
 */
@Controller
@RequestMapping({ "/system/workFlow" })
public class WorkFlowController extends BaseControllerSupport {

	@Autowired
	private WorkFlowService workFlowService;

	
	/**
	 * 描述：跳转到流程部署首页
	 * @return 
	 * @return
	 */
	@RequestMapping()
	public ModelAndView gotoView() {
		ModelAndView mv = this.getModelAndView();
		List<Deployment> list = workFlowService.queryDeploymentList();
		mv.addObject("deployments", list);
		mv.setViewName("/system/workFlow/list");
		
		return mv;
	}
	
	/**
	 * 描述：跳转到添加流程页面
	 * @return 
	 * @return
	 */
	@RequestMapping("/add")
	public ModelAndView add() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("/system/workFlow/add");
		return mv;
	}
	
	@RequestMapping(value = "/query")
	@ResponseBody
	public PageData query(){
		PageData pd = this.getPageData();
		List<Deployment> list = workFlowService.queryDeploymentList();
		pd.setData(list);
		return pd;
	}
	
	/**
	 * 
	 * 描述：新增流程文件
	 * @param file zip文件
	 * @return 
	 * @return
	 */
	@RequestMapping(value = "/saveDeploye")
	@ResponseBody
	public PageData saveDeploye(@RequestParam(required=false) MultipartFile[] file){
		try {
			return workFlowService.saveDeploye(file);
		} catch (IOException e) {
			logger.error("File upload error is {}",e);
			PageData pd = new PageData();
			pd.setResult(Constant.FAILED);
			pd.setMsg("系统异常，文件上传失败！");
			return pd;
		}
	}
	
}
