package com.challenge.midas.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProductRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("price")
    private String price;

    @JsonProperty("quantity")
    private String quantity;

    @JsonProperty("image")
    private String image;

    @JsonProperty("idUser")
    private String idUser;
}