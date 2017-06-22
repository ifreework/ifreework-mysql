package com.ifreework.service.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifreework.common.constant.Constant;
import com.ifreework.common.entity.PageData;
import com.ifreework.entity.system.Department;
import com.ifreework.mapper.system.DepartmentMapper;

@Service("departmentService")
public class DepartmentServiceImpl  implements DepartmentService {

	@Autowired
	private DepartmentMapper departmentMapper;

	@Override
	public List<Department> queryDepartmentList(PageData pd) {
		// TODO Auto-generated method stub
		return departmentMapper.queryDepartmentList(pd);
	}

	public Department getDepartmentById(String departmentId){
		return departmentMapper.getDepartmentById(departmentId);
	}
	@Override
	public PageData add(Department department) {
		PageData pd = new PageData();
		setDepartmentOrder(department);
		department.setStatus("1");
		department.setIsLeaf("1");
		departmentMapper.add(department);
		setParentNotLeaf(department);
		pd.setResult(Constant.SUCCESS);
		return pd;
	}
	
	/**
	 * 
	 * 描述：设置部门ID，默认格式 parent_order + 100
	 * @Title: setDepartmentId
	 * @param 
	 * @return   
	 * @throws
	 */
	private void setDepartmentOrder(Department department) {
		Department dept = departmentMapper.getMaxDepartmentOrder(department.getParentId());
		if(dept != null){
			department.setDepartmentOrder(dept.getDepartmentOrder() + 1);		
		}else{
			department.setDepartmentOrder(0);
		}
	}
	
	/**
	 * 
	 * 描述：如果父节点为子节点的话，修改父节点为非子节点
	 * @Title: setDepartmentId
	 * @param 
	 * @return   
	 * @throws
	 */
	private void setParentNotLeaf(Department department){
		Department dept = departmentMapper.getDepartmentByIdAndIsLeaf(department.getParentId());
		if(dept != null){
			dept.setIsLeaf("0");
			departmentMapper.update(dept);
		}
	}
	
	@Override
	public PageData update(Department department) {
		PageData pd = new PageData();
		departmentMapper.update(department);
		pd.setResult(Constant.SUCCESS);
		return pd;
	}

	/**
	 * 删除节点
	 */
	@Override
	public PageData delete(Department department) {
		// TODO Auto-generated method stub
		PageData pd = new PageData();
		department.setStatus("0");
		departmentMapper.update(department);  //删除本节点
		
		//如果父节点下没有节点后，修改父节点为子节点
		department = getDepartmentById(department.getDepartmentId());
		pd.put("id", department.getParentId());
		List<Department> deptList = queryDepartmentList(pd);
		if(deptList == null || deptList.isEmpty()){
			Department parent = new Department();
			parent.setIsLeaf("1");
			departmentMapper.update(parent);
		}
		
		//删除子节点
		departmentMapper.deleteChildren(department.getDepartmentId());
		pd.setResult(Constant.SUCCESS);
		return pd;
	}

	
}