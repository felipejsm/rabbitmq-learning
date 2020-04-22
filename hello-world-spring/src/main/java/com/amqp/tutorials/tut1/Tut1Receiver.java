package com.amqp.tutorials.tut1;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@RabbitListener(queues = "hello")
public class Tut1Receiver {
    @RabbitHandler
    public void receiver(String in) {
        System.out.println("[x] Received '".concat(in).concat("' "));
    }
}
