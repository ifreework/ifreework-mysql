package com.ifreework.common.alipay.request;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayRequest;
import com.alipay.api.request.AlipayTradeCloseRequest;
import com.ifreework.common.alipay.config.AlipayConfigs;
import com.ifreework.common.alipay.exception.AlipayException;
import com.ifreework.common.alipay.model.AlipayTradeRequest;

public class AlipayTradeCloseRequestBuilder extends RequestBuilder {

	public AlipayTradeCloseRequestBuilder(AlipayTradeRequest alipayTradeRequest,AlipayConfigs alipayConfigs) {
		super(alipayTradeRequest, alipayConfigs);
	}
	
	/**
	 * 建立订单关闭请求
	 */
	@SuppressWarnings("rawtypes")
	public AlipayRequest builder() {
		AlipayTradeCloseRequest request = new AlipayTradeCloseRequest();
		if(validate()){
			request.setBizContent(getBizContent());
		}
		return request;
	}
	
	/**
	 * 获取订单关闭请求附加域
	 */
	public String getBizContent() {
		AlipayTradeRequest ao = new AlipayTradeRequest();
		ao.setTrade_no(getAlipayTradeRequest().getTrade_no());
		ao.setOut_trade_no(getAlipayTradeRequest().getOut_trade_no());
		return JSONObject.toJSONString(ao);
	}
	
	/**
	 * 校验订单关闭参数
	 */
	public boolean validate() {
		if (StringUtils.isEmpty(getAlipayTradeRequest().getOut_trade_no())
				&& StringUtils.isEmpty(getAlipayTradeRequest().getTrade_no())) {
			throw new AlipayException(
					"Trade_no and Out_trade_no can not both be NULL!");
		}
		return true;
	}

}