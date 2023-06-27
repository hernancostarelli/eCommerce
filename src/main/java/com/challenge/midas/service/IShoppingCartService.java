package com.challenge.midas.service;

import com.challenge.midas.dto.request.ShoppingCartRequest;
import com.challenge.midas.dto.response.ShoppingCartResponse;
import com.challenge.midas.exception.ShoppingCartException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface IShoppingCartService {

    @Transactional
    ShoppingCartResponse create(ShoppingCartRequest request) throws ShoppingCartException;

    @Transactional
    ShoppingCartResponse modify(String idShoppingCart, ShoppingCartRequest request) throws ShoppingCartException;

    @Transactional
    void enable(String idShoppingCart) throws ShoppingCartException;

    @Transactional
    void disable(String idShoppingCart) throws ShoppingCartException;

    @Transactional
    void delete(String idShoppingCart) throws ShoppingCartException;

    @Transactional(readOnly = true)
    ShoppingCartResponse getById(String idShoppingCart) throws ShoppingCartException;

    @Transactional(readOnly = true)
    List<ShoppingCartResponse> getAll(String value) throws ShoppingCartException;

    @Transactional(readOnly = true)
    List<ShoppingCartResponse> getShoppingCartByUser(String idUser) throws ShoppingCartException;

    @Transactional(readOnly = true)
    List<ShoppingCartResponse> getForEnable() throws ShoppingCartException;

    @Transactional(readOnly = true)
    List<ShoppingCartResponse> getForDisable() throws ShoppingCartException;
}