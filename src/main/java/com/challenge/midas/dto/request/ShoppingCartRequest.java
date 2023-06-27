package com.challenge.midas.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class ShoppingCartRequest {

    private String idUser;
    private List<String> idProducts;
}