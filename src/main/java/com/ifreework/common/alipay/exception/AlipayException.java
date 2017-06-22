package com.ifreework.common.alipay.exception;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 描述：    当支付宝出现异常的时候，抛出此类
 * @author：wangyh qq735789026  
 * @创建时间：2017年4月4日 下午3:23:39    
 * @修改人：wangyh    
 * @修改时间：2017年4月4日 下午3:23:39    
 * @version 1.0
 */
public class AlipayException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	private static final String DEFAULT_ERROR_MSG = "未知异常";
	private Map<String,Object> parameters = new HashMap<String, Object>();
	
	
	public AlipayException(){
		super(DEFAULT_ERROR_MSG);
	}
	
	public AlipayException( Map<String, Object> parameters){
		this.parameters = parameters;
	}
	
	public AlipayException(Throwable cause) {
        super(DEFAULT_ERROR_MSG, cause);
    }
	
	public AlipayException(String message, Throwable cause) {
        super(message, cause);
    }
	
	public AlipayException(String errorMsg){
		super(errorMsg);
	}
	public Map<String, Object> getParameters(){
		return this.parameters;
	}
	
	public void addParameter(String key,Object obj){
		this.parameters.put(key, obj);
	}
	
	public String getMessage() {
		StringBuffer sb = new StringBuffer();
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sb.append("\r");
		sb.append("当前异常发生时间为：" + sd.format(new Date()));
		sb.append("\r");
		sb.append("异常消息：");
		sb.append(super.getMessage());
		return sb.toString();
	}
}
