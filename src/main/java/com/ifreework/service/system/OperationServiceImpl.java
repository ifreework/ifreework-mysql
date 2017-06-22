package com.ifreework.service.system;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifreework.common.constant.Constant;
import com.ifreework.common.entity.PageData;
import com.ifreework.entity.system.Operation;
import com.ifreework.mapper.system.OperationMapper;



@Service("operationService")
public class OperationServiceImpl  implements OperationService {

	@Autowired
	private OperationMapper operationMapper;

	/**
	 * 根据参数，查询分页后的查询结果，及总共条数
	 * @author 王宜华
	 *
	 * @param operationQo 查询参数
	 * @param startPage 开始页数
	 * @param pageSize 每页条数
	 * @return
	 */
	public PageData queryPageList(PageData pd){
		List<Operation> list = operationMapper.queryPageList(pd);
		pd = new PageData();
		pd.setPagination(list);
		return pd;
	}
	

	/**
	 * 
	 * 描述：根据ID获取operation信息
	 * @Title: getOperationById
	 * @param 
	 * @return   
	 * @throws
	 */
	public Operation getOperationById(String operationId){
		return operationMapper.getOperationById(operationId);
	}
	/**
	 * 新增
	 * @param operationPO
	 */
	public PageData add(Operation operation){
		PageData pd = new PageData();
		operationMapper.add(operation);
		pd.setResult(Constant.SUCCESS);
		return pd;
	}
	
	/**
	 * 修改
	 * @param operationPO
	 */
	public PageData update(Operation operation){
		PageData pd = new PageData();
		operationMapper.update(operation);
		pd.setResult(Constant.SUCCESS);
		return pd;
	}
	
	/**
	 * 删除，该删除方法必须包含ID
	 * @author 王宜华
	 *
	 * @param Operation
	 * @return
	 */
	/**
	 * 修改
	 * @param operationPO
	 */
	public PageData delete(Operation operation){
		PageData pd = new PageData();
		operationMapper.delete(operation.getOperationId());
		pd.setResult(Constant.SUCCESS);
		return pd;
	}


	/**
	 * 
	 * 描述：验证PK值是否已经存在
	 * @Title: validatePK
	 * @param 
	 * @return   
	 * @throws
	 */
	@Override
	public PageData validatePK(PageData pd) {
		Operation operation = operationMapper.getOperationByPK( pd.getString("pk"));
		pd.put("valid", operation == null || operation.getOperationId().equals(pd.getString("operationId")));
		return pd;
	}
}