package com.ifreework.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ifreework.common.controller.BaseControllerSupport;
import com.ifreework.common.entity.PageData;
import com.ifreework.entity.system.Operation;
import com.ifreework.service.system.OperationService;
import com.ifreework.util.StringUtil;

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
@RequestMapping({ "/system/operation" })
public class OperationController extends BaseControllerSupport {

	@Autowired
	private OperationService operationService;

	public OperationService getOperationService() {
		return operationService;
	}

	public void setOperationService(OperationService operationService) {
		this.operationService = operationService;
	}

	/**
	 * 
	 * @Title: gotoView
	 * @Description: TODO(根据用户名查询用户信息，并跳转到修改界面)
	 * @param 
	 * @return   
	 * @throws
	 */
	@RequestMapping()
	public ModelAndView gotoView() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("/system/operation/list");
		return mv;
	}

	@RequestMapping(value = "/query")
	@ResponseBody
	public PageData query() {
		PageData pd = this.getPageData();
		pd = operationService.queryPageList(pd);
		return pd;
	}

	@RequestMapping(value = "/validate")
	@ResponseBody
	public PageData validate() {
		PageData pd = this.getPageData();
		pd = operationService.validatePK(pd);
		return pd;
	}

	@RequestMapping("/add")
	public ModelAndView add() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("/system/operation/edit");
		return mv;
	}

	@RequestMapping("/update")
	public ModelAndView update() {
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		Operation operation = operationService.getOperationById(pd.getString("operationId"));
		mv.addObject("operation", operation);
		mv.setViewName("/system/operation/edit");
		return mv;
	}

	@RequestMapping(value = "/save")
	@ResponseBody
	public PageData save(@ModelAttribute("operation") Operation operation) {
		PageData pd;
		if (StringUtil.isEmpty(operation.getOperationId())) {
			pd = operationService.add(operation);
		} else {
			pd = operationService.update(operation);
		}

		return pd;
	}

	@RequestMapping(value = "/delete")
	@ResponseBody
	public PageData delete(@ModelAttribute("operation") Operation operation) {
		PageData pd;
		pd = operationService.delete(operation);

		return pd;
	}

}
