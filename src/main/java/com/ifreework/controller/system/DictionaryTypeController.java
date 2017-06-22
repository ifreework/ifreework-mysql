package com.ifreework.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ifreework.common.controller.BaseControllerSupport;
import com.ifreework.common.entity.PageData;
import com.ifreework.entity.system.DictionaryType;
import com.ifreework.service.system.DictionaryTypeService;

/**    
 *     
 * 类名称：DictionaryTypeController    
 * 类描述：    
 * 创建人：王宜华    
 * 创建时间：2014-11-26 下午4:46:18    
 * 修改人：王宜华    
 * 修改时间：2014-11-26 下午4:46:18    
 * 修改备注：    
 * @version     
 *     
 */
@Controller
@RequestMapping("/system/dictionaryType")
public class DictionaryTypeController extends BaseControllerSupport {

	@Autowired
	private DictionaryTypeService dictionaryTypeService;

	@RequestMapping("/add")
	public ModelAndView add() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("/system/dictionaryType/edit");
		return mv;
	}

	@RequestMapping("/update")
	public ModelAndView update() {
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		DictionaryType dictionaryType = dictionaryTypeService.getDictionaryTypeById(pd.getString("treeId"));
		mv.addObject("dictionaryType", dictionaryType);
		mv.setViewName("/system/dictionaryType/edit");
		return mv;
	}

	@RequestMapping(value = "/addSave")
	@ResponseBody
	public PageData addSave(@ModelAttribute("dictionaryType") DictionaryType dictionaryType) {
		PageData pd = dictionaryTypeService.add(dictionaryType);
		return pd;
	}

	@RequestMapping(value = "/editSave")
	@ResponseBody
	public PageData editSave(@ModelAttribute("dictionaryType") DictionaryType dictionaryType) {
		PageData pd = dictionaryTypeService.update(dictionaryType);
		return pd;
	}
}
