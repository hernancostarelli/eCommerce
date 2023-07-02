package com.challenge.midas.service;

import com.challenge.midas.dto.request.OrderDetailRequest;
import com.challenge.midas.dto.response.OrderDetailResponse;
import com.challenge.midas.exception.OrderDetailException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface IOrderDetailService {

    @Transactional
    OrderDetailResponse create(OrderDetailRequest request) throws OrderDetailException;

    @Transactional
    OrderDetailResponse modify(String idOrderDetail, OrderDetailRequest request) throws OrderDetailException;

    @Transactional
    void enable(String idOrder) throws OrderDetailException;

    @Transactional
    void disable(String idOrder) throws OrderDetailException;

    @Transactional
    void delete(String idOrder) throws OrderDetailException;

    @Transactional(readOnly = true)
    OrderDetailResponse getById(String idOrder) throws OrderDetailException;

    @Transactional(readOnly = true)
    List<OrderDetailResponse> getAll(String value) throws OrderDetailException;

    @Transactional(readOnly = true)
    List<OrderDetailResponse> getForEnable() throws OrderDetailException;

    @Transactional(readOnly = true)
    List<OrderDetailResponse> getForDisable() throws OrderDetailException;
}