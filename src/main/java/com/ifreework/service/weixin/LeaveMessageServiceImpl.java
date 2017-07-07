package com.ifreework.service.weixin;




import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifreework.common.constant.Constant;
import com.ifreework.common.entity.PageData;
import com.ifreework.entity.weixin.LeaveMessage;
import com.ifreework.mapper.weixin.LeaveMessageMapper;
import com.ifreework.util.StringUtil;


@Service("leaveMessageService")
public class LeaveMessageServiceImpl  implements LeaveMessageService {
	@Autowired
	private LeaveMessageMapper leaveMessageMapper;
	
	@Override
	public List<LeaveMessage> queryList(PageData pd) {
		List<LeaveMessage> list = leaveMessageMapper.queryList(pd);
		return list;
	}

	@Override
	public LeaveMessage getLeaveMessageById(String leaveMessageId) {
		return leaveMessageMapper.getLeaveMessageById(leaveMessageId);
	}

	@Override
	public PageData add(LeaveMessage leaveMessage) {
		String leaveMessageId = StringUtil.uuid();
		leaveMessage.setLeaveMessageId(leaveMessageId);
		leaveMessage.setCreateTime(new Date());
		leaveMessageMapper.add(leaveMessage);
		PageData pd = new PageData();
		pd.put("leaveMessage", leaveMessage);
		pd.setResult(Constant.SUCCESS);
		return pd;
	}

	@Override
	public PageData update(LeaveMessage leaveMessage) {
		leaveMessageMapper.update(leaveMessage);
		PageData pd = new PageData();
		pd.setResult(Constant.SUCCESS);
		return pd;
	}

	@Override
	public PageData delete(String leaveMessageId) {
		leaveMessageMapper.delete(leaveMessageId);
		PageData pd = new PageData();
		pd.setResult(Constant.SUCCESS);
		return pd;
	}
	
	
}