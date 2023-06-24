package com.challenge.midas.service;

import com.challenge.midas.dto.request.Product.ProductRequest;
import com.challenge.midas.dto.response.ProductResponse;
import com.challenge.midas.exception.ProductException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface IProductService {

    @Transactional
    ProductResponse create(ProductRequest request) throws ProductException;

    ProductResponse modify(String idProduct, ProductRequest request) throws ProductException;

    @Transactional
    void enable(String idProduct) throws ProductException;

    @Transactional
    void disable(String idProduct) throws ProductException;

    @Transactional
    void delete(String idProduct) throws ProductException;

    @Transactional(readOnly = true)
    ProductResponse getById(String idProduct) throws ProductException;

    @Transactional(readOnly = true)
    List<ProductResponse> getAll(String value) throws ProductException;

    @Transactional(readOnly = true)
    List<ProductResponse> getForEnable() throws ProductException;

    @Transactional(readOnly = true)
    List<ProductResponse> getForDisable() throws ProductException;
}