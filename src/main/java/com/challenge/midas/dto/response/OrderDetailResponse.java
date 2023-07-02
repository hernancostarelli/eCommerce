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
    private double unitPrice;

    @JsonProperty("Producto")
    private Product product;

    @JsonProperty("Orden a la que pertenece")
    private Order order;

    @JsonProperty("Fecha de creación")
    private Date creationDate;

    @JsonProperty("Fecha de modificación")
    private Date modificationDate;

    @JsonProperty("Detalle de orden deshabilitado")
    private boolean deleted;
}