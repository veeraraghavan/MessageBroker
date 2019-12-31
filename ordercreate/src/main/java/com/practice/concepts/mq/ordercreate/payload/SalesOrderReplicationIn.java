package com.practice.concepts.mq.ordercreate.payload;

import lombok.Data;

@Data
public class SalesOrderReplicationIn {
    private String id;
    private String replication_status;
}
