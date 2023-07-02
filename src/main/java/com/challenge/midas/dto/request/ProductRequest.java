package com.challenge.midas.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class ProductRequest {

    @Size(min = 3, max = 15, message = "EL NOMBRE DEL PRODUCTO DEBE TENER UN MÍNIMO DE 3 LETRAS Y UN MÁXIMO DE 15")
    @JsonProperty("name")
    private String name;

    @Size(min = 3, max = 150, message = "LA DESCRIPCIÓN DEL PRODUCTO DEBE TENER UN MÍNIMO DE 3 LETRAS Y UN MÁXIMO DE 150")
    @JsonProperty("description")
    private String description;

    @JsonProperty("price")
    private double price;

    @JsonProperty("quantity")
    private String quantity;

    @JsonProperty("image")
    private String image;

    @JsonProperty("idUser")
    private String idUser;
}