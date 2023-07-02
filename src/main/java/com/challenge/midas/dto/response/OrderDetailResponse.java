package com.challenge.midas.dto.response;

import com.challenge.midas.model.Order;
import com.challenge.midas.model.Product;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class OrderDetailResponse {

    @JsonProperty("Id del detalle de la orden")
    private String id;

    @JsonProperty("Cantidad")
    private int quantity;

    @JsonProperty("Precio unitario")
    private String unitPrice;

    @JsonProperty("Producto")
    private Product product;

    @JsonProperty("Orden a la que pertenece")
    private Order order;

    @JsonProperty("Fecha de creación")
    private String creationDate;

    @JsonProperty("Fecha de modificación")
    private String modificationDate;

    @JsonProperty("Detalle de orden deshabilitado")
    private String deleted;
}