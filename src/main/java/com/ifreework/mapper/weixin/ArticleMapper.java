package com.ifreework.mapper.weixin;

import java.util.List;

import com.ifreework.common.entity.PageData;
import com.ifreework.entity.weixin.Article;


public interface ArticleMapper  {
	public List<Article> queryPageList(PageData pd);
	public Article getArticleById(String articleId);
	public void add(Article article);
	public void update(Article article);
	public void delete(String articleId);
}
