package com.ifreework.mapper.system;
import java.util.List;

import com.ifreework.common.entity.PageData;
import com.ifreework.entity.system.Department;

public interface DepartmentMapper  {
	public List<Department> queryDepartmentList(PageData pd);
	public Department getMaxDepartmentOrder(String parentId);
	public Department getDepartmentByIdAndIsLeaf(String departmentId);
	public Department getDepartmentById(String departmentId);
	
	public void add(Department department);
	public void update(Department department);
	public void deleteChildren(String departmentId);
}
