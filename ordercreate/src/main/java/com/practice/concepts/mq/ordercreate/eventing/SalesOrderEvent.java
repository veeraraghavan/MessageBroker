package com.practice.concepts.mq.ordercreate.eventing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Arrays;
import java.util.List;

public class SalesOrderEvent {
    private final RabbitTemplate rabbitTemplate;
    private final TopicExchange topicExchange;
    private static Logger logger = LoggerFactory.getLogger(SalesOrderEvent.class);
    private static List<String> ROUTING_KEYS = Arrays.asList("salesorder.created",
            "salesorder.replicated", "purchaseorder.created");

    @Autowired
    SalesOrderEvent(RabbitTemplate rabbitTemplate, @Qualifier("create") TopicExchange topicExchange) {
        this.topicExchange = topicExchange;
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String message) {
        String routingKey = "salesorder.create";
        rabbitTemplate.convertAndSend(topicExchange.getName(), routingKey, message);
        logger.info(message);
    }
}
