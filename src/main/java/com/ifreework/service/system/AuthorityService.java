package com.ifreework.service.system;


import com.ifreework.common.entity.PageData;


public interface AuthorityService {
	/**
	 * 根据参数，查询分页后的查询结果，及总共条数
	 * @author 王宜华
	 *
	 * @param authorityQo 查询参数
	 * @param startPage 开始页数
	 * @param pageSize 每页条数
	 * @return
	 */
	public PageData queryPageList(PageData pd);

}