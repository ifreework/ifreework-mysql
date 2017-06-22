package com.ifreework.service.system;

import java.util.List;

import com.ifreework.common.entity.PageData;
import com.ifreework.entity.system.Operation;
import com.ifreework.entity.system.Resource;


public interface ResourceService {
	/**
	 * 根据参数，查询分页后的查询结果，及总共条数
	 * @author 王宜华
	 *
	 * @param resourceQo 查询参数
	 * @param startPage 开始页数
	 * @param pageSize 每页条数
	 * @return
	 */
	public List<Resource> queryResourceList(PageData pd);
	
	public Resource getResourceById(String resourceId);

	/**
	 * 
	 * 描述：查询当前资源所拥有的操作
	 * @Title: selectOperationWithResourceId
	 * @param 
	 * @return   
	 * @throws
	 */
	public List<Operation> selectOperationWithResourceId(String resourceId);
	
	/**
	 * 
	 * 描述：查询当前资源尚未拥有的操作
	 * @Title: selectNoOperationWithResourceId
	 * @param 
	 * @return   
	 * @throws
	 */
	public List<Operation> selectNoOperationWithResourceId(String resourceId);
	
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
	 * @param resourcePO
	 */
	public PageData add(Resource resource,String addArray);
	
	/**
	 * 修改
	 * @param resourcePO
	 */
	public PageData update(Resource resource,String addArray,String deleteArray);
	/**
	 * 删除，该删除方法必须包含ID
	 * @author 王宜华
	 *
	 * @param Resource
	 * @return
	 */
	public PageData delete(Resource resource);

}