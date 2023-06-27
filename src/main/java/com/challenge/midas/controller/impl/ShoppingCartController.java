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

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ShoppingCartController implements IShoppingCartController {

    private final IShoppingCartService service;


    @Override
    public ResponseEntity<ShoppingCartResponse> create(ShoppingCartRequest request) throws ShoppingCartException, UserException, ProductException {
        ShoppingCartResponse response = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<ShoppingCartResponse> modify(String idShoppingCart, ShoppingCartRequest request) throws ShoppingCartException, UserException, ProductException {
        ShoppingCartResponse response = service.modify(idShoppingCart, request);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> enable(String idShoppingCart) throws ShoppingCartException {
        service.enable(idShoppingCart);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> disable(String idShoppingCart) throws ShoppingCartException {
        service.disable(idShoppingCart);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> delete(String idShoppingCart) throws ShoppingCartException {
        service.delete(idShoppingCart);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<ShoppingCartResponse> getById(String idShoppingCart) throws ShoppingCartException {
        ShoppingCartResponse response = service.getById(idShoppingCart);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<List<ShoppingCartResponse>> getAll(String value) throws ShoppingCartException {
        List<ShoppingCartResponse> responses = service.getAll(value);
        return ResponseEntity.ok(responses);
    }

    @Override
    public ResponseEntity<List<ShoppingCartResponse>> getShoppingCartByUser(String idUser) throws ShoppingCartException, UserException {
        List<ShoppingCartResponse> responses = service.getShoppingCartByUser(idUser);
        return ResponseEntity.ok(responses);
    }

    @Override
    public ResponseEntity<List<ShoppingCartResponse>> getForEnable() throws ShoppingCartException {
        List<ShoppingCartResponse> responses = service.getForEnable();
        return ResponseEntity.ok(responses);
    }

    @Override
    public ResponseEntity<List<ShoppingCartResponse>> getForDisable() throws ShoppingCartException {
        List<ShoppingCartResponse> responses = service.getForDisable();
        return ResponseEntity.ok(responses);
    }
}