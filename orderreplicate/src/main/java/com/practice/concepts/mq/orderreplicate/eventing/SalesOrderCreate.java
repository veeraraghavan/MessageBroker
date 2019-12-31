package com.practice.concepts.mq.orderreplicate.eventing;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.concepts.mq.orderreplicate.payload.SalesOrderMessageIn;
import com.practice.concepts.mq.orderreplicate.payload.SalesOrderReplicationMessageOut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class SalesOrderCreate {
    private static Logger log = LoggerFactory.getLogger(SalesOrderCreate.class);
    @Autowired
    SalesOrderReplicate replicate;

    public void receive(String message) {
        try {
            SalesOrderMessageIn in = new ObjectMapper().readValue(message, SalesOrderMessageIn.class);
            log.info(message);
            SalesOrderReplicationMessageOut out = new SalesOrderReplicationMessageOut();
            out.setId(in.getId());
            out.setReplication_status("Transferred to ERP");
            replicate.sendMessage(new ObjectMapper().writeValueAsString(out));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
}
