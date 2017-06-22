package com.ifreework.controller.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.ifreework.common.manager.WebsocketManager;
import com.ifreework.entity.system.Msg;

@Controller
public class GreetingController {

	@SuppressWarnings("unused")
	
	@Autowired
	private SimpMessagingTemplate template;

	@MessageMapping("/greeting")
	public void handle(String msg) {
//		ObjectMapper mapper = new ObjectMapper();
//		Msg msgObj = null;
//		try {
//			msgObj = mapper.readValue(msg, Msg.class);
//			int length = msgObj.getLength();
//			if (length == 1) {
//				WebsocketHelp.send(msgObj.getToUser(), "/topic/greetings", msg);
//			} else {
//				int order = msgObj.getOrder();
//				List<Msg> msgList = Const.MSG_MAP.get(msgObj.getUuid());
//				if(order == length - 1  ){
//					Collections.sort(msgList);
//					String msgStr = joinStr(msgList);
//					msgStr += msgObj.getMsg();
//					msgObj.setMsg(msgStr);
//					WebsocketHelp.send(msgObj.getToUser(), "/topic/greetings", msgObj);
//					
//				}else{
//					if(msgList == null){
//						msgList = new ArrayList<Msg>();
//						Const.MSG_MAP.put(msgObj.getUuid(), msgList);
//					}
//					msgList.add(msgObj);
//				}     
//			}
//		} catch (JsonParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (JsonMappingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	@SuppressWarnings("unused")
	private String joinStr(List<Msg> list) {
		String msgStr = "";
		for (Msg m : list) {
			msgStr += m.getMsg();
		}
		return msgStr;
	}

	@MessageMapping("/httpSendMsg")
	public String httpSendMsg(String msg) {
		//this.template.convertAndSendToUser("admin", "/topic/greetings", "zxZXzzxczx");
		for (int i = 0; i < 10; i++) {
			WebsocketManager.send("/topic/greetings", msg);
		}
		
		
		return "asdasdas";
	}
	
}