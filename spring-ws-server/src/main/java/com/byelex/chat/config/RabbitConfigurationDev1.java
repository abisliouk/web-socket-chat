package com.byelex.chat.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev1")
public class RabbitConfigurationDev1 {

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
    Queue Dev1Queue() {
        return new Queue("Dev1Queue");
    }

    @Bean
    FanoutExchange fanoutExchange(){
        return new FanoutExchange("common-exchange");
    }

    @Bean
    Binding binding1(){
        return BindingBuilder.bind(Dev1Queue()).to(fanoutExchange());
    }
}
