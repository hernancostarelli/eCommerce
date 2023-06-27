package com.challenge.midas.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class ShoppingCartResponse {

    private String id;
    private String user;
    private List<ProductResponse> products;
    private String creationDate;
    private String modificationDate;
    private String deleted;
}