package com.challenge.midas.dto.request;

import com.challenge.midas.model.OrderDetail;
import com.challenge.midas.model.Payment;
import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {

    private List<OrderDetail> orderDetails;
    private String totalAmount;
    private String idUser;
    private ShippingAddressRequest shippingAddress;
    private List<Payment> payments;
}