package com.challenge.midas.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class OrderResponse {

    @JsonProperty("Id del la orden")
    private String id;

    @JsonProperty("Número de orden")
    private String ordenNumber;

    @JsonProperty("Detalles de la orden")
    private List<OrderDetailResponse> orderDetails;

    @JsonProperty("Total de la orden")
    private String totalAmount;

    @JsonProperty("Cliente")
    private UserResponse user;

    @JsonProperty("Dirección de envío de la orden")
    private ShippingAddressResponse shippingAddress;

    @JsonProperty("Pago")
    private PaymentResponse payment;

    @JsonProperty("Fecha de creación")
    private String creationDate;

    @JsonProperty("Fecha de modificación")
    private String modificationDate;

    @JsonProperty("Orden deshabilitada")
    private String deleted;
}