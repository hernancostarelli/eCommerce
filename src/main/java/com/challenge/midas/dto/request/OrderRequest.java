package com.challenge.midas.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OrderRequest {

    @JsonProperty("idUser")
    private String idUser;

    @JsonProperty("idShoppingCart")
    private String idShoppingCart;

    @JsonProperty("shippingAddress")
    private ShippingAddressRequest shippingAddress;
}