package com.ifreework.controller.weixin;


import javax.servlet.http.HttpServletResponse;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import com.ifreework.common.controller.BaseControllerSupport;
import com.ifreework.common.entity.PageData;
import com.ifreework.common.manager.ServletRequestManager;
import com.ifreework.service.weixin.WeixinService;
import com.ifreework.util.StringUtil;

/**
 * 
 * 描述：微信接口调用总接口    
 * @author：wangyh
 * @createDate：2017年6月22日
 * @modify：wangyh    
 * @modifyDate：2017年6月22日 
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/weixin")
public class WeixinController extends BaseControllerSupport {

	private static Logger logger = LoggerFactory.getLogger(WeixinController.class);
	
	@Autowired
	private WeixinService weixinService;
	/**
	 * 接口验证,总入口,用于微信公众号绑定时的验签
	 * @param out
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/index")
	public void index() {
		logger.debug("开始验签");
		PageData pd = this.getPageData();
		 HttpServletResponse response = ServletRequestManager.getHttpServletResponse();
		try {
			String signature = pd.getString("signature"); // 微信加密签名
			String timestamp = pd.getString("timestamp"); // 时间戳
			String nonce = pd.getString("nonce"); // 随机数
			
			String echostr = pd.getString("echostr"); // 字符串
			
			boolean sign = weixinService.sign(signature, timestamp, nonce);
			logger.debug("验签结束，验签结果：{}",sign);
			
			if(sign){
				if(!StringUtil.isEmpty(echostr)){ //验证服务器
					 response.getWriter().write(echostr);						// 请求验证成功，返回随机码 
				}else{ //公众号推送信息
					
				}
			}else{
				 response.getWriter().write("error");		
			}
		} catch (Exception e) {
			logger.error("验签失败。{}",e);
		}
	}

}
