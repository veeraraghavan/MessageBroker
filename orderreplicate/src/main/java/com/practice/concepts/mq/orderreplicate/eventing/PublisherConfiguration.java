package com.practice.concepts.mq.orderreplicate.eventing;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PublisherConfiguration {
    @Bean
    @Qualifier("replicate")
    public TopicExchange getReplicationTopic(){
        return new TopicExchange("salesorder.replicate");
    }

}
