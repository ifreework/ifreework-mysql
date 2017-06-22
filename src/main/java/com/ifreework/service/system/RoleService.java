package com.ifreework.service.system;

import java.util.List;

import com.ifreework.common.entity.PageData;
import com.ifreework.entity.system.Role;


public interface RoleService {
	/**
	 * 根据参数，查询分页后的查询结果，及总共条数
	 * @author 王宜华
	 *
	 * @param departmentQo 查询参数
	 * @param startPage 开始页数
	 * @param pageSize 每页条数
	 * @return
	 */
	public List<Role> queryRoleList(PageData pd);
	
	public Role getRoleById(String departmentId);

	/**
	 * 新增
	 * @param departmentPO
	 */
	public PageData add(Role department);
	
	/**
	 * 修改
	 * @param departmentPO
	 */
	public PageData update(Role department);
	/**
	 * 删除，该删除方法必须包含ID
	 * @author 王宜华
	 *
	 * @param Role
	 * @return
	 */
	public PageData delete(Role department);

}