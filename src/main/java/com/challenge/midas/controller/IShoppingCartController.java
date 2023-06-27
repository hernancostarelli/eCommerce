package com.challenge.midas.controller;

import com.challenge.midas.dto.request.ShoppingCartRequest;
import com.challenge.midas.dto.response.ShoppingCartResponse;
import com.challenge.midas.exception.ProductException;
import com.challenge.midas.exception.ShoppingCartException;
import com.challenge.midas.exception.UserException;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.challenge.midas.config.ApiConstants.SHOPPING_CARD_URI;

@Validated
@Tag(name = "SHOPPING CART CONTROLLER")
@RestController
@RequestMapping(SHOPPING_CARD_URI)
@CrossOrigin(origins = "*")
public interface IShoppingCartController {

    @PostMapping(path = "/create-shopping-cart", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ShoppingCartResponse> create(@Valid @RequestBody ShoppingCartRequest request) throws ShoppingCartException, UserException, ProductException;
}