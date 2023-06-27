package com.challenge.midas.service;

import com.challenge.midas.dto.request.PaymentRequest;
import com.challenge.midas.dto.response.PaymentResponse;
import com.challenge.midas.exception.PaymentException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface IPaymentService {

    @Transactional
    PaymentResponse create(PaymentRequest request) throws PaymentException;

    @Transactional
    PaymentResponse modify(String idPayment, PaymentRequest request) throws PaymentException;

    @Transactional
    void enable(String idPayment) throws PaymentException;

    @Transactional
    void disable(String idPayment) throws PaymentException;

    @Transactional
    void delete(String idPayment) throws PaymentException;

    @Transactional(readOnly = true)
    PaymentResponse getById(String idPayment) throws PaymentException;

    @Transactional(readOnly = true)
    List<PaymentResponse> getAll(String value) throws PaymentException;

    @Transactional(readOnly = true)
    List<PaymentResponse> gePaymentByUser(String idUser) throws PaymentException;

    @Transactional(readOnly = true)
    List<PaymentResponse> getForEnable() throws PaymentException;

    @Transactional(readOnly = true)
    List<PaymentResponse> getForDisable() throws PaymentException;
}