package com.ifreework.service.weixin;


import java.io.IOException;

import com.ifreework.common.entity.PageData;
import com.ifreework.entity.weixin.Article;

public interface ArticleService {
	public PageData queryPageList(PageData pd);
	public Article getArticleById(String articleId);
	public PageData add(Article article) throws IOException;
	public PageData update(Article article) throws IOException;
	public PageData delete(String articleId);
	public void pageView(Article article);

}