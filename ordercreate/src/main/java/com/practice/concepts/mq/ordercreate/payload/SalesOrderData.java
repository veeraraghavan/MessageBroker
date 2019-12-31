package com.practice.concepts.mq.ordercreate.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
@Data
public class SalesOrderData {
    @JsonProperty
    private String description;
    @JsonProperty("sold_to_party")
    private String sold_to_party;
    @JsonProperty("ship_to_party")
    private String ship_to_party;
    @JsonProperty
    private double price;

}
