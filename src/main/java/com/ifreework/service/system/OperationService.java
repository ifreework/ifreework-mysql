package com.ifreework.service.system;


import com.ifreework.common.entity.PageData;
import com.ifreework.entity.system.Operation;


public interface OperationService {
	/**
	 * 根据参数，查询分页后的查询结果，及总共条数
	 * @author 王宜华
	 *
	 * @param operationQo 查询参数
	 * @param startPage 开始页数
	 * @param pageSize 每页条数
	 * @return
	 */
	public PageData queryPageList(PageData pd);
	
	/**
	 * 
	 * 描述：根据ID获取operation信息
	 * @Title: getOperationById
	 * @param 
	 * @return   
	 * @throws
	 */
	public Operation getOperationById(String operationId);

	/**
	 * 
	 * 描述：验证PK值是否已经存在
	 * @Title: validatePK
	 * @param 
	 * @return   
	 * @throws
	 */
	public PageData validatePK(PageData pd);
	/**
	 * 新增
	 * @param operationPO
	 */
	public PageData add(Operation operation);
	
	/**
	 * 修改
	 * @param operationPO
	 */
	public PageData update(Operation operation);
	
	
	/**
	 * 删除，该删除方法必须包含ID
	 * @author 王宜华
	 *
	 * @param Operation
	 * @return
	 */
	public PageData delete(Operation operation);

}