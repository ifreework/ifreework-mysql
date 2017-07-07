package com.ifreework.controller.weixin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ifreework.common.controller.BaseControllerSupport;
import com.ifreework.common.entity.PageData;
import com.ifreework.common.manager.UserManager;
import com.ifreework.entity.system.User;
import com.ifreework.entity.weixin.LeaveMessage;
import com.ifreework.service.weixin.LeaveMessageService;

/**
 * 
 * 描述：微信相册
 * 
 * @author：wangyh
 * @createDate：2017年6月22日
 * @modify：wangyh
 * @modifyDate：2017年6月22日 
 * @version 1.0
 */
@Controller
public class LeaveMessageController extends BaseControllerSupport {
	@Autowired
	private LeaveMessageService leaveMessageService;

	@RequestMapping("/mobile/leaveMessage")
	public ModelAndView leaveMessage() {
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		User user = UserManager.getUser(pd.getString("m"));
		List<LeaveMessage> list = leaveMessageService.queryList(pd);
		mv.addObject("leaveMessageList", list);
		mv.addObject("user", user);
		mv.setViewName("/mobile/leaveMessage/leaveMessage");
		return mv;
	}

	@RequestMapping(value = "/mobile/leaveMessage/add")
	@ResponseBody
	public PageData add(@ModelAttribute("leaveMessage") LeaveMessage leaveMessage) {
		PageData pd = leaveMessageService.add(leaveMessage);
		return pd;
	}

	@RequestMapping(value = "/mobile/leaveMessage/edit")
	@ResponseBody
	public PageData edit(@ModelAttribute("leaveMessage") LeaveMessage leaveMessage) {
		PageData pd = leaveMessageService.update(leaveMessage);
		return pd;
	}

	@RequestMapping(value = "/mobile/leaveMessage/delete")
	@ResponseBody
	public PageData delete() {
		PageData pd = this.getPageData();
		pd = leaveMessageService.delete(pd.getString("leaveMessageId"));
		return pd;
	}

}
