package com.challenge.midas.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ShoppingCartRequest {

    @JsonProperty("idUser")
    private String idUser;

    @JsonProperty("idProducts")
    private List<String> idProducts;
}