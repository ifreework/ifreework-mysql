package com.ifreework.common.alipay;

public enum AlipayConstant {
	SUCCESS("10000"), // 成功
	PAYING("10003"), // 用户支付中
	ERROR("20000"), // 系统异常
	NOAUTH("20001"), // 权限不足
	NOPARAM("40001"), // 缺少必要参数
	PARAMERROR("40002"), // 非法参数
	
	FAILED("40004"), // 失败

	
	UNKNOWN("50000"), // 系统异常
	NETERROR("50001"), // 网络连接异常
	FILEPATH("50003"), // 读取配置文件异常
	REQUESTERROR("50004"), //请求参数未通过检验
	PID_NOT_FOUND("50005"), // pid不存在
	
	TRADE_NOT_EXIST("ACQ.TRADE_NOT_EXIST"),//关闭时订单不存在
	TRADE_STATUS_ERROR("ACQ.TRADE_STATUS_ERROR"),//关闭时订单状态不存在，通常为已经支付或者订单已经关闭
	
	
	TRADE_CLOSED("TRADE_CLOSED"), //单据已经关闭
	TRADE_SUCCESS("TRADE_SUCCESS"),//订单支付成功状态
	TRADE_FINISHED("TRADE_FINISHED"),//交易结束，不可退款
	WAIT_BUYER_PAY("WAIT_BUYER_PAY");//等待买家付款
	
	private String name;

	private AlipayConstant(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
