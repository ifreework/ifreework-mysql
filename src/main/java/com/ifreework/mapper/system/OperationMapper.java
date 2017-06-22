package com.ifreework.mapper.system;
import java.util.List;

import com.ifreework.common.entity.PageData;
import com.ifreework.entity.system.Operation;

public interface OperationMapper  {
	public List<Operation> queryPageList(PageData pd);
	public Operation getOperationById(String operationId);
	
	public Operation getOperationByPK(String pk);
	public void add(Operation operation);
	public void update(Operation operation);
	public void delete(String operation);
}
