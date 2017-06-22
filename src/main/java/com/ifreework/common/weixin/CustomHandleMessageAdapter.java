package com.ifreework.common.weixin;

import org.marker.weixin.HandleMessageAdapter;
import org.marker.weixin.msg.Msg4Event;
import org.marker.weixin.msg.Msg4Image;
import org.marker.weixin.msg.Msg4Link;
import org.marker.weixin.msg.Msg4Location;
import org.marker.weixin.msg.Msg4Text;
import org.marker.weixin.msg.Msg4Video;
import org.marker.weixin.msg.Msg4Voice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 描述：对微信发送的消息进行自定义处理    
 * @author：wangyh
 * @createDate：2017年6月22日
 * @modify：wangyh    
 * @modifyDate：2017年6月22日 
 * @version 1.0
 */
public class CustomHandleMessageAdapter extends HandleMessageAdapter {

	private static Logger logger = LoggerFactory.getLogger(CustomHandleMessageAdapter.class);
	/**
	 * 
	 * 描述：关注事件
	 * @param msg 
	 * @return
	 */
	@Override
	public void onEventMsg(Msg4Event msg) {
		if ("subscribe".equals(msg.getEvent())) {
			returnMSg(msg, null, "关注");
		}
	}

	/**
	 * 收到的文本消息
	 */
	@Override
	public void onTextMsg(Msg4Text msg) {
		returnMSg(null, msg, msg.getContent().trim());
	}

	@Override
	public void onImageMsg(Msg4Image msg) {
		// TODO Auto-generated method stub
		super.onImageMsg(msg);
	}

	@Override
	public void onLocationMsg(Msg4Location msg) {
		// TODO Auto-generated method stub
		super.onLocationMsg(msg);
	}

	@Override
	public void onLinkMsg(Msg4Link msg) {
		// TODO Auto-generated method stub
		super.onLinkMsg(msg);
	}

	@Override
	public void onVideoMsg(Msg4Video msg) {
		// TODO Auto-generated method stub
		super.onVideoMsg(msg);
	}

	@Override
	public void onVoiceMsg(Msg4Voice msg) {
		// TODO Auto-generated method stub
		super.onVoiceMsg(msg);
	}

	@Override
	public void onErrorMsg(int errorCode) {
		// TODO Auto-generated method stub
		super.onErrorMsg(errorCode);
	}

	/**
	 * 返回消息
	 * @param emsg
	 * @param tmsg
	 * @param getmsg
	 */
	public void returnMSg(Msg4Event emsg, Msg4Text tmsg, String getmsg) {
		logger.info("公共号发送消息：{}" ,getmsg );
	}
}
