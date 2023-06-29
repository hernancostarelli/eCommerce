package com.challenge.midas.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PaymentRequest {

    @JsonProperty("idUser")
    private String idUser;

    @JsonProperty("idOrder")
    private String idOrder;
}