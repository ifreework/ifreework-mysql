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
import com.ifreework.entity.weixin.AlbumImage;
import com.ifreework.service.weixin.AlbumImageService;

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
public class AlbumImageController extends BaseControllerSupport {
	@Autowired
	private AlbumImageService albumImageService;

	@RequestMapping("/mobile/albumImage")
	public ModelAndView albumImage() {
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		List<AlbumImage> list = albumImageService.queryList(pd);
		mv.addObject("albumImageList", list);
		mv.addObject("userId", pd.getString("m"));
		mv.addObject("albumId", pd.getString("a"));
		mv.setViewName("/mobile/album/albumImage");
		return mv;
	}

	@RequestMapping(value = "/mobile/albumImage/add")
	@ResponseBody
	public PageData add(@ModelAttribute("albumImage") AlbumImage albumImage) {
		PageData pd = albumImageService.add(albumImage);
		return pd;
	}

	@RequestMapping(value = "/mobile/albumImage/delete")
	@ResponseBody
	public PageData delete() {
		PageData pd = this.getPageData();
		pd = albumImageService.delete(pd.getString("albumImageId"));
		return pd;
	}

}
