package com.ifreework.controller.attendance;


import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ifreework.common.controller.BaseControllerSupport;
import com.ifreework.common.entity.PageData;
import com.ifreework.entity.attendance.LeaveBill;
import com.ifreework.service.attendance.LeaveBillService;
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
@RequestMapping({ "/attendance/leaveBill" })
public class LeaveBillController extends BaseControllerSupport {

	@Autowired
	private LeaveBillService leaveBillService;
	
	/**
	 * 描述：跳转到请假首页
	 * @return 
	 */
	@RequestMapping()
	public ModelAndView gotoView() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("/attendance/leaveBill/list");
		return mv;
	}
	
	/**
	 * 
	 * 描述：跳转到新增页面
	 * @return 
	 */
	@RequestMapping("/add")
	public ModelAndView add() {
		ModelAndView mv = this.getModelAndView();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 1);
		cal.set(Calendar.SECOND, 0);
		
		LeaveBill leaveBill = new LeaveBill();
		cal.set(Calendar.HOUR_OF_DAY, 8);
		cal.set(Calendar.MINUTE, 30);
		leaveBill.setStartTime(cal.getTime());
		cal.set(Calendar.HOUR_OF_DAY, 17);
		
		leaveBill.setEndTime(cal.getTime());
		mv.addObject("leaveBill", leaveBill);
		mv.setViewName("/attendance/leaveBill/edit");
		return mv;
	}
	
	/**
	 * 
	 * 描述：跳转到新增页面
	 * @return 
	 */
	@RequestMapping("/edit")
	public ModelAndView edit() {
		ModelAndView mv = this.getModelAndView();
		String leaveBillId = this.getPageData().getString("leaveBillId");
		LeaveBill leaveBill = leaveBillService.getLeaveBill(leaveBillId);
		mv.addObject("leaveBill", leaveBill);
		mv.setViewName("/attendance/leaveBill/edit");
		return mv;
	}
	
	@RequestMapping(value = "/taskList")
	public ModelAndView taskList() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("/attendance/leaveBill/taskList");
		return mv;
	}
	

	@RequestMapping(value = "/detail")
	public ModelAndView detail() {
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		String leaveBillId = pd.getString("leaveBillId");
		LeaveBill leaveBill = leaveBillService.getLeaveBill(leaveBillId);
		List<Map<String,Object>> list = leaveBillService.queryHistoryTask(leaveBill.getProcessId());
		mv.addObject("historyTaskList", list);
		mv.addObject("leaveBill", leaveBill);
		mv.setViewName("/attendance/leaveBill/detail");
		return mv;
	}
	
	/**
	 * 描述：查询我的请假信息
	 * @return 
	 * @return
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public PageData query(){
		PageData pd = this.getPageData();
		pd = leaveBillService.queryPageList(pd);
		return pd;
	}
	
	@RequestMapping(value = "/save")
	@ResponseBody
	public PageData save(@ModelAttribute("leaveBill") LeaveBill leaveBill) {
		PageData pd;
		if (StringUtil.isEmpty(leaveBill.getLeaveBillId())) {
			pd = leaveBillService.add(leaveBill);
		} else {
			pd = leaveBillService.update(leaveBill);
		}

		return pd;
	}
	
	/**
	 * 描述：删除我的请假信息
	 * @return 
	 * @return
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public PageData delete(){
		PageData pd = this.getPageData();
		pd = leaveBillService.delete(pd.getString("leaveBillId"));
		return pd;
	}
	
	/**
	 * 描述：查询我的待办任务
	 * @return 
	 * @return
	 */
	@RequestMapping(value = "/queyTaskListByName")
	@ResponseBody
	public PageData queyTaskListByName() {
		PageData pd = new PageData();
		List<Map<String,Object>> list = leaveBillService.queyTaskListByName();
		pd.setData(list);
		return pd;
	}
	
	
	/**
	 * 描述：完成当前任务
	 * @return
	 */
	@RequestMapping(value = "/submit")
	@ResponseBody
	public PageData submit(){
		PageData pd = this.getPageData();
		pd = leaveBillService.submit(pd.getString("processId"));
		return pd;
	}
	
	
	/**
	 * 描述：完成当前任务
	 * @return
	 */
	@RequestMapping(value = "/complete")
	@ResponseBody
	public PageData complete(){
		PageData pd = this.getPageData();
		pd = leaveBillService.complete(pd.getString("taskId"), pd.getString("status"),pd.getString("comment"));
		return pd;
	}
	
	
	/**
	 * 描述：通过任务ID，下载任务图片
	 * @return
	 */
	@RequestMapping(value = "/downloadImage")
	@ResponseBody
	public void downloadImage(){
		PageData pd = this.getPageData();
		String processId = pd.getString("processId");
		leaveBillService.downloadImage(processId);
	}
}
