package com.practice.concepts.mq.ordercreate.eventing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsumerConfiguration implements ApplicationListener<ApplicationReadyEvent> {
    private static Logger log = LoggerFactory.getLogger(ConsumerConfiguration.class);

    @Override
    public void onApplicationEvent(ApplicationReadyEvent salesOrderReplicateEvent) {
        log.info("Message replicated");
    }

    private String queueName = "salesorder_replicate";
    private String routingKey = "salesorder.replicate";

    @Bean
    @Qualifier("replicate")
    public TopicExchange getTopic() {
        return new TopicExchange("salesorder.replicate");
    }

    @Bean
    public Queue eventQueue() {
        return new Queue(queueName);
    }

    @Bean
    public Binding bind(Queue queue, @Qualifier("replicate") TopicExchange topicExchange) {
        return BindingBuilder.bind(queue).to(topicExchange).with(routingKey);

    }

    @Bean
    public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    public MessageListenerAdapter listenerAdapter(SalesOrderReplicateEvent replicateEvent) {
        return new MessageListenerAdapter(replicateEvent, "receive");
    }

    @Bean
    public SalesOrderReplicateEvent replicateEvent() {
        return new SalesOrderReplicateEvent();
    }

}
