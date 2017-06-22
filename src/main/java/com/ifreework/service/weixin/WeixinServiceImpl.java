package com.ifreework.service.weixin;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.marker.weixin.DefaultSession;
import org.marker.weixin.MySecurity;
import org.springframework.stereotype.Service;

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
@Service("countyService")
public class WeixinServiceImpl implements WeixinService {
	
	
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
