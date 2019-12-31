package com.practice.concepts.mq.ordercreate.eventing;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.concepts.mq.ordercreate.payload.SalesOrderReplicationIn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;

public class SalesOrderReplicateEvent {
    private static Logger logger = LoggerFactory.getLogger(SalesOrderReplicateEvent.class);

    public void receive(String message) {
        SalesOrderReplicationIn in = null;
        try {
            in = new ObjectMapper().readValue(message, SalesOrderReplicationIn.class);
            logger.info(MessageFormat.format("Order id {0} with replication status {1} replicated", in.getId(), in.getReplication_status()));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


    }
}
