package com.ifreework.common.alipay.request;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.ifreework.common.alipay.config.AlipayConfigs;
import com.ifreework.common.alipay.exception.AlipayException;
import com.ifreework.common.alipay.model.AlipayTradeRequest;

/**
 * 
 * 描述：订单查询请求工具类    
 * @author：wangyh qq735789026  
 * @创建时间：2017年4月4日 下午4:19:42    
 * @修改人：wangyh    
 * @修改时间：2017年4月4日 下午4:19:42    
 * @version 1.0
 */
public class AlipayTradeQueryRequestBuilder extends RequestBuilder {

	public AlipayTradeQueryRequestBuilder(AlipayTradeRequest alipayTradeRequest,AlipayConfigs alipayConfigs) {
		super(alipayTradeRequest, alipayConfigs);
	}
	
	/**
	 * 建立订单状态查询请求
	 */
	@SuppressWarnings({ "rawtypes" })
	public AlipayRequest builder() {
		AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
		if(validate()){
			request.setBizContent(getBizContent());
		}
		return request;
	}

	/**
	 * 获取订单请求附加域参数
	 */
	public String getBizContent() {
		AlipayTradeRequest ao = new AlipayTradeRequest();
		ao.setTrade_no(getAlipayTradeRequest().getTrade_no());
		ao.setOut_trade_no(getAlipayTradeRequest().getOut_trade_no());
		return JSONObject.toJSONString(ao);
	}

	/**
	 * 校验请求参数是否正确
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