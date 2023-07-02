package com.challenge.midas.controller.impl;

import com.challenge.midas.controller.IOrderController;
import com.challenge.midas.dto.request.OrderRequest;
import com.challenge.midas.dto.response.OrderResponse;
import com.challenge.midas.exception.OrderException;
import com.challenge.midas.exception.ProductException;
import com.challenge.midas.exception.ShippingAddressException;
import com.challenge.midas.exception.ShoppingCartException;
import com.challenge.midas.exception.UserException;
import com.challenge.midas.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController implements IOrderController {

    private final IOrderService service;


    @Override
    public ResponseEntity<OrderResponse> create(OrderRequest request) throws OrderException, ShoppingCartException, ShippingAddressException, UserException, ProductException {
        OrderResponse response = service.create(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<OrderResponse> modify(String idOrder, OrderRequest request) throws OrderException, ShoppingCartException, ShippingAddressException, UserException, ProductException {
        OrderResponse response = service.modify(idOrder, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> enable(String idOrder) throws OrderException {
        service.enable(idOrder);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> disable(String idOrder) throws OrderException {
        service.disable(idOrder);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> delete(String idOrder) throws OrderException {
        service.delete(idOrder);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<OrderResponse> getById(String idOrder) throws OrderException {
        OrderResponse response = service.getById(idOrder);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<OrderResponse>> getAll(String value) throws OrderException {
        List<OrderResponse> responses = service.getAll(value);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<OrderResponse>> getOrderByUser(String idOrder) throws OrderException, UserException {
        List<OrderResponse> responses = service.getOrderByUser(idOrder);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<OrderResponse>> getForEnable() throws OrderException {
        List<OrderResponse> responses = service.getForEnable();
        return ResponseEntity.ok(responses);
    }

    @Override
    public ResponseEntity<List<OrderResponse>> getForDisable() throws OrderException {
        List<OrderResponse> responses = service.getForDisable();
        return ResponseEntity.ok(responses);
    }
}