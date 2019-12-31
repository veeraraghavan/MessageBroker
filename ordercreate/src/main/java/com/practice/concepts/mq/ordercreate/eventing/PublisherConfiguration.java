package com.practice.concepts.mq.ordercreate.eventing;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class PublisherConfiguration {
    @Bean
    @Qualifier("create")
    public TopicExchange soCreateTopicName() {
        return new TopicExchange("salesorder.create");
    }

    @Bean
    public SalesOrderEvent soEvent(RabbitTemplate rabbitTemplate, @Qualifier("create") TopicExchange topicExchange) {
        return new SalesOrderEvent(rabbitTemplate, topicExchange);

    }


}
