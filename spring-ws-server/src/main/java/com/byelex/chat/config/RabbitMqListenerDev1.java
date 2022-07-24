package com.byelex.chat.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@EnableRabbit
@Component
@Profile("dev1")
public class RabbitMqListenerDev1 {

    Logger logger = LoggerFactory.getLogger(RabbitMqListenerDev1.class);

    @RabbitListener(queues = "Dev1Queue")
    public void listenDev1Queue(String message) {
        logger.info("Received from Dev1Queue: {}", message);
    }
}
