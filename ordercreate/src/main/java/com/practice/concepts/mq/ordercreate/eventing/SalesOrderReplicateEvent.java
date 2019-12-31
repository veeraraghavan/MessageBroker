package com.practice.concepts.mq.ordercreate.eventing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SalesOrderReplicateEvent {
    private static Logger logger = LoggerFactory.getLogger(SalesOrderReplicateEvent.class);
    public void receive(String message){
        logger.info("Received");
    }
}
