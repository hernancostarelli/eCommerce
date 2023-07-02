package com.challenge.midas.controller.impl;

import com.challenge.midas.controller.IOrderDetailController;
import com.challenge.midas.dto.request.OrderDetailRequest;
import com.challenge.midas.dto.response.OrderDetailResponse;
import com.challenge.midas.exception.OrderDetailException;
import com.challenge.midas.service.IOrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderDetailController implements IOrderDetailController {

    private final IOrderDetailService service;

    @Override
    public ResponseEntity<OrderDetailResponse> create(OrderDetailRequest request) throws OrderDetailException {
        OrderDetailResponse response = service.create(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<OrderDetailResponse> modify(String idOrderDetail, OrderDetailRequest request) throws OrderDetailException {
        OrderDetailResponse response = service.modify(idOrderDetail, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> enable(String idOrderDetail) throws OrderDetailException {
        service.enable(idOrderDetail);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> disable(String idOrderDetail) throws OrderDetailException {
        service.disable(idOrderDetail);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> delete(String idOrderDetail) throws OrderDetailException {
        service.delete(idOrderDetail);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<OrderDetailResponse> getById(String idOrderDetail) throws OrderDetailException {
        OrderDetailResponse response = service.getById(idOrderDetail);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<OrderDetailResponse>> getAll(String value) throws OrderDetailException {
        List<OrderDetailResponse> response = service.getAll(value);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<OrderDetailResponse>> getForEnable() throws OrderDetailException {
        List<OrderDetailResponse> responses = service.getForEnable();
        return ResponseEntity.ok(responses);

    }

    @Override
    public ResponseEntity<List<OrderDetailResponse>> getForDisable() throws OrderDetailException {
        List<OrderDetailResponse> responses = service.getForDisable();
        return ResponseEntity.ok(responses);
    }
}