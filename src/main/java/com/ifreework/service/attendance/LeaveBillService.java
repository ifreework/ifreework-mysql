package com.ifreework.service.attendance;


import java.util.List;
import java.util.Map;


import com.ifreework.common.entity.PageData;
import com.ifreework.entity.attendance.LeaveBill;

public interface LeaveBillService {
	public PageData queryPageList(PageData pd);
	public LeaveBill getLeaveBill(String leaveBillId);
	public PageData add(LeaveBill leaveBill);
	public PageData update(LeaveBill leaveBill);
	public PageData delete(String leaveBillId);
	public List<Map<String, Object>> queyTaskListByName();
	public PageData complete(String taskId,String status,String comment);
	public PageData submit(String processId);
	
	public void downloadImage(String taskId);
	
	public List<Map<String, Object>> queryHistoryTask(String processId);
}
