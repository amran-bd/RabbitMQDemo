package com.amran.rmq.demo.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rmq/test")
public class TestController {

    private final AmqpTemplate queueSender;

    public TestController(AmqpTemplate queueSender) {
        this.queueSender = queueSender;
    }

    @GetMapping
    public String send(){
        String input = "Hello! This is RabbitMQ Test.";
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setHeader("ultima", "sim");
        Message message = new Message(input.getBytes(), messageProperties);

        queueSender.convertAndSend("test-exchange", "routing-key-test", message);
        return "Message Send Successful.";
    }

}
