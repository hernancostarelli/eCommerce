package com.challenge.midas.controller.impl;

import com.challenge.midas.controller.IProductController;
import com.challenge.midas.dto.request.Product.ProductRequest;
import com.challenge.midas.dto.response.ProductResponse;
import com.challenge.midas.exception.ProductException;
import com.challenge.midas.service.IProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController implements IProductController {

    private final IProductService service;

    public ProductController(IProductService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<ProductResponse> create(ProductRequest request) throws ProductException {
        ProductResponse response = service.create(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ProductResponse> modify(String idProduct, ProductRequest request) throws ProductException {
        ProductResponse response = service.modify(idProduct, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProductResponse> enable(String idProduct) throws ProductException {
        service.enable(idProduct);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProductResponse> disable(String idProduct) throws ProductException {
        service.disable(idProduct);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProductResponse> delete(String idProduct) throws ProductException {
        service.delete(idProduct);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProductResponse> getById(String idProduct) throws ProductException {
        ProductResponse response = service.getById(idProduct);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ProductResponse>> getAll(String value) throws ProductException {
        List<ProductResponse> response = service.getAll(value);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ProductResponse>> getForEnable() throws ProductException {
        List<ProductResponse> response = service.getForEnable();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ProductResponse>> getForDisable() throws ProductException {
        List<ProductResponse> response = service.getForDisable();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}