package com.ifreework.common.weixin;

import org.marker.weixin.DefaultSession;
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
	private DefaultSession session;

	private String CONTACT_US = "CONTACT_US";//联系我们按钮KEY

	

	/**
	 * 
	 * 描述：事件
	 * @param msg 
	 * @return
	 */
	@Override
	public void onEventMsg(Msg4Event msg) {
		if ("subscribe".equals(msg.getEvent())) {
			returnMSg(msg, null, "关注");
		}
		if ("unsubscribe".equals(msg.getEvent())) {
			returnMSg(msg, null, "取消关注");
		}
		if ("CLICK".equals(msg.getEvent())) {
			String clickKey = msg.getEventKey();
			if (CONTACT_US.equals(clickKey)) { // 联系我们
				String html = "您好，很高兴为您服务。\n如果系统中没有记录您的公司或者产品，请与我们联系。\n邮箱：xz_zhimakaimen@163.com\n电话：18888888888\n";
				String toUserName, fromUserName;
				toUserName = msg.getToUserName();
				fromUserName = msg.getFromUserName();
				Msg4Text rmsg = new Msg4Text();
				rmsg.setFromUserName(toUserName);
				rmsg.setToUserName(fromUserName);
				rmsg.setContent(html); // 回复文字消息
				session.callback(rmsg);
			}
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
		logger.info("公共号发送消息：{}", msg.getPicUrl());
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
		logger.info("公共号发送消息：{}", getmsg);
	}

	public DefaultSession getSession() {
		return session;
	}

	public void setSession(DefaultSession session) {
		this.session = session;
	}
}
