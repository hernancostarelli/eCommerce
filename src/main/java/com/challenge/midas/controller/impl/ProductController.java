package com.challenge.midas.controller.impl;

import com.challenge.midas.controller.IProductController;
import com.challenge.midas.dto.request.ProductRequest;
import com.challenge.midas.dto.response.ProductResponse;
import com.challenge.midas.exception.ProductException;
import com.challenge.midas.exception.UserException;
import com.challenge.midas.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController implements IProductController {

    private final IProductService service;

    @Override
    public ResponseEntity<ProductResponse> create(ProductRequest request) throws ProductException, UserException {
        ProductResponse response = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<ProductResponse> modify(String idProduct, ProductRequest request) throws ProductException, UserException {
        ProductResponse response = service.modify(idProduct, request);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> enable(String idProduct) throws ProductException {
        service.enable(idProduct);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> disable(String idProduct) throws ProductException {
        service.disable(idProduct);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> delete(String idProduct) throws ProductException {
        service.delete(idProduct);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<ProductResponse> getById(String idProduct) throws ProductException {
        ProductResponse response = service.getById(idProduct);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<List<ProductResponse>> getAll(String value) throws ProductException {
        List<ProductResponse> responses = service.getAll(value);
        return ResponseEntity.ok(responses);
    }

    @Override
    public ResponseEntity<List<ProductResponse>> getForEnable() throws ProductException {
        List<ProductResponse> responses = service.getForEnable();
        return ResponseEntity.ok(responses);
    }

    @Override
    public ResponseEntity<List<ProductResponse>> getForDisable() throws ProductException {
        List<ProductResponse> responses = service.getForDisable();
        return ResponseEntity.ok(responses);
    }
}