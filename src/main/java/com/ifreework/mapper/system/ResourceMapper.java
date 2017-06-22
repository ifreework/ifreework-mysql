package com.ifreework.mapper.system;
import java.util.List;

import com.ifreework.common.entity.PageData;
import com.ifreework.entity.system.Operation;
import com.ifreework.entity.system.Resource;

public interface ResourceMapper  {
	public List<Resource> queryResourceList(PageData pd);
	public Resource getMaxResourceOrder(String parentId);
	public Resource getResourceByIdAndIsLeaf(String resourceId);
	public Resource getResourceById(String resourceId);
	public Resource getResourceByPK(String pk);
	public Resource getResourceByUrl(String url);
	public List<Resource> selectParentResourceList(String resourceId);
	
	
	public List<Operation> selectOperationWithResourceId(String resourceId);
	public List<Operation> selectNoOperationWithResourceId(String resourceId);
	
	public void add(Resource resource);
	public void update(Resource resource);
	public void delete(String resourceId);
	public void deleteChildren(String resourceId);
}
