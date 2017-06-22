package com.ifreework.service.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifreework.common.constant.Constant;
import com.ifreework.common.entity.PageData;
import com.ifreework.entity.system.Role;
import com.ifreework.mapper.system.RoleMapper;

@Service("roleService")
public class RoleServiceImpl  implements RoleService {

	@Autowired
	private RoleMapper roleMapper;

	@Override
	public List<Role> queryRoleList(PageData pd) {
		// TODO Auto-generated method stub
		return roleMapper.queryRoleList(pd);
	}

	public Role getRoleById(String roleId){
		return roleMapper.getRoleById(roleId);
	}
	
	@Override
	public PageData add(Role role) {
		PageData pd = new PageData();
		role.setIsLeaf("1");
		roleMapper.add(role);
		setParentNotLeaf(role);
		pd.setResult(Constant.SUCCESS);
		return pd;
	}

	
	/**
	 * 
	 * 描述：如果父节点为子节点的话，修改父节点为非子节点
	 * @Title: setRoleId
	 * @param 
	 * @return   
	 * @throws
	 */
	private void setParentNotLeaf(Role role){
		Role dept = roleMapper.getRoleByIdAndIsLeaf(role.getParentId());
		if(dept != null){
			dept.setIsLeaf("0");
			roleMapper.update(dept);
		}
	}
	
	@Override
	public PageData update(Role role) {
		PageData pd = new PageData();
		roleMapper.update(role);
		pd.setResult(Constant.SUCCESS);
		return pd;
	}

	/**
	 * 删除节点
	 */
	@Override
	public PageData delete(Role role) {
		// TODO Auto-generated method stub
		PageData pd = new PageData();
		
		//如果父节点下没有节点后，修改父节点为子节点
		role = getRoleById(role.getRoleId());
		pd.put("id", role.getParentId());
		List<Role> roleList = queryRoleList(pd);
		if(roleList == null || roleList.isEmpty() || roleList.size() == 1){
			Role parent = new Role();
			parent.setIsLeaf("1");
			roleMapper.update(parent);
		}
		
		//删除子节点
		roleMapper.deleteChildren(role.getRoleId());
		
		roleMapper.delete(role.getRoleId());  //删除本节点
		pd.setResult(Constant.SUCCESS);
		return pd;
	}

	
}