/***********************************************************************
 * Module:  AbsAlipayTradeServiceImpl.java
 * Author:  wangyh
 * Purpose: Defines the Class AbsAlipayTradeServiceImpl
 ***********************************************************************/
package com.ifreework.common.alipay.service;



import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayRequest;
import com.alipay.api.AlipayResponse;
import com.alipay.api.DefaultAlipayClient;
import com.ifreework.common.alipay.config.AlipayConfigs;


public abstract class AbsAlipayTradeServiceImpl implements AlipayTradeService {
	private Log log = LogFactory.getLog(AbsAlipayTradeServiceImpl.class);


	/**
	 * 
	 * @Title: clientBuilder
	 * @Description: TODO(校验支付宝配置参数，创建AlipayClient)
	 * @param alipayConfigs：支付宝参数
	 * @return   AlipayClient
	 * @throws
	 */
	protected AlipayClient clientBuilder(AlipayConfigs alipayConfigs) throws AlipayApiException {
		// TODO: implement
		AlipayClient client = null;
		if(alipayConfigs == null){
			throw new AlipayApiException("AlipayConfigs should not be NUll!");
		}
		
		if (StringUtils.isEmpty(alipayConfigs.getOpenapidomain())) {
			throw new AlipayApiException("Open Api Domain should not be NULL!");
		}
		if (StringUtils.isEmpty(alipayConfigs.getAppid())) {
			throw new AlipayApiException("Appid should not be NULL!");
		}
		if (StringUtils.isEmpty(alipayConfigs.getPrivatekey())) {
			throw new AlipayApiException("Private Key should not be NULL!");
		}
		if (StringUtils.isEmpty(alipayConfigs.getAlipaypublickey())) {
			throw new AlipayApiException("Alipay Public Key should not be NULL!");
		}

		client = new DefaultAlipayClient(alipayConfigs.getOpenapidomain(), alipayConfigs
				.getAppid(), alipayConfigs.getPrivatekey(), "json", "utf-8", alipayConfigs
				.getAlipaypublickey());
		return client;
	}

	/**
	 * 
	 * @Title: getResponse
	 * @Description: TODO(调用支付宝请求方法)
	 * @param 
	 * @return   
	 * @throws
	 */
	@SuppressWarnings( {"unchecked", "rawtypes" })
	protected AlipayResponse getResponse(AlipayClient client,
			AlipayRequest request) {
		try {
			AlipayResponse response = client.execute(request);
			return response;
		} catch (AlipayApiException e) {
			e.printStackTrace();
			log.error(e);
			return null;
		}
	}
}