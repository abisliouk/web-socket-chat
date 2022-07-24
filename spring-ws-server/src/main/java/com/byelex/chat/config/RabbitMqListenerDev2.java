package com.byelex.chat.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@EnableRabbit
@Component
@Profile("dev2")
public class RabbitMqListenerDev2 {

    Logger logger = LoggerFactory.getLogger(RabbitMqListenerDev2.class);

    @RabbitListener(queues = "Dev2Queue")
    public void listenDev2Queue(String message) {
        logger.info("Received from Dev2Queue: {}", message);
    }
}
