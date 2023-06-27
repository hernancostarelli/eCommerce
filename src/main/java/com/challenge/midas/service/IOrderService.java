package com.challenge.midas.service;

import com.challenge.midas.dto.request.OrderRequest;
import com.challenge.midas.dto.response.OrderResponse;
import com.challenge.midas.exception.OrderException;
import com.challenge.midas.exception.UserException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface IOrderService {

    @Transactional
    OrderResponse create(OrderRequest request) throws OrderException;

    @Transactional
    OrderResponse modify(String idOrder, OrderRequest request) throws OrderException;

    @Transactional
    void enable(String idOrder) throws OrderException;

    @Transactional
    void disable(String idOrder) throws OrderException;

    @Transactional
    void delete(String idOrder) throws OrderException;

    @Transactional(readOnly = true)
    OrderResponse getById(String idOrder) throws OrderException;

    @Transactional(readOnly = true)
    List<OrderResponse> getAll(String value) throws OrderException;

    @Transactional(readOnly = true)
    List<OrderResponse> getOrderByUser(String idUser) throws OrderException, UserException;

    @Transactional(readOnly = true)
    List<OrderResponse> getForEnable() throws OrderException;

    @Transactional(readOnly = true)
    List<OrderResponse> getForDisable() throws OrderException;
}