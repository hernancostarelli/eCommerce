package com.challenge.midas.dto.request;

import lombok.Data;

@Data
public class ProductRequest {
    private String name;
    private String description;
    private String price;
    private String quantity;
    private String image;
    private String idUser;
}