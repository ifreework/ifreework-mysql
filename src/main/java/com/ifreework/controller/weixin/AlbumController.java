package com.ifreework.controller.weixin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ifreework.common.controller.BaseControllerSupport;
import com.ifreework.common.entity.PageData;
import com.ifreework.entity.weixin.Album;
import com.ifreework.service.weixin.AlbumService;

/**
 * 
 * 描述：微信相册
 * 
 * @author：wangyh
 * @createDate：2017年6月22日
 * @modify：wangyh
 * @modifyDate：2017年6月22日 
 * @version 1.0
 */
@Controller
public class AlbumController extends BaseControllerSupport {
	@Autowired
	private AlbumService albumService;

	@RequestMapping("/mobile/album")
	public ModelAndView album() {
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		List<Album> list = albumService.queryList(pd);
		mv.addObject("albumList", list);
		mv.addObject("userId", pd.getString("m"));
		mv.setViewName("/mobile/album/album");
		return mv;
	}

	@RequestMapping(value = "/mobile/album/add")
	@ResponseBody
	public PageData add(@ModelAttribute("album") Album album) {
		PageData pd = albumService.add(album);
		return pd;
	}

	@RequestMapping(value = "/mobile/album/edit")
	@ResponseBody
	public PageData edit(@ModelAttribute("album") Album album) {
		PageData pd = albumService.update(album);
		return pd;
	}

	@RequestMapping(value = "/mobile/album/delete")
	@ResponseBody
	public PageData delete() {
		PageData pd = this.getPageData();
		pd = albumService.delete(pd.getString("albumId"));
		return pd;
	}

}
