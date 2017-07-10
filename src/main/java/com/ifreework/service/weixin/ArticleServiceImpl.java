package com.ifreework.service.weixin;




import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifreework.common.constant.Constant;
import com.ifreework.common.entity.PageData;
import com.ifreework.common.manager.AttachmentManager;
import com.ifreework.common.manager.UserManager;
import com.ifreework.entity.weixin.Article;
import com.ifreework.mapper.weixin.ArticleMapper;
import com.ifreework.util.NumberUtil;
import com.ifreework.util.StringUtil;


@Service("articleService")
public class ArticleServiceImpl  implements ArticleService {
	@Autowired
	private ArticleMapper articleMapper;
	
	private final String HTML_FILE_PATH = "article/html";
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

	/**
	 * 
	 * 描述：页面访问量修改
	 * @param companyIntroductionId 
	 * @return
	 */
	@Override
	public void pageView(Article article) {
		int pageView = NumberUtil.random(1, 10);
		article.setPageView(article.getPageView() + pageView);
		update(article);
	}
	@Override
	public PageData add(Article article) {
		String articleId = StringUtil.uuid();
		article.setArticleId(articleId);
		article.setCreater(UserManager.getUser().getUserId());
		article.setCreateTime(new Date());
		article.setPageView(NumberUtil.random(20000,30000));
		setImage(article);
		articleMapper.add(article);
		PageData pd = new PageData();
		pd.setResult(Constant.SUCCESS);
		return pd;
	}

	@Override
	public PageData update(Article article) {
		setImage(article);
		articleMapper.update(article);
		PageData pd = new PageData();
		pd.setResult(Constant.SUCCESS);
		return pd;
	}
	
	/**
	 * 
	 * 描述：将下载地址改为下载路径
	 * @param article 
	 * @return
	 */
	private void setImage(Article article){
		String image = article.getImage();
		if(!StringUtil.isEmpty(image)){
			String att = AttachmentManager.getDownloadUrl(image);
			article.setImage(att);
		}
	}
	
	
	private void setHtml(){
		
	}

	@Override
	public PageData delete(String articleId) {
		articleMapper.delete(articleId);
		PageData pd = new PageData();
		pd.setResult(Constant.SUCCESS);
		return pd;
	}
	
	
}