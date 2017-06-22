package com.ifreework.common.alipay.service;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayRequest;
import com.alipay.api.response.AlipayTradeCancelResponse;
import com.alipay.api.response.AlipayTradeCloseResponse;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.ifreework.common.alipay.AlipayConstant;
import com.ifreework.common.alipay.config.AlipayConfigs;
import com.ifreework.common.alipay.model.AlipayTradeRequest;
import com.ifreework.common.alipay.request.AlipayTradeCloseRequestBuilder;
import com.ifreework.common.alipay.request.AlipayTradePrecreateRequestBuilder;
import com.ifreework.common.alipay.request.AlipayTradeQueryRequestBuilder;
import com.ifreework.common.alipay.request.RequestBuilder;
import com.ifreework.common.alipay.utils.AlipayNotify;
import com.ifreework.util.FileUtil;
import com.ifreework.util.ImageUtil;
import com.ifreework.util.PropertiesUtil;
import com.ifreework.util.TwoDimensionCode;

/**
 * 
 * 描述：支付宝业务处理类    
 * @author：wangyh qq735789026  
 * @创建时间：2017年4月4日 下午5:38:16    
 * @修改人：wangyh    
 * @修改时间：2017年4月4日 下午5:38:16    
 * @version 1.0
 */
public class AlipayTradeServiceImpl extends AbsAlipayTradeServiceImpl {
	private Log log = LogFactory.getLog(AlipayTradeServiceImpl.class);

	/**
	 * 创建当面付请求，并获取所生成的二维码
	 */
	@SuppressWarnings("unchecked")
	public AlipayTradePrecreateResponse tradePrecreate(AlipayConfigs alipayConfigs,
			AlipayTradeRequest alipayTradeRequest) throws AlipayApiException {
		AlipayTradePrecreateResponse res = null;
		AlipayClient client = clientBuilder(alipayConfigs);
		RequestBuilder arb = new AlipayTradePrecreateRequestBuilder(alipayTradeRequest, alipayConfigs);
		AlipayRequest<AlipayTradePrecreateResponse> request = arb.builder();

		res = (AlipayTradePrecreateResponse) getResponse(client, request);

		if (res != null) {
			if (AlipayConstant.SUCCESS.equals(res.getCode())) {// 请求二维码成功
				String imgPath = PropertiesUtil.getProperty(FileUtil.getRootPath() ,
						"userImgSavePath");
				imgPath += "/alipay";

				String fileName = String.format("temp/images%sqr-%s.png", File.separator, res.getOutTradeNo());

				String filePath = new StringBuilder(imgPath).append(fileName).toString();
				TwoDimensionCode.encoderQRCode(res.getQrCode(), filePath, "png", 256);
				String baseImg = ImageUtil.getImageStr(filePath);
				log.info("二维码编码：\t" + baseImg);
				res.setQrCode(baseImg);
			}
		} else {
			res = new AlipayTradePrecreateResponse();
			res.setCode(AlipayConstant.NETERROR.toString());
			res.setMsg("网络异常，请稍后再试。");
		}

		return res;
	}


	/**
	 * 根据订单号，查询订单状态
	 */
	@SuppressWarnings("unchecked")
	public AlipayTradeQueryResponse queryTradeResult(AlipayConfigs alipayConfigs, AlipayTradeRequest alipayTradeRequest)
			throws AlipayApiException {

		AlipayTradeQueryResponse res = null;
		AlipayClient client = clientBuilder(alipayConfigs);
		RequestBuilder arb = new AlipayTradeQueryRequestBuilder(alipayTradeRequest, alipayConfigs);
		AlipayRequest<AlipayTradeQueryResponse> request = arb.builder();
		for (int i = 0; i < AlipayConfigs.getMaxqueryretry(); i++) {
			res = (AlipayTradeQueryResponse) getResponse(client, request);
			if (res != null) {
				log.info("单号" + alipayTradeRequest.getOut_trade_no() + "支付宝查询结果: code: " + res.getCode() + ",msg:"
						+ res.getMsg() + ",subcode:" + res.getSubCode() + ",submsg:" + res.getSubMsg());
				return res;
			}
			try {
				Thread.sleep(AlipayConfigs.getQueryduration());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if (res == null) {
			res = new AlipayTradeQueryResponse();
			res.setCode(AlipayConstant.NETERROR.toString());
			res.setMsg("网络异常，请稍后再试。");
		}

		return res;
	}



	/**
	 * 退款请求，该方法暂未实现
	 */
	public AlipayTradeRefundResponse tradeRefund(AlipayConfigs alipayConfigs, AlipayTradeRequest alipayTradeRequest) {
		return null;
	}

	/**
	 * 撤销订单请求，该方法暂未实现
	 */
	@Override
	public AlipayTradeCancelResponse cancelTrade(AlipayConfigs alipayConfigs, AlipayTradeRequest alipayOrder) {
		return null;
	}

	/**
	 * 关闭订单请求，只有订单已经生成且未支付成功的状态下才可进行关闭
	 */
	@SuppressWarnings("unchecked")
	@Override
	public AlipayTradeCloseResponse closeTrade(AlipayConfigs alipayConfigs, AlipayTradeRequest alipayOrder)
			throws AlipayApiException {
		AlipayTradeCloseResponse res = null;
		AlipayClient client = clientBuilder(alipayConfigs);
		RequestBuilder arb = new AlipayTradeCloseRequestBuilder(alipayOrder, alipayConfigs);
		AlipayRequest<AlipayTradeCloseResponse> request = arb.builder();
		for (int i = 0; i < AlipayConfigs.getMaxqueryretry(); i++) {
			res = (AlipayTradeCloseResponse) getResponse(client, request);
			if (res != null) {
				return res;
			}
			try {
				Thread.sleep(AlipayConfigs.getQueryduration());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (res == null) {
			res = new AlipayTradeCloseResponse();
			res.setCode(AlipayConstant.NETERROR.toString());
			res.setMsg("网络异常，请稍后再试。");
		}
		return res;
	}

	/**
	 * 验证支付宝回调接口发送的请求是否支付成功
	 */
	public boolean isTrade(AlipayConfigs alipayConfigs, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (verify(request, alipayConfigs)) {
				response.getWriter().print("success");
				String trade_status = request.getParameter("trade_status");
				return AlipayConstant.TRADE_SUCCESS.equals(trade_status);
			}
		} catch (Exception e) {
			log.error(e);
			// TODO: handle exception
		}
		return false;
	}

	/**
	 * 对支付宝异步通知进行验签
	 * 
	 * @param request
	 * @param response
	 */
	private boolean verify(HttpServletRequest request, AlipayConfigs alipayConfigs) {
		try {
			// 获取支付宝POST过来反馈信息
			Map<String, String> params = getRequestToMap(request);
			log.info("支付宝返回结果：" + params);
			return verify(params, alipayConfigs);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error(e);
			e.printStackTrace();
		}
		return false;
	}


	/**
	 * 
	 * @Title: verify
	 * @Description: TODO(对支付宝回掉函数进行验签，并返回验签结果)
	 * @param params 支付宝请求参数
	 * @param alipayConfigs 支付宝配置参数
	 * @return   
	 * @throws
	 */
	private boolean verify(Map<String, String> params, AlipayConfigs alipayConfigs) {
		return AlipayNotify.verify(params, alipayConfigs);
	}



	/**
	 * 
	 * @Title: getRequestToMap
	 * @Description: TODO(将支付宝异步请求的参数放入map中)
	 * @param 
	 * @return   
	 * @throws
	 */
	private Map<String, String> getRequestToMap(HttpServletRequest request) throws UnsupportedEncodingException {
		Map<String, String> params = new HashMap<String, String>();
		Map<String, String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = iter.next();
			String[] values = requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			// valueStr = new String(valueStr.getBytes("ISO-8859-1"), "UTF-8");
			params.put(name, valueStr);
		}
		return params;
	}


}