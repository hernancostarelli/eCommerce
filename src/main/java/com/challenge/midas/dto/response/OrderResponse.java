package com.challenge.midas.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class OrderResponse {

    private String id;
    private String ordenNumber;
    private List<OrderDetailResponse> orderDetails;
    private double totalAmount;
    private UserResponse user;
    private ShippingAddressResponse shippingAddress;
    private List<PaymentResponse> payments;
    private String creationDate;
    private String modificationDate;
    private String deleted;
}
