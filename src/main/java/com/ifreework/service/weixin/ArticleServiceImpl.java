package com.ifreework.service.weixin;




import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifreework.common.constant.Constant;
import com.ifreework.common.entity.PageData;
import com.ifreework.common.manager.UserManager;
import com.ifreework.entity.weixin.Article;
import com.ifreework.mapper.weixin.ArticleMapper;
import com.ifreework.util.NumberUtil;
import com.ifreework.util.StringUtil;


@Service("articleService")
public class ArticleServiceImpl  implements ArticleService {
	@Autowired
	private ArticleMapper articleMapper;
	
	@Override
	public PageData queryPageList(PageData pd) {
		List<Article> list = articleMapper.queryPageList(pd);
		pd.setPagination(list);
		return pd;
	}

	@Override
	public Article getArticleById(String articleId) {
		return articleMapper.getArticleById(articleId);
	}

	@Override
	public PageData add(Article article) {
		String articleId = StringUtil.uuid();
		article.setArticleId(articleId);
		article.setCreater(UserManager.getUser().getUserId());
		article.setCreateTime(new Date());
		article.setPageView(NumberUtil.random(20000,30000));
		articleMapper.add(article);
		PageData pd = new PageData();
		pd.setResult(Constant.SUCCESS);
		return pd;
	}

	@Override
	public PageData update(Article article) {
		articleMapper.update(article);
		PageData pd = new PageData();
		pd.setResult(Constant.SUCCESS);
		return pd;
	}

	@Override
	public PageData delete(String articleId) {
		articleMapper.delete(articleId);
		PageData pd = new PageData();
		pd.setResult(Constant.SUCCESS);
		return pd;
	}
	
	
}