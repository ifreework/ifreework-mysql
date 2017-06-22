package com.ifreework.common.alipay.request;

import com.alipay.api.AlipayRequest;
import com.ifreework.common.alipay.config.AlipayConfigs;
import com.ifreework.common.alipay.model.AlipayTradeRequest;


/**
 * 
 * 描述：创建支付宝请求抽象类    
 * @author：wangyh qq735789026  
 * @创建时间：2017年4月4日 下午3:46:52    
 * @修改人：wangyh    
 * @修改时间：2017年4月4日 下午3:46:52    
 * @version 1.0
 */
public abstract class RequestBuilder {
	private AlipayTradeRequest alipayTradeRequest;
	private AlipayConfigs alipayConfigs;
	
	public RequestBuilder(AlipayTradeRequest alipayTradeRequest,AlipayConfigs alipayConfigs) {
		super();
		this.alipayTradeRequest = alipayTradeRequest;
		this.alipayConfigs = alipayConfigs;
	}

	public abstract boolean validate();

	public abstract String getBizContent();

	@SuppressWarnings("rawtypes")
	public abstract AlipayRequest builder();

	public AlipayTradeRequest getAlipayTradeRequest() {
		return alipayTradeRequest;
	}

	public void setAlipayTradeRequest(AlipayTradeRequest alipayTradeRequest) {
		this.alipayTradeRequest = alipayTradeRequest;
	}

	public AlipayConfigs getAlipayConfigs() {
		return alipayConfigs;
	}

	public void setAlipayConfigs(AlipayConfigs alipayConfigs) {
		this.alipayConfigs = alipayConfigs;
	}

	

}