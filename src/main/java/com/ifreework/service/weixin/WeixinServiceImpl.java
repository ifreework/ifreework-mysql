package com.ifreework.service.weixin;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.marker.weixin.DefaultSession;
import org.marker.weixin.MySecurity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ifreework.common.manager.WeixinManager;
import com.ifreework.common.weixin.CustomHandleMessageAdapter;
import com.ifreework.entity.system.Config;
import com.ifreework.util.StringUtil;

/**
 * 
 * 描述：    
 * @author：wangyh qq735789026  
 * @创建时间：2016年9月2日 下午2:52:42    
 * @修改人：wangyh    
 * @修改时间：2016年9月2日 下午2:52:42    
 * @version 1.0
 */
@Service("weixinService")
public class WeixinServiceImpl implements WeixinService {
	Logger logger = LoggerFactory.getLogger(WeixinServiceImpl.class);
	
	/**
	 * 
	 * 描述：验签微信回写数据
	 * @param signature 微信加密签名
	 * @param timestamp 时间戳
	 * @param nonce 随机数
	 * @return  true:验签通过，false：验签未通过
	 */
	@Override
	public boolean sign(String signature, String timestamp, String nonce) {
		if (!StringUtil.isEmpty(signature) && !StringUtil.isEmpty(timestamp)
				&& !StringUtil.isEmpty(nonce) ) {/* 接口验证 */
			List<String> list = new ArrayList<String>(3) {
				private static final long serialVersionUID = 2621444383666420433L;

				public String toString() { // 重写toString方法，得到三个参数的拼接字符串
					return this.get(0) + this.get(1) + this.get(2);
				}
			};
			
			
			list.add(Config.init().get(Config.WEIXIN_TOKEN)); // 读取Token(令牌)
			list.add(timestamp);
			list.add(nonce);
			
			Collections.sort(list); // 排序
			String tmpStr = new MySecurity().encode(list.toString(), MySecurity.SHA_1); // SHA-1加密
			return signature.equals(tmpStr);
		}
		return false;
	}
	
	
	/**
	 * 
	 * 描述： 微信菜单创建
	 * @return
	 */
	public void createMenu(){
		JSONObject menu = new JSONObject();
		JSONArray buttons = new JSONArray();
		
		JSONObject button1 = new JSONObject();
		JSONObject button2 = new JSONObject();
		JSONObject button3 = new JSONObject();
		button1.put("type", "view");
		button1.put("name", "申请工作室");
		button1.put("url", "http://wnsx231.gnway.org/ifreework-mysql/mobile/register");
		
		button2.put("type", "view");
		button2.put("name", "登录工作室");
		button2.put("url", "http://wnsx231.gnway.org/ifreework-mysql/mobile/personal");
		
		button3.put("type", "click");
		button3.put("name", "联系我们");
		button3.put("key", "V1001_GOOD");
		buttons.add(button1);
		buttons.add(button2);
		buttons.add(button3);
		menu.put("button", buttons);
		String josn = menu.toJSONString();
		Map<String, Object> param = WeixinManager.createMenu(josn);
		logger.info("菜单创建结果：{}",param);
	}
	
	
	/**
	 * 处理微信服务器发过来的各种消息，包括：文本、图片、地理位置、音乐等等 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void sendMsg(HttpServletRequest request, HttpServletResponse response) throws Exception {
		InputStream is = request.getInputStream();
		OutputStream os = response.getOutputStream();
		final DefaultSession session = DefaultSession.newInstance();
		
		session.addOnHandleMessageListener(new CustomHandleMessageAdapter());

		// 必须调用这两个方法 如果不调用close方法，将会出现响应数据串到其它Servlet中。 
		session.process(is, os); // 处理微信消息
		session.close(); // 关闭Session
	}
	
}
