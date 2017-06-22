package com.ifreework.common.alipay.model;

import java.util.List;
import com.alipay.api.domain.GoodsDetail;

/**
 * 
 * 描述：    支付宝订单BEAN类，字段解释请参考支付宝API
 * @author：wangyh qq735789026  
 * @创建时间：2017年4月4日 下午12:58:36    
 * @修改人：wangyh    
 * @修改时间：2017年4月4日 下午12:58:36    
 * @version 1.0
 */
public class AlipayTradeRequest {
	private String out_trade_no;//
	private String trade_no;
	private String seller_id;
	private Double total_amount;
	private String subject;
	private String body;
	private List<GoodsDetail> goods_detail;
	private String store_id;
	private String timeout_express;
	
	
	
	public String getTimeout_express() {
		return timeout_express;
	}
	public void setTimeout_express(String timeoutExpress) {
		timeout_express = timeoutExpress;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String outTradeNo) {
		out_trade_no = outTradeNo;
	}
	public String getTrade_no() {
		return trade_no;
	}
	public void setTrade_no(String tradeNo) {
		trade_no = tradeNo;
	}
	public String getSeller_id() {
		return seller_id;
	}
	public void setSeller_id(String sellerId) {
		seller_id = sellerId;
	}
	public Double getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(Double totalAmount) {
		total_amount = totalAmount;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public List<GoodsDetail> getGoods_detail() {
		return goods_detail;
	}
	public void setGoods_detail(List<GoodsDetail> goodsDetail) {
		goods_detail = goodsDetail;
	}
	public String getStore_id() {
		return store_id;
	}
	public void setStore_id(String storeId) {
		store_id = storeId;
	}
	
}