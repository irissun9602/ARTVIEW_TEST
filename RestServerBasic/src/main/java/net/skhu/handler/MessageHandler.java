package net.skhu.handler;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageHandler {

	/*
	@MessageMapping("/hello")
	@SendTo("/topic/roomId")
	public MessageVo broadcasting(MessageVo message) throws Exception{
		System.out.println("message: "  message.getSendMessage());
		return message;
	}*/
}
