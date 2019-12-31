package com.practice.concepts.mq.ordercreate.controller;

import com.practice.concepts.mq.ordercreate.eventing.SalesOrderEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class endpoint {
    @Autowired
    SalesOrderEvent event;
    @GetMapping("/ping")
    public String ping() {
        return "Hello";
    }
    @PostMapping("/salesOrder")
    public String createSO(){
        event.sendMessage("Create Message");
        return "Message Pushed successfully";
    }
}
