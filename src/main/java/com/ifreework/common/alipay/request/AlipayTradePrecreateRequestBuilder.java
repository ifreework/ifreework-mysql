package com.ifreework.common.alipay.request;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayRequest;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.ifreework.common.alipay.config.AlipayConfigs;
import com.ifreework.common.alipay.exception.AlipayException;
import com.ifreework.common.alipay.model.AlipayTradeRequest;

/**
 * 
 * 描述：    建立扫码支付订单请求
 * @author：wangyh qq735789026  
 * @创建时间：2017年4月4日 下午4:08:49    
 * @修改人：wangyh    
 * @修改时间：2017年4月4日 下午4:08:49    
 * @version 1.0
 */
public class AlipayTradePrecreateRequestBuilder extends RequestBuilder {
	private Log log = LogFactory.getLog(AlipayTradePrecreateRequestBuilder.class);

	public AlipayTradePrecreateRequestBuilder(AlipayTradeRequest alipayTradeRequest,AlipayConfigs alipayConfigs) {
		super(alipayTradeRequest, alipayConfigs);
	}

	/**
	 * 建立扫码支付请求
	 */
	@SuppressWarnings({"rawtypes" })
	public AlipayRequest builder() {
		// TODO: implement
		AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
		if (validate()) {
			// 设置平台参数
			request.setNotifyUrl(getAlipayConfigs().getNotifyurl());
			// 设置业务参数
			request.setBizContent(getBizContent());
			log.info("trade.pay bizContent:" + request.getBizContent());
		}
		return request;
	}

	/**
	 * 获取订单请求附加域参数
	 */
	public String getBizContent() {
		// TODO: implement
		AlipayTradeRequest alipayTradeRequest = getAlipayTradeRequest();
		alipayTradeRequest.setTimeout_express(AlipayConfigs.getTimeoutexpress());
		return JSONObject.toJSONString(alipayTradeRequest);
	}

	
	/**
	 * 校验请求参数是否正确
	 */
	public boolean validate() {
		// TODO: implement
		AlipayTradeRequest alipayTradeRequest = getAlipayTradeRequest();
		AlipayConfigs alipayConfigs = getAlipayConfigs();
		if (StringUtils.isEmpty(alipayTradeRequest.getOut_trade_no())) {
			throw new AlipayException("out_trade_no should not be NULL!");
		}
		if (alipayTradeRequest.getTotal_amount() == null || alipayTradeRequest.getTotal_amount() == 0.00) {
			throw new AlipayException("total_amount should not be NULL!");
		}
		if (StringUtils.isEmpty(alipayTradeRequest.getSubject())) {
			throw new AlipayException("subject should not be NULL!");
		}
		if (StringUtils.isEmpty(alipayConfigs.getNotifyurl())) {
			throw new AlipayException("notifyUrl should not be NULL!");
		}
		return true;
	}
}