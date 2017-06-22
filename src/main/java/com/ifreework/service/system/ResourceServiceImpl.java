package com.ifreework.service.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.ifreework.common.constant.Constant;
import com.ifreework.common.entity.PageData;
import com.ifreework.entity.system.Authority;
import com.ifreework.entity.system.Operation;
import com.ifreework.entity.system.Resource;
import com.ifreework.mapper.system.AuthorityMapper;
import com.ifreework.mapper.system.OperationMapper;
import com.ifreework.mapper.system.ResourceMapper;

@Service("resourceService")
public class ResourceServiceImpl  implements ResourceService {

	@Autowired
	private ResourceMapper resourceMapper;

	@Autowired
	private AuthorityMapper authorityMapper;
	
	@Autowired
	private OperationMapper operationMapper;
	
	@Override
	public List<Resource> queryResourceList(PageData pd) {
		// TODO Auto-generated method stub
		return resourceMapper.queryResourceList(pd);
	}

	public Resource getResourceById(String resourceId){
		return resourceMapper.getResourceById(resourceId);
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
		Resource resource = resourceMapper.getResourceByPK( pd.getString("pk"));
		pd.put("valid", resource == null || resource.getResourceId().equals(pd.getString("resourceId")));
		return pd;
	}
	
	
	/**
	 * 
	 * 描述：查询当前资源所拥有的操作
	 * @Title: selectOperationWithResourceId
	 * @param 
	 * @return   
	 * @throws
	 */
	public List<Operation> selectOperationWithResourceId(String resourceId){
		return resourceMapper.selectOperationWithResourceId(resourceId);
	}
	
	/**
	 * 
	 * 描述：查询当前资源尚未拥有的操作
	 * @Title: selectNoOperationWithResourceId
	 * @param 
	 * @return   
	 * @throws
	 */
	public List<Operation> selectNoOperationWithResourceId(String resourceId){
		return resourceMapper.selectNoOperationWithResourceId(resourceId);
	}
	
	
	@Override
	public PageData add(Resource resource,String addArray) {
		PageData pd = new PageData();
		List<Authority> addList = JSON.parseArray(addArray,Authority.class);
		setResourceOrder(resource);
		resource.setIsLeaf("1");
		resourceMapper.add(resource);
		setParentNotLeaf(resource);
		bathAddAuthority(addList,resource);
		pd.setResult(Constant.SUCCESS);
		return pd;
	}
	
	/**
	 * 
	 * 描述：批量新增操作权限
	 * @Title: bathAddAuthority
	 * @param 
	 * @return   
	 * @throws
	 */
	private void bathAddAuthority(List<Authority> list,Resource resource){
		String resourceName = "";
		String parentId = resource.getParentId();
		List<Resource> resources = resourceMapper.selectParentResourceList(parentId);
		for (int i = resources.size() - 1 ; i >= 0 ; i--) {
			Resource p = resources.get(i);
			resourceName += p.getResourceName() + "-";
		}
		resourceName += resource.getResourceName();
		
		for(Authority auth : list){
			Operation operation = operationMapper.getOperationById(auth.getOperationId());
			auth.setResourceId(resource.getResourceId());
			auth.setPk(resource.getPk() + "-" + operation.getPk());
			auth.setAuthorityName(resourceName + "-" + operation.getOperationName());
			authorityMapper.add(auth);
		}
	}
	
	
	/**
	 * 
	 * 描述：批量新增操作权限
	 * @Title: bathAddAuthority
	 * @param 
	 * @return   
	 * @throws
	 */
	private void bathDeleteAuthority(List<Authority> list,String resourceId){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("resourceId", resourceId);
		map.put("deleteList", list);
		authorityMapper.delete(map);
	}
	
	/**
	 * 
	 * 描述：设置部门ID，默认格式 parent_order + 100
	 * @Title: setResourceId
	 * @param 
	 * @return   
	 * @throws
	 */
	private void setResourceOrder(Resource resource) {
		Resource dept = resourceMapper.getMaxResourceOrder(resource.getParentId());
		if(dept != null){
			resource.setResourceOrder(dept.getResourceOrder() + 1);		
		}else{
			resource.setResourceOrder(0);
		}
	}
	
	/**
	 * 
	 * 描述：如果父节点为子节点的话，修改父节点为非子节点
	 * @Title: setResourceId
	 * @param 
	 * @return   
	 * @throws
	 */
	private void setParentNotLeaf(Resource resource){
		Resource dept = resourceMapper.getResourceByIdAndIsLeaf(resource.getParentId());
		if(dept != null){
			dept.setIsLeaf("0");
			resourceMapper.update(dept);
		}
	}
	
	@Override
	public PageData update(Resource resource,String addArray,String deleteArray) {
		PageData pd = new PageData();
		String resourceId = resource.getResourceId();
		List<Authority> addList = JSON.parseArray(addArray,Authority.class);
		List<Authority> deleteList = JSON.parseArray(deleteArray,Authority.class);
		resourceMapper.update(resource);
		bathAddAuthority(addList, resource);
		bathDeleteAuthority(deleteList, resourceId);
		pd.setResult(Constant.SUCCESS);
		return pd;
	}

	/**
	 * 删除节点
	 */
	@Override
	public PageData delete(Resource resource) {
		// TODO Auto-generated method stub
		PageData pd = new PageData();
		
		
		//如果父节点下没有节点后，修改父节点为子节点
		resource = getResourceById(resource.getResourceId());
		pd.put("id", resource.getParentId());
		List<Resource> deptList = queryResourceList(pd);
		if(deptList == null || deptList.isEmpty()){
			Resource parent = new Resource();
			parent.setIsLeaf("1");
			resourceMapper.update(parent);
		}
		
		//删除子节点
		resourceMapper.deleteChildren(resource.getResourceId());
		resourceMapper.delete(resource.getResourceId());  //删除本节点
		pd.setResult(Constant.SUCCESS);
		return pd;
	}

	
}