package com.ifreework.service.attendance;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifreework.common.constant.Constant;
import com.ifreework.common.entity.PageData;
import com.ifreework.common.manager.ServletRequestManager;
import com.ifreework.common.manager.UserManager;
import com.ifreework.entity.attendance.LeaveBill;
import com.ifreework.mapper.attendance.LeaveBillMapper;
import com.ifreework.util.StringUtil;

@Service("leaveBillService")
public class LeaveBillServiceImpl implements LeaveBillService {

	private static final String ACTIVITI_LEAVE_BILL_KEY = "leave_bill";
	@Autowired
	private LeaveBillMapper leaveBillMapper;

	@Autowired
	private RepositoryService repositoryService;

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private TaskService taskService;

	@Autowired
	private HistoryService historyService;

	public LeaveBill getLeaveBill(String leaveBillId) {
		return leaveBillMapper.getLeaveBill(leaveBillId);
	}

	/**
	 * 
	 * 描述：查询当前用户的请假列表
	 * @param pd
	 * @return
	 */
	@Override
	public PageData queryPageList(PageData pd) {
		pd.put("userId", UserManager.getUser().getUserId());
		List<Map<String, Object>> list = leaveBillMapper.queryPageList(pd);
		for (Map<String, Object> map : list) {
			// 获取当前任务节点名称
			String processId = (String) map.get("processId");

			String taskName = getBillStatus(processId);
			map.put("taskName", taskName);
			// 获取最后一个任务节点操作人，如果当前是第一个任务节点
			String lastOperationUser = getLastOperationUser(processId);
			map.put("lastOperationUser", lastOperationUser);
		}
		pd.setPagination(list);
		return pd;
	}

	/**
	 * 描述：获取当前实例最后一个操作人
	 * @param processId 实例ID
	 * @return 
	 */
	private String getLastOperationUser(String processId) {
		String lastOperationUser;

		List<HistoricTaskInstance> taskList = historyService.createHistoricTaskInstanceQuery()
				.processInstanceId(processId).taskDeleteReason("completed").orderByHistoricTaskInstanceEndTime().desc()
				.listPage(0, 1);
		if (taskList == null || taskList.isEmpty()) {
			lastOperationUser = UserManager.getUser().getPersonName();
		} else {
			HistoricTaskInstance task = taskList.get(0);
			String userId = task.getAssignee();
			lastOperationUser = UserManager.getUser(userId).getPersonName();
		}
		return lastOperationUser;
	}

	/**
	 * 
	 * 描述：获取当前实例的单据状态
	 * @param processId  实力ID
	 * @return
	 */
	private String getBillStatus(String processId) {
		List<Task> taskList = taskService.createTaskQuery().processInstanceId(processId).list();
		String taskName = "";
		if (taskList.isEmpty()) {
			return "已完成";
		}
		for (Task task : taskList) {
			if (StringUtil.isEmpty(taskName)) {
				taskName = task.getName();
			} else {
				taskName += "," + task.getName();
			}
		}
		return taskName;
	}

	@Override
	public PageData add(LeaveBill leaveBill) {
		leaveBill.setStatus("0");
		leaveBill.setUserId(UserManager.getUser().getUserId());
		leaveBill.setCreateTime(new Date());
		leaveBillMapper.add(leaveBill);
		String processId = saveStartProcess(leaveBill.getLeaveBillId());
		leaveBill.setProcessId(processId);
		return update(leaveBill);
	}

	@Override
	public PageData update(LeaveBill leaveBill) {
		PageData pd = new PageData();
		leaveBillMapper.update(leaveBill);
		pd.setResult(Constant.SUCCESS);
		return pd;
	}

	@Override
	public PageData delete(String leaveBillId) {
		PageData pd = new PageData();
		leaveBillMapper.delete(leaveBillId);
		deleteTask("");
		pd.setResult(Constant.SUCCESS);
		return pd;
	}

	private String saveStartProcess(String leaveBillId) {
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("submitUser", UserManager.getUser().getUserId());// 提交人
		variables.put("secondUser", "1");// 初审人，admin
		variables.put("thirdUser1", "9991f4d7782a4ccfb8a65bd96ea7aafa");// 部门经理审批
																		// lisi
		variables.put("thirdUser2", "e29149962e944589bb7da23ad18ddeed");// 总经理审批
																		// zhangsan
		variables.put("leaveBillId", leaveBillId);
		variables.put("status", "0");
		return runtimeService.startProcessInstanceByKey(ACTIVITI_LEAVE_BILL_KEY, variables).getId();
	}

	/**
	 * 
	 * 描述：个人提报请假申请
	 * @param executionId 
	 * @return
	 */
	public PageData submit(String processId) {

		String userId = UserManager.getUser().getUserId();
		List<Task> tasks = taskService.createTaskQuery()//
				.taskAssignee(userId)// 指定个人任务查询
				.processInstanceId(processId).list();
		if (tasks != null && !tasks.isEmpty()) {
			Task task = tasks.get(0);
			return complete(task.getId(), "1","");
		} else {
			PageData pd = new PageData();
			pd.setResult(Constant.FAILED);
			pd.setMsg("当前请假任务不存在，提报失败");
			return pd;
		}
	}

	/**
	 * 描述：根据taskId,完成当前任务
	 * @param taskId 任务ID
	 * @param status  审批状态，0 未通过 1通过
	 * @return
	 */
	public PageData complete(String taskId, String status,String comment) {
		PageData pd = new PageData();
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		
		String leaveBillId = (String) taskService.getVariable(taskId, "leaveBillId");

		taskService.setVariableLocal(taskId, "status", status);

		if ("0".equals(status)) {  //如果审批未通过，删除其他审批任务
			deleteTask(taskId);
		}
		
		if(!StringUtil.isEmpty(comment)){ //添加批注
			taskService.addComment(taskId, task.getProcessInstanceId(), comment);
		}
		
		LeaveBill leaveBill = getLeaveBill(leaveBillId);
		leaveBill.setStatus(status);
		update(leaveBill);
		taskService.complete(taskId);
		pd.setResult(Constant.SUCCESS);
		return pd;
	}

	/**
	 * 描述：删除其他分支的流程
	 * @param notDeleteTaskId 保存的流程，当notDeleteTaskId = “” 时，删除全部流程
	 * @return
	 */
	private void deleteTask(String notDeleteTaskId) {
		List<Task> list = taskService.createTaskQuery().taskId(notDeleteTaskId).list();
		if (list != null && !list.isEmpty()) {
			List<Task> tasks = taskService.createTaskQuery().processInstanceId(list.get(0).getProcessInstanceId())
					.list();
			for (Task task : tasks) {
				String taskId = task.getId();
				if(!notDeleteTaskId.equals(taskId)){
					taskService.setVariableLocal(taskId, "status", "0");
					taskService.complete(taskId);
				}
			}
		}
	}

	/**
	 * 描述：查询个人待办任务
	 * @return 
	 * @return
	 */
	public List<Map<String, Object>> queyTaskListByName() {
		String userId = UserManager.getUser().getUserId();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		List<Task> tasks = taskService.createTaskQuery()//
				.taskAssignee(userId)// 指定个人任务查询
				.processDefinitionKey(ACTIVITI_LEAVE_BILL_KEY).orderByTaskCreateTime().asc()//
				.list();

		for (Task task : tasks) {
			Map<String, Object> map = new HashMap<String, Object>();
			String taskId = task.getId();
			String leaveBillId = (String) taskService.getVariable(taskId, "leaveBillId");
			LeaveBill leaveBill = getLeaveBill(leaveBillId);
			if ("1".equals(leaveBill.getStatus())) {
				map.put("taskId", taskId);
				map.put("taskName", task.getName());
				map.put("leaveBill", leaveBill);
				String lastOperationUser = getLastOperationUser(leaveBill.getProcessId());
				map.put("lastOperationUser", lastOperationUser);

				list.add(map);
			}
		}

		return list;
	}

	
	
	/**
	 * 描述：下载流程图
	 * @param processId 当前实例ID
	 * @return
	 */
	public void downloadImage(String processId) {
		
		ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processId).singleResult();
		ProcessDefinition pd = repositoryService.getProcessDefinition(processInstance.getProcessDefinitionId());

		String deploymentId = pd.getDeploymentId();
		String imageName = pd.getDiagramResourceName();

		InputStream in = repositoryService.getResourceAsStream(deploymentId, imageName);

		HttpServletResponse resp = ServletRequestManager.getHttpServletResponse();
		try {
			OutputStream out = resp.getOutputStream();
			// 把图片的输入流程写入resp的输出流中
			byte[] b = new byte[1024];
			for (int len = -1; (len = in.read(b)) != -1;) {
				out.write(b, 0, len);
			}
			// 关闭流
			out.close();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Map<String, Object> getCurrentActivityCoordinates(String taskId) {
		Map<String, Object> coordinates = new HashMap<String, Object>();
		// 1. 获取到当前活动的ID
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId())
				.singleResult();
		String currentActivitiId = pi.getActivityId();
		// 2. 获取到流程定义
		ProcessDefinitionEntity pd = (ProcessDefinitionEntity) repositoryService
				.getProcessDefinition(task.getProcessDefinitionId());
		// 3. 使用流程定义通过currentActivitiId得到活动对象
		ActivityImpl activity = pd.findActivity(currentActivitiId);
		// 4. 获取活动的坐标
		coordinates.put("x", activity.getX());
		coordinates.put("y", activity.getY());
		coordinates.put("width", activity.getWidth());
		coordinates.put("height", activity.getHeight());
		return coordinates;
	}

	/**
	 * 描述：查询流程实例的历史任务
	 * @param processId
	 * @return 
	 * @return
	 */
	public List<Map<String, Object>> queryHistoryTask(String processId) {
		List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery().processInstanceId(processId).taskDeleteReason("completed")
				.orderByTaskCreateTime().desc().list();

		List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
		for (HistoricTaskInstance task : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("taskId", task.getId());
			map.put("taskName", task.getName());
			map.put("taskUser", UserManager.getUser(task.getAssignee()).getPersonName());
			map.put("endTime", task.getEndTime());

			HistoricVariableInstance vi = historyService.createHistoricVariableInstanceQuery().taskId(task.getId())
					.variableName("status").singleResult();

			if (vi != null) {
				map.put("status", vi.getValue());
			}

			List<Comment> comments = taskService.getTaskComments(task.getId());
			String commentStr = "";
			for (Comment comment : comments) {
				if (StringUtil.isEmpty(commentStr)) {
					commentStr = comment.getFullMessage();
				} else {
					commentStr += "," + comment.getFullMessage();
				}
			}

			map.put("comment", commentStr);
			maps.add(map);
		}
		return maps;
	}

}