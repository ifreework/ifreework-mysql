package com.ifreework.controller.weixin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ifreework.common.controller.BaseControllerSupport;
import com.ifreework.common.entity.PageData;
import com.ifreework.entity.weixin.Article;
import com.ifreework.service.weixin.ArticleService;
import com.ifreework.util.StringUtil;

/**
 * 
 * 描述：微信接口调用总接口
 * 
 * @author：wangyh
 * @createDate：2017年6月22日 @modify：wangyh
 * @modifyDate：2017年6月22日 @version 1.0
 */
@Controller
@RequestMapping(value = "/weixin/article")
public class ArticleController extends BaseControllerSupport {
	@Autowired
	private ArticleService articleService;

	@RequestMapping()
	public ModelAndView gotoView() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("/weixin/article/list");
		return mv;
	}

	@RequestMapping("/add")
	public ModelAndView add() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("/weixin/article/edit");
		return mv;
	}

	@RequestMapping(value = "/edit")
	public ModelAndView edit() {
		ModelAndView mv = this.getModelAndView();
		String articleId = this.getPageData().getString("articleId");
		Article article = articleService.getArticleById(articleId);
		mv.addObject("article", article);
		mv.setViewName("/weixin/article/edit");
		return mv;
	}

	@RequestMapping("/img")
	public ModelAndView img() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("/weixin/article/img");
		return mv;
	}

	@RequestMapping(value = "/query")
	@ResponseBody
	public PageData query() {
		PageData pd = this.getPageData();
		return articleService.queryPageList(pd);
	}

	@RequestMapping(value = "/save")
	@ResponseBody
	public PageData save(@ModelAttribute("article") Article article) {
		PageData pd;
		if (StringUtil.isEmpty(article.getArticleId())) {
			pd = articleService.add(article);
		} else {
			pd = articleService.update(article);
		}
		return pd;
	}

	@RequestMapping(value = "/delete")
	@ResponseBody
	public PageData delete() {
		PageData pd = this.getPageData();
		pd = articleService.delete(pd.getString("articleId"));
		return pd;
	}

}
