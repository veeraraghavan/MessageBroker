package com.practice.concepts.mq.orderreplicate.eventing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SubscriberConfiguration implements ApplicationListener<ApplicationReadyEvent> {
    private static Logger log = LoggerFactory.getLogger(SubscriberConfiguration.class);

    @Bean
    @Qualifier("create")
    public TopicExchange getTopic() {
        return new TopicExchange("salesorder.create");
    }

    private String queueName = "salesorder_create";
    private String routingKey = "salesorder.create";

    @Bean
    public Queue eventQueue() {
        if (queueName == null) {
            throw new IllegalStateException("No queue to listen to");
        }
        return new Queue(queueName);
    }

    @Bean
    public Binding binding(Queue queue, @Qualifier("create") TopicExchange topic) {
        return BindingBuilder.bind(queue).to(topic).with(routingKey);

    }

    @Bean
    public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                                    MessageListenerAdapter listenerAdapter
    ) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName);
        container.setMessageListener(listenerAdapter);
        return container;

    }

    @Bean
    public MessageListenerAdapter listenerAdapter(SalesOrderCreate salesOrderCreate) {
        return new MessageListenerAdapter(salesOrderCreate, "receive");
    }

    @Bean
    public SalesOrderCreate event() {
        return new SalesOrderCreate();
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        log.info("Subscribing to events");
    }
}
