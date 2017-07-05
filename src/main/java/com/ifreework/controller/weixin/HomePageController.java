package com.ifreework.controller.weixin;


import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriUtils;

import com.google.zxing.WriterException;
import com.ifreework.common.controller.BaseControllerSupport;
import com.ifreework.common.entity.PageData;
import com.ifreework.common.manager.ServletRequestManager;
import com.ifreework.common.manager.UserManager;
import com.ifreework.entity.system.User;
import com.ifreework.util.HttpRequestUtil;
import com.ifreework.util.ImageUtil;
import com.ifreework.util.QRCodeUtil;
import com.ifreework.util.StringUtil;

/**
 * 描述：微信接口调用总接口
 * 
 * @author：wangyh
 * @createDate：2017年6月22日 @modify：wangyh
 * @modifyDate：2017年6月22日 @version 1.0
 */
@Controller
public class HomePageController extends BaseControllerSupport {

	/**
	 * 
	 * 描述：跳转到微主页页面
	 */
	@RequestMapping(value = "/mobile/homePage")
	public ModelAndView homePage() {
		ModelAndView mv = new ModelAndView();
		PageData pd = this.getPageData();
		String userId = pd.getString("m");
		User user = UserManager.getUser(userId);
		mv.addObject("user", user);
		mv.setViewName("/mobile/homePage/homePage");
		return mv;
	}
	
	/**
	 * 描述：跳转到个人名片页面
	 */
	@RequestMapping(value = "/mobile/card")
	public ModelAndView card() {
		ModelAndView mv = new ModelAndView();
		PageData pd = this.getPageData();
		String userId = pd.getString("m");
		User user = UserManager.getUser(userId);
		mv.addObject("user", user);
		mv.setViewName("/mobile/homePage/card");
		return mv;
	}
	
	/**
	 * 描述：跳转到个人名片页面
	 */
	@RequestMapping(value = "/mobile/mzsm")
	public ModelAndView mzsm() {
		ModelAndView mv = new ModelAndView();
		PageData pd = this.getPageData();
		String userId = pd.getString("m");
		User user = UserManager.getUser(userId);
		mv.addObject("user", user);
		mv.setViewName("/mobile/homePage/mzsm");
		return mv;
	}
	
	/**
	 * 描述：跳转到个人名片页面
	 * @throws IOException 
	 * @throws WriterException 
	 */
	@RequestMapping(value = "/mobile/card/qrCode")
	public void qrCode() throws WriterException, IOException {
		HttpServletResponse response = ServletRequestManager.getHttpServletResponse();
		PageData pd = this.getPageData();
		
		
		String userId = pd.getString("m");
		User user = UserManager.getUser(userId);
		String image = user.getImgPath();
		String url = pd.getString("url");
		url = UriUtils.decode(url, "UTF-8");
		
		if(!StringUtil.isEmpty(image)){
			if(image.startsWith("http")){
				InputStream in = HttpRequestUtil.httpRequestToInputStream(image);
				BufferedImage img = QRCodeUtil.encode(url, 600, 600, in);
				download(response, img);
			}else{
				image = image.substring(image.indexOf("base64,") + 7);
				BufferedImage img = ImageUtil.decode(image);
				img = QRCodeUtil.encode(url, 600, 600, ImageUtil.bufferedImageToInputStream(img));
				download(response, img);
			}
		}
		
		
	}

	private void download(HttpServletResponse response, BufferedImage img) throws IOException {
		InputStream in = ImageUtil.bufferedImageToInputStream(img);
		byte[] bytes = IOUtils.toByteArray(in);
		response.reset();
		response.addHeader("Content-Length", "" + bytes.length);
		response.setContentType("application/octet-stream;charset=UTF-8");
		OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
		outputStream.write(bytes);
		outputStream.flush();
		outputStream.close();
	}

}
