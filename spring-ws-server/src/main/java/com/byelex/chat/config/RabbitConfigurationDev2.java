package com.byelex.chat.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev2")
public class RabbitConfigurationDev2 {

    @Bean
    public CachingConnectionFactory connectionFactory() {
        return new CachingConnectionFactory("localhost");
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }

    @Bean
    Queue Dev2Queue() {
        return new Queue("Dev2Queue");
    }

    @Bean
    FanoutExchange fanoutExchange(){
        return new FanoutExchange("common-exchange");
    }

    @Bean
    Binding binding2(){
        return BindingBuilder.bind(Dev2Queue()).to(fanoutExchange());
    }

}

