package com.ifreework.service.weixin;


import java.util.List;

import com.ifreework.common.entity.PageData;
import com.ifreework.entity.weixin.LeaveMessage;

public interface LeaveMessageService {
	public List<LeaveMessage> queryList(PageData pd);
	public LeaveMessage getLeaveMessageById(String leaveMessageId);
	public PageData add(LeaveMessage leaveMessage);
	public PageData update(LeaveMessage leaveMessage);
	public PageData delete(String leaveMessageId);
}