package com.practice.concepts.mq.orderreplicate.eventing;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class SalesOrderReplicate {
    private static Logger log = LoggerFactory.getLogger(SalesOrderReplicate.class);
    private final RabbitTemplate rabbitTemplate;
    private final TopicExchange topicExchange;

    @Autowired
    public SalesOrderReplicate(RabbitTemplate rabbitTemplate, @Qualifier("replicate") TopicExchange topicExchange) {
        this.rabbitTemplate = rabbitTemplate;
        this.topicExchange = topicExchange;

    }

    public void sendMessage(String message) {
        String routingKey = "salesorder.replicate";

        try {
            Thread.sleep(10000);
            log.info(message);
            rabbitTemplate.convertAndSend(topicExchange.getName(), routingKey, message);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
