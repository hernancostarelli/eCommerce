package com.challenge.midas.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProductResponse {

    @JsonProperty("Id del producto")
    private String id;

    @JsonProperty("Nombre del producto")
    private String name;

    @JsonProperty("Descripción del producto")
    private String description;

    @JsonProperty("Precio del producto")
    private String price;

    @JsonProperty("Stock del producto")
    private int quantity;

    @JsonProperty("Imagen del producto")
    private String image;

    @JsonProperty("Nombre del Vendedor")
    private String user;

    @JsonProperty("Fecha de creación")
    private String creationDate;

    @JsonProperty("Fecha de modificación")
    private String modificationDate;

    @JsonProperty("Producto deshabilitado")
    private String deleted;
}