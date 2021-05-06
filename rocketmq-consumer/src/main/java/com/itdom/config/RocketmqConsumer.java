package com.itdom.config;

import com.itdom.bean.Message;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class RocketmqConsumer {
@Service
@RocketMQMessageListener(topic = "topic-queue-one",consumerGroup = "consumer_topic-queue-one")
public class ConsumerOne implements RocketMQListener<Message> {
    private final Logger logger = LoggerFactory.getLogger(ConsumerOne.class);

    @Override
    public void onMessage(Message message) {
        logger.info("receive message one:{}",message);
    }
}

    @Service
    @RocketMQMessageListener(topic = "topic-queue-two",consumerGroup = "consumer_topic-queue-two")
    public class ConsumerTwo implements RocketMQListener<String> {
        private final Logger logger = LoggerFactory.getLogger(ConsumerOne.class);

        @Override
        public void onMessage(String message) {
            logger.info("receive message two:{}",message);
        }
    }
}
