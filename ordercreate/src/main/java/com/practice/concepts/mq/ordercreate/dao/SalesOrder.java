package com.practice.concepts.mq.ordercreate.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.concepts.mq.ordercreate.eventing.SalesOrderEvent;
import com.practice.concepts.mq.ordercreate.payload.SalesOrderData;
import com.practice.concepts.mq.ordercreate.payload.SalesOrderMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;

@Service
public class SalesOrder {
    @Autowired
    SalesOrderEvent event;

    public void create(SalesOrderData order) {
        try {
            SalesOrderMessage out = new SalesOrderMessage();
            Random r = new Random();
            out.setId(Integer.toString(r.nextInt(1000)));
            out.setShip_to_party(order.getShip_to_party());
            out.setSold_to_party(order.getSold_to_party());
            out.setDescription(order.getDescription());
            out.setPrice(order.getPrice());
            Date now = new Date();
            out.setCreated_at(now);
            event.sendMessage(new ObjectMapper().writeValueAsString(out));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
}
