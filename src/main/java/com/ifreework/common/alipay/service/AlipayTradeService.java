
package com.ifreework.common.alipay.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alipay.api.AlipayApiException;
import com.alipay.api.response.AlipayTradeCancelResponse;
import com.alipay.api.response.AlipayTradeCloseResponse;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.ifreework.common.alipay.config.AlipayConfigs;
import com.ifreework.common.alipay.model.AlipayTradeRequest;

public interface AlipayTradeService {

	/**
	 * @throws AlipayApiException 
	 * 
	 * @Title: tradePrecreate
	 * @Description: TODO(创建订单)
	 * @param 
	 * config：支付宝配置条件  
	 * alipayOrder：订单信息
	 * @return   订单创建结果
	 * @throws
	 */
	public AlipayTradePrecreateResponse tradePrecreate(AlipayConfigs config,AlipayTradeRequest alipayOrder) throws AlipayApiException ;


	/**
	 * @throws AlipayApiException 
	 * 
	 * @Title: queryTradeResult
	 * @Description: TODO(查询订单创建结果)
	 * @param 
	 * config：支付宝配置条件  
	 * alipayOrder：订单信息
	 * @return   订单查询结果
	 * @throws
	 */
	public AlipayTradeQueryResponse queryTradeResult(AlipayConfigs config,AlipayTradeRequest alipayOrder) throws AlipayApiException;

	/**
	 * @param pid
	 * @param alipayOrder
	 */
	public AlipayTradeRefundResponse tradeRefund(AlipayConfigs config,AlipayTradeRequest alipayOrder) ;

	/**
	 *  
	 * @param pid
	 * @param request
	 * @param response
	 * @return
	 */
	public boolean isTrade(AlipayConfigs config,HttpServletRequest request,
			HttpServletResponse response) ;
	/**
	 * 撤销订单
	 */
	public  AlipayTradeCancelResponse cancelTrade(AlipayConfigs config,AlipayTradeRequest alipayOrder) ;
	
	/**
	 * 关闭订单
	 * @param jgid
	 * @param alipayOrder
	 * @return
	 * @throws AlipayApiException 
	 */
	public AlipayTradeCloseResponse closeTrade(AlipayConfigs config,AlipayTradeRequest alipayOrder) throws AlipayApiException;
}