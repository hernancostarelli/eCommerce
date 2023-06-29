package com.challenge.midas.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PaymentResponse {

    @JsonProperty("ID del pago")
    private String id;

    @JsonProperty("Monto total del pago")
    private double amount;

    @JsonProperty("Fecha del pago")
    private String paymentDate;

    @JsonProperty("Nombre del Cliente que realiza el pago")
    private String user;

    @JsonProperty("Detalle de la orden de pago")
    private OrderResponse order;

    @JsonProperty("Fecha de creación del Pago")
    private String creationDate;

    @JsonProperty("Última modificación del Pago")
    private String modificationDate;

    @JsonProperty("Pago deshabilitado")
    private String deleted;
}