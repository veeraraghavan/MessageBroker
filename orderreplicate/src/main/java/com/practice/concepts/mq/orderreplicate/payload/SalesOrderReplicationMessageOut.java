package com.practice.concepts.mq.orderreplicate.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class SalesOrderReplicationMessageOut {
    @JsonProperty
    private String id;
    @JsonProperty("replication_status")
    private String replication_status;
}
