package com.byelex.chat.controller;

import com.byelex.chat.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/message")
    @SendTo("/topic/chatroom")
    public Message receiveMessage(@Payload Message message) {
        System.out.println("Chatroom : " + message.toString());
        return message;
    }

    @MessageMapping("/private-message")
    public Message recMessage(@Payload Message message) {
        simpMessagingTemplate.convertAndSend("/topic/user-" + message.getReceiverName() + "-private", message);
        simpMessagingTemplate.convertAndSend("/topic/chatroom", message);
        System.out.println("private message: " + message.toString());
        return message;
    }
}
