package com.ifreework.mapper.weixin;

import java.util.List;

import com.ifreework.common.entity.PageData;
import com.ifreework.entity.weixin.LeaveMessage;


public interface LeaveMessageMapper  {
	public List<LeaveMessage> queryList(PageData pd);
	public LeaveMessage getLeaveMessageById(String leaveMessageId);
	public void add(LeaveMessage leaveMessage);
	public void update(LeaveMessage leaveMessage);
	public void delete(String leaveMessageId);
}
