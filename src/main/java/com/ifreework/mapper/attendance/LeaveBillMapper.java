package com.ifreework.mapper.attendance;
import java.util.List;
import java.util.Map;

import com.ifreework.common.entity.PageData;
import com.ifreework.entity.attendance.LeaveBill;

public interface LeaveBillMapper  {
	public List<Map<String,Object>> queryPageList(PageData pd);
	public LeaveBill getLeaveBill(String leaveBillId);
	public void add(LeaveBill leaveBill);
	public void update(LeaveBill leaveBill);
	public void delete(String leaveBillId);
}
