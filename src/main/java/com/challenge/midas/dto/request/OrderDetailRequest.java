package com.challenge.midas.dto.request;

import com.challenge.midas.model.Order;
import com.challenge.midas.model.Product;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OrderDetailRequest {

    @JsonProperty("quantity")
    private int quantity;

    @JsonProperty("unitPrice")
    private double unitPrice;

    @JsonProperty("product")
    private Product product;

    @JsonProperty("order")
    private Order order;
}