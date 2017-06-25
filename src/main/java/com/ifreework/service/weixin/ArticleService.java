package com.ifreework.service.weixin;


import com.ifreework.common.entity.PageData;
import com.ifreework.entity.weixin.Article;

public interface ArticleService {
	public PageData queryPageList(PageData pd);
	public Article getArticleById(String articleId);
	public PageData add(Article article);
	public PageData update(Article article);
	public PageData delete(String articleId);

}