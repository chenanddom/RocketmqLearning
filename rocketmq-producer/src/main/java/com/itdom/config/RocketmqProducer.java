package com.itdom.config;

import com.itdom.bean.Message;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RocketmqProducer implements CommandLineRunner {
    private RocketMQTemplate rocketMQTemplate;

    @Autowired
    public RocketmqProducer(RocketMQTemplate rocketMQTemplate){
        this.rocketMQTemplate=rocketMQTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        Message<String> message = new Message<>();
        message.setId(UUID.randomUUID().toString());
        message.setContent("Hello ,Springboot-ac-rocketmq!");
        rocketMQTemplate.convertAndSend("topic-queue-one",message);
        rocketMQTemplate.convertAndSend("topic-queue-two","Hello, springboot-ac-rocketmq !");
    }
}
