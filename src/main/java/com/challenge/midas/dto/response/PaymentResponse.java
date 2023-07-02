package com.challenge.midas.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PaymentResponse {

    @JsonProperty("Id del pago")
    private String id;

    @JsonProperty("Monto total del pago")
    private double amount;

    @JsonProperty("Fecha del pago")
    private String paymentDate;

    @JsonProperty("Nombre del Cliente")
    private String user;

    @JsonProperty("Detalle de la orden de pago")
    private OrderResponse order;

    @JsonProperty("Fecha de creación")
    private String creationDate;

    @JsonProperty("Última modificación")
    private String modificationDate;

    @JsonProperty("Pago deshabilitado")
    private String deleted;
}