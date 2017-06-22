package com.ifreework.common.alipay.request;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayRequest;
import com.alipay.api.request.AlipayTradeCancelRequest;
import com.ifreework.common.alipay.config.AlipayConfigs;
import com.ifreework.common.alipay.exception.AlipayException;
import com.ifreework.common.alipay.model.AlipayTradeRequest;

/**
 * 
 * 描述：撤销订单请求工具类（如果订单已生成且尚未支付、则订单讲自动关闭，如果订单已支付完成，则对订单进行退款）    
 * @author：wangyh qq735789026  
 * @创建时间：2017年4月4日 下午4:20:03    
 * @修改人：wangyh    
 * @修改时间：2017年4月4日 下午4:20:03    
 * @version 1.0
 */
public class AlipayTradeCancelRequestBuilder extends RequestBuilder {

	public AlipayTradeCancelRequestBuilder(AlipayTradeRequest alipayTradeRequest,AlipayConfigs alipayConfigs) {
		super(alipayTradeRequest, alipayConfigs);
	}

	/**
	 * 建立订单撤销请求
	 */
	@SuppressWarnings("rawtypes")
	public AlipayRequest builder() {
		AlipayTradeCancelRequest request = new AlipayTradeCancelRequest();
		if(validate()){
			request.setBizContent(getBizContent());
		}
		return request;
	}

	/**
	 * 获取订单撤销请求附加域
	 */
	public String getBizContent() {
		AlipayTradeRequest ao = new AlipayTradeRequest();
		ao.setTrade_no(getAlipayTradeRequest().getTrade_no());
		ao.setOut_trade_no(getAlipayTradeRequest().getOut_trade_no());
		return JSONObject.toJSONString(ao);
	}

	/**
	 * 校验订单撤销参数
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