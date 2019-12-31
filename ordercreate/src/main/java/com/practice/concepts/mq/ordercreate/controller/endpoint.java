package com.practice.concepts.mq.ordercreate.controller;

import com.practice.concepts.mq.ordercreate.dao.SalesOrder;
import com.practice.concepts.mq.ordercreate.eventing.SalesOrderEvent;
import com.practice.concepts.mq.ordercreate.payload.SalesOrderData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class endpoint {

    @Autowired
    SalesOrder orderDao;

    @GetMapping("/ping")
    public String ping() {
        return "Hello";
    }

    @PostMapping("/salesOrder")
    public SalesOrderData createSO(@RequestBody SalesOrderData order) {
        orderDao.create(order);
        return order;
    }
}
