package com.ifreework.common.manager;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.ifreework.util.StringUtil;


/**
 * 
 * 描述： 服务请求处理API
 * 
 * @author：wangyh qq735789026
 * @创建时间：2016年7月5日 上午11:19:54
 * @修改人：wangyh
 * @修改时间：2016年7月5日 上午11:19:54
 * @version 1.0
 */
public class ServletRequestManager {
	static Logger logger = Logger.getLogger(ServletRequestManager.class);
	private static SimpleDateFormat httpDateFormat = new SimpleDateFormat("EEE, dd MMM yyyyy HH:mm:ss z", Locale.US);

	/**
	 * 从web根目录下获取资源文件
	 * 
	 * @author 王宜华
	 * @param path
	 *            文件路径，从跟目录下开始，Tomcat下path是否以’/'开头无所谓
	 * @return
	 */
	public static InputStream getResourceAsStream(String path) {
		return getHttpServletRequest().getSession().getServletContext().getResourceAsStream(path);
	}

	/**
	 * 系统时间同步
	 * 
	 * @author 王宜华
	 * @param date
	 * @return
	 */
	public static String formatHttpDate(Date date) {
		synchronized (httpDateFormat) {
			return httpDateFormat.format(date);
		}
	}

	/**
	 * 对参数进行URLEncoder转码，编码格式默认UTF-8
	 * 
	 * @author 王宜华
	 * @param str
	 * @return
	 */
	public static String convertEncode(String str) {
		String encodeValue = null;
		try {
			encodeValue = URLEncoder.encode(str, "UTF-8");
			encodeValue = StringUtil.replace(encodeValue, "+", "%20");
			if (encodeValue.length() > 150) {
				encodeValue = new String(str.getBytes("UTF-8"), "ISO8859-1");
				encodeValue = StringUtil.replace(encodeValue, "+", "%20");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encodeValue;
	}

	/**
	 * 
	 * @Title: convertEncode
	 * @Description: TODO(对参数进行URLEncoder转码)
	 * @param charset
	 *            编码格式
	 * @return String
	 */
	public static String convertEncode(String str, String charset) {
		String encodeValue = null;
		try {
			encodeValue = URLEncoder.encode(str, "UTF-8");
			encodeValue = StringUtil.replace(encodeValue, "+", "%20");
			if (encodeValue.length() > 150) {
				encodeValue = new String(str.getBytes(charset), "ISO8859-1");
				encodeValue = StringUtil.replace(encodeValue, "+", "%20");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encodeValue;
	}

	/**
	 * 获取request
	 * 
	 * @author 王宜华
	 * @return HttpServletRequest
	 */
	public static HttpServletRequest getHttpServletRequest() {
		HttpServletRequest request = null;
		try {
			request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e);
		}

		return request;
	}

	/**
	 * 获取HttpServletResponse
	 * 
	 * @author 王宜华
	 * @return HttpServletResponse
	 */
	public static HttpServletResponse getHttpServletResponse() {
		return (HttpServletResponse) getHttpServletRequest().getAttribute("response");
	}

	/**
	 * 获取HttpSession,如果为空，则不返回对象
	 * 
	 * @author 王宜华
	 * @return getHttpSession
	 */
	public static HttpSession getHttpSession() {
		return getHttpServletRequest().getSession();
	}

	/**
	 * 获取HttpSession,如果为空，ture返回新对象，false返回null
	 * 
	 * @author 王宜华
	 * @return
	 */
	public static HttpSession getHttpSession(boolean flag) {
		return getHttpServletRequest().getSession(flag);
	}

	/**
	 * 设置http协议文件头
	 * 
	 * @author 王宜华
	 * @param str
	 */
	public static void setAttachHeader(String str) {
		getHttpServletResponse().setHeader("Content-Disposition", "attachment; filename=" + convertEncode(str));
	}

	public static void setInlineHeader(String str) {
		getHttpServletResponse().setHeader("Content-Disposition", "inline; filename=" + convertEncode(str));
	}

	/**
	 * 讲对象写回到前天页面，通常用于AJAX
	 * 
	 * @author 王宜华
	 * @param obj
	 */
	public static void printHttpServletResponse(String html) {
		ServletOutputStream prw = null;
		try {
			HttpServletResponse res = getHttpServletResponse();
			res.setContentType("text/html;charset=utf-8");
			prw = res.getOutputStream();
			prw.print(JSON.toJSONString(html));
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
			try {
				prw.flush();
				prw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	public static String encodeURL(String url, String encoding) {
		try {
			return URLEncoder.encode(url, encoding);
		} catch (Exception exception) {
		}
		return url;
	}

	public static String encodePathInfo(String pathinfo) {
		String s = encodeURL(pathinfo, "UTF-8");
		char[] chars = s.toCharArray();
		StringBuffer sb = new StringBuffer();
		char c = '\000';
		for (int i = 0; i < chars.length; i++) {
			c = chars[i];
			if (c == '+')
				sb.append("%20");
			else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	public static String decodeURL(String url, String encoding) {
		try {
			return URLDecoder.decode(url, encoding);
		} catch (Exception exception) {
		}
		return url;
	}

	static {
		httpDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
	}

}