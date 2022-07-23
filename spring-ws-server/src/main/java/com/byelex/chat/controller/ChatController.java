package com.byelex.chat.controller;

import com.byelex.chat.config.RabbitMqListener;
import com.byelex.chat.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ChatController {

    //////////////////////////////   RabbitMQ test   //////////////////////////////

    Logger logger = LoggerFactory.getLogger(ChatController.class);

    private final RabbitTemplate template;

    @Autowired
    public ChatController(RabbitTemplate template){ this.template = template;}

    @PostMapping("/emit")
    public ResponseEntity<String> emit(@RequestBody String message){
        logger.info("Emit to queues");
        template.setExchange("common-exchange");
        template.convertAndSend(message);
        return ResponseEntity.ok("Succes emit to queues");
    }


    ////////////////////////////////////////////////////////////////////////////////


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
//        simpMessagingTemplate.convertAndSend("/topic/user/" + message.getReceiverName() + "/private", message);
//        simpMessagingTemplate.convertAndSend("/topic/chatroom", message);
        simpMessagingTemplate.convertAndSendToUser(message.getReceiverName(), "/topic", message);
        simpMessagingTemplate.convertAndSend("/topic/chatroom", message);
        System.out.println("private message: " + message.toString());
        return message;
    }
}
