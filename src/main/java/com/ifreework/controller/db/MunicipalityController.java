package com.ifreework.controller.db;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ifreework.common.controller.BaseControllerSupport;
import com.ifreework.common.entity.PageData;
import com.ifreework.service.db.MunicipalityService;

@Controller
@RequestMapping({ "/db/municipality" })
public class MunicipalityController extends BaseControllerSupport {

	@Autowired
	private MunicipalityService municipalityService;
	
	/**
	 * 
	 * @Title: gotoIndexView 
	 * @Description:
	 * @TODO(用户登录界面跳转，如果已经登录，则跳转到Main页面) 
	 * @param @return @throws
	 */
	@RequestMapping()
	@ResponseBody
	public List<Map<String, String>> query() {
		PageData pd = this.getPageData();
		List<Map<String, String>> list =  municipalityService.queryMunicipalityListMap(pd);
		return list;
	}

}
