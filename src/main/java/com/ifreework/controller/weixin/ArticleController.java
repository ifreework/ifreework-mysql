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
import com.ifreework.common.manager.UserManager;
import com.ifreework.entity.system.Dictionary;
import com.ifreework.entity.system.User;
import com.ifreework.entity.weixin.Article;
import com.ifreework.service.system.DictionaryService;
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
public class ArticleController extends BaseControllerSupport {
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private DictionaryService dictionaryService;

	@RequestMapping("/weixin/article")
	public ModelAndView gotoView() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("/weixin/article/list");
		return mv;
	}
	
	/**
	 * 
	 * 描述：跳转到文章页面
	 */
	@RequestMapping(value = "/mobile/articleList")
	public ModelAndView articleList() {
		ModelAndView mv = new ModelAndView();
		PageData pd = this.getPageData();
		String userId = pd.getString("m");
		
		pd.put("dictionaryTypeId", "weixin");
		pd.put("code", "article");
		
		List<Dictionary> dList = dictionaryService.queryByCodeList(pd);
		User user = UserManager.getUser(userId);
		mv.addObject("user", user);
		mv.addObject("dList", dList);
		mv.setViewName("/mobile/article/articleList");
		return mv;
	}
	
	/**
	 * 
	 * 描述：跳转到文章详情
	 */
	@RequestMapping(value = "/mobile/articleInfo")
	public ModelAndView articleInfo() {
		ModelAndView mv = new ModelAndView();
		PageData pd = this.getPageData();
		String userId = pd.getString("m");
		String articleId = pd.getString("p");
		User user = UserManager.getUser(userId);
		Article article = articleService.getArticleById(articleId);
		mv.addObject("user", user);
		mv.addObject("article", article);
		mv.setViewName("/mobile/article/articleInfo");
		return mv;
	}
	
	/**
	 * 
	 * 描述：跳转到文章页面
	 */
	@RequestMapping(value = "/mobile/articleList/query")
	@ResponseBody
	public PageData mobileQuery() {
		PageData pd = this.getPageData();
		return articleService.queryPageList(pd);
	}

	@RequestMapping("/weixin/article/add")
	public ModelAndView add() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("/weixin/article/edit");
		return mv;
	}

	@RequestMapping(value = "/weixin/article/edit")
	public ModelAndView edit() {
		ModelAndView mv = this.getModelAndView();
		String articleId = this.getPageData().getString("articleId");
		Article article = articleService.getArticleById(articleId);
		mv.addObject("article", article);
		mv.setViewName("/weixin/article/edit");
		return mv;
	}

	@RequestMapping("/weixin/article/img")
	public ModelAndView img() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("/weixin/article/img");
		return mv;
	}

	@RequestMapping(value = "/weixin/article/query")
	@ResponseBody
	public PageData query() {
		PageData pd = this.getPageData();
		return articleService.queryPageList(pd);
	}

	@RequestMapping(value = "/weixin/article/save")
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

	@RequestMapping(value = "/weixin/article/delete")
	@ResponseBody
	public PageData delete() {
		PageData pd = this.getPageData();
		pd = articleService.delete(pd.getString("articleId"));
		return pd;
	}

}
