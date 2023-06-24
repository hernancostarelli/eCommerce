package com.challenge.midas.dto.response;

import com.challenge.midas.model.Order;
import com.challenge.midas.model.ShoppingCart;
import com.challenge.midas.model.User;
import lombok.Data;

import java.util.List;

@Data
public class ProductResponse {
    private String id;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private String image;
    private User user;
    private List<Order> orders;
    private List<ShoppingCart> shoppingCarts;
    private String creationDate;
    private String modificationData;
    private String deleted;
}