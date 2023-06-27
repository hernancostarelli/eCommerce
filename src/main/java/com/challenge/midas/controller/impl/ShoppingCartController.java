package com.challenge.midas.controller.impl;

import com.challenge.midas.controller.IShoppingCartController;
import com.challenge.midas.dto.request.ShoppingCartRequest;
import com.challenge.midas.dto.response.ShoppingCartResponse;
import com.challenge.midas.exception.ProductException;
import com.challenge.midas.exception.ShoppingCartException;
import com.challenge.midas.exception.UserException;
import com.challenge.midas.service.IShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ShoppingCartController implements IShoppingCartController {

    private final IShoppingCartService service;


    @Override
    public ResponseEntity<ShoppingCartResponse> create(ShoppingCartRequest request) throws ShoppingCartException, UserException, ProductException {
        ShoppingCartResponse response = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}