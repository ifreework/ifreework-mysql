package com.ifreework.service.weixin;




import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifreework.common.constant.Constant;
import com.ifreework.common.entity.PageData;
import com.ifreework.common.manager.AttachmentManager;
import com.ifreework.common.manager.FileManager;
import com.ifreework.common.manager.UserManager;
import com.ifreework.entity.weixin.Article;
import com.ifreework.mapper.weixin.ArticleMapper;
import com.ifreework.util.NumberUtil;
import com.ifreework.util.StringUtil;


@Service("articleService")
public class ArticleServiceImpl  implements ArticleService {
	@Autowired
	private ArticleMapper articleMapper;
	
	private final String HTML_FILE_PATH = "/article/html";
	@Override
	public PageData queryPageList(PageData pd) {
		List<Article> list = articleMapper.queryPageList(pd);
		pd.setPagination(list);
		return pd;
	}

	@Override
	public Article getArticleById(String articleId) {
		Article article = articleMapper.getArticleById(articleId);
		if(!StringUtil.isEmpty(article.getContent())){
			String content = null;
			try {
				content = FileManager.htmlToStr(article.getContent());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			article.setContent(content);
		}
		return article;
	}

	/**
	 * 
	 * 描述：页面访问量修改
	 * @param companyIntroductionId 
	 * @return
	 */
	@Override
	public void pageView(Article article) {
		Article a = new Article();
		int pageView = NumberUtil.random(1, 10);
		a.setPageView(article.getPageView() + pageView);
		a.setArticleId(article.getArticleId());
		articleMapper.update(a);
	}
	@Override
	public PageData add(Article article) throws IOException {
		String articleId = StringUtil.uuid();
		article.setArticleId(articleId);
		article.setCreater(UserManager.getUser().getUserId());
		article.setCreateTime(new Date());
		article.setPageView(NumberUtil.random(20000,30000));
		setImage(article);
		setHtml(article);
		articleMapper.add(article);
		PageData pd = new PageData();
		pd.setResult(Constant.SUCCESS);
		return pd;
	}

	@Override
	public PageData update(Article article) throws IOException {
		setImage(article);
		setHtml(article);
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
	
	/**
	 * 
	 * 描述：将文件内容转换为html进行存储
	 * @param article
	 * @throws IOException 
	 * @return
	 */
	private void setHtml(Article article) throws IOException{
		String content = article.getContent();
		if(!StringUtil.isEmpty(content)){
			content = FileManager.strToHtml(content, HTML_FILE_PATH);
			article.setContent(content);
		}
	}

	@Override
	public PageData delete(String articleId) {
		articleMapper.delete(articleId);
		PageData pd = new PageData();
		pd.setResult(Constant.SUCCESS);
		return pd;
	}
	
	
}