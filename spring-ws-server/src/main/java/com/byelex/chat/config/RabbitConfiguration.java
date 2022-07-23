package com.byelex.chat.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {

    Logger logger = LoggerFactory.getLogger(RabbitConfiguration.class);

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
    Queue myQueue1() {
        return new Queue("myQueue1");
    }

    @Bean
    Queue myQueue2() {
        return new Queue("myQueue2");
    }

    @Bean
    FanoutExchange fanoutExchange(){
        return new FanoutExchange("common-exchange");
    }

    @Bean
    Binding binding1(){
        return BindingBuilder.bind(myQueue1()).to(fanoutExchange());
    }

    @Bean
    Binding binding2(){
        return BindingBuilder.bind(myQueue2()).to(fanoutExchange());
    }
}
