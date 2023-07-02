package com.challenge.midas.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ShoppingCartResponse {

    @JsonProperty("Id del carrito de compras")
    private String id;

    @JsonProperty("Nombre del cliente")
    private String user;

    @JsonProperty("Lista de productos en el carrito de compras")
    private List<ProductResponse> products;

    @JsonProperty("Fecha de creación")
    private String creationDate;

    @JsonProperty("Fecha de modificación")
    private String modificationDate;

    @JsonProperty("Carrito de compras deshabilitado")
    private String deleted;
}