package com.practice.concepts.mq.orderreplicate.eventing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class SalesOrderCreate {
    private static Logger log = LoggerFactory.getLogger(SalesOrderCreate.class);
    @Autowired
    SalesOrderReplicate replicate;

    public void receive(String message) {
        log.info("Received");
        replicate.sendMessage(message);
    }
}
