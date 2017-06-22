package com.ifreework.common.alipay.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ifreework.common.alipay.config.AlipayConfigs;


/* *
 *类名：AlipayNotify
 *功能：支付宝通知处理类
 *详细：处理支付宝各接口通知返回
 *版本：3.3
 *日期：2017年4月4日 下午5:22:27    
 *说明：
 *************************注意*************************
 *调试通知返回时，可查看或改写log日志的写入TXT里的数据，来检查通知返回是否正常
 */
public class AlipayNotify {

	private static Logger log = Logger.getLogger(AlipayNotify.class);

	/**
	 * 验证消息是否是支付宝发出的合法消息
	 * 
	 * @param params
	 *            通知返回来的参数数组
	 * @param configs
	 *            支付宝配置信息
	 * @return 验证结果
	 */
	public static boolean verify(Map<String, String> params,AlipayConfigs configs) {

		// 判断responsetTxt是否为true，isSign是否为true
		// responsetTxt的结果不是true，与服务器设置问题、合作身份者ID、notify_id一分钟失效有关
		// isSign不是true，与安全校验码、请求时的参数格式（如：带自定义参数等）、编码格式有关
		String responseTxt = "false";
		if (params.get("notify_id") != null) {
			String notify_id = params.get("notify_id");
			responseTxt = verifyResponse(notify_id, configs);
		}
		String sign = "";
		if (params.get("sign") != null) {
			sign = params.get("sign");
		}
		boolean isSign = getSignVeryfy(params,configs, sign);

		// 写日志记录（若要调试，请取消下面两行注释）
		String sWord = "responseTxt=" + responseTxt + "\n isSign=" + isSign
				+ "\n 返回回来的参数：" + AlipayCore.createLinkString(params);
		log.debug(sWord);
		log.info("验签结果isSign：" +  isSign + ",responseTxt:" + responseTxt);
		if (isSign && responseTxt.equals("true")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 根据反馈回来的信息，生成签名结果
	 * 
	 * @param Params
	 *            通知返回来的参数数组
	 * @param configs
	 *            支付宝配置信息
	 * @param sign
	 *            比对的签名结果
	 * @return 生成的签名结果
	 */
	private static boolean getSignVeryfy(Map<String, String> Params,AlipayConfigs configs,
			String sign) {
		// 过滤空值、sign与sign_type参数
		Map<String, String> sParaNew = AlipayCore.paraFilter(Params);
		// 获取待签名字符串
		String preSignStr = AlipayCore.createLinkString(sParaNew);
		
		log.debug("待签名字符串：" +  preSignStr);
		log.debug("待签名sign：" +  sign);
		log.debug("支付宝公钥：" +  configs.getAlipaypublickey());
		
		// 获得签名验证结果
		boolean isSign = false;
		isSign = RSA.verify(preSignStr, sign, configs.getAlipaypublickey(),
				"utf-8");
		
		return isSign;
	}

	/**
	 * 获取远程服务器ATN结果,验证返回URL
	 * 
	 * @param notify_id
	 *            通知校验ID
	 * @param configs
	 *            支付宝配置信息
	 * @return 服务器ATN结果 验证结果集： invalid命令参数不对 出现这个错误，请检测返回处理中partner和key是否为空 true
	 *         返回正确信息 false 请检查防火墙或者是服务器阻止端口问题以及验证时间是否超过一分钟
	 */
	private static String verifyResponse(String notify_id, AlipayConfigs configs) {
		// 获取远程服务器ATN结果，验证是否是支付宝服务器发来的请求
		String partner = configs.getPid();
		String veryfy_url = configs.getHttpsverifyurl() + "partner=" + partner
				+ "&notify_id=" + notify_id;
		log.debug("ATN验证地址：" + veryfy_url);
		return checkUrl(veryfy_url);

	}

	/**
	 * 获取远程服务器ATN结果
	 * 
	 * @param urlvalue
	 *            指定URL路径地址
	 * @return 服务器ATN结果 验证结果集： invalid命令参数不对 出现这个错误，请检测返回处理中partner和key是否为空 true
	 *         返回正确信息 false 请检查防火墙或者是服务器阻止端口问题以及验证时间是否超过一分钟
	 */
	private static String checkUrl(String urlvalue) {
		String inputLine = "";
		try {
			URL url = new URL(urlvalue);
			HttpURLConnection urlConnection = (HttpURLConnection) url
					.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					urlConnection.getInputStream()));
			inputLine = in.readLine().toString();
			log.info("ATN请求结果：" + inputLine);
		} catch (Exception e) {
			log.error("ATN连接异常：",e);
			e.printStackTrace();
			inputLine = "false";
		}

		return inputLine;
	}
}
