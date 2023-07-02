package com.challenge.midas.controller.impl;

import com.challenge.midas.controller.IPaymentController;
import com.challenge.midas.dto.request.PaymentRequest;
import com.challenge.midas.dto.response.PaymentResponse;
import com.challenge.midas.exception.PaymentException;
import com.challenge.midas.exception.UserException;
import com.challenge.midas.service.IPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PaymentController implements IPaymentController {

    private final IPaymentService service;


    @Override
    public ResponseEntity<PaymentResponse> create(PaymentRequest request) throws PaymentException {
        PaymentResponse response = service.create(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<PaymentResponse> modify(String idPayment, PaymentRequest request) throws PaymentException {
        PaymentResponse response = service.modify(idPayment, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> enable(String idPayment) throws PaymentException {
        service.enable(idPayment);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> disable(String idPayment) throws PaymentException {
        service.disable(idPayment);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> delete(String idPayment) throws PaymentException {
        service.delete(idPayment);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<PaymentResponse> getById(String idPayment) throws PaymentException {
        PaymentResponse response = service.getById(idPayment);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<PaymentResponse>> getAll(String value) throws PaymentException {
        List<PaymentResponse> response = service.getAll(value);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<PaymentResponse>> getPaymentByUser(String idPayment) throws PaymentException, UserException {
        List<PaymentResponse> response = service.gePaymentByUser(idPayment);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<PaymentResponse>> getForEnable() throws PaymentException {
        List<PaymentResponse> responses = service.getForEnable();
        return ResponseEntity.ok(responses);
    }

    @Override
    public ResponseEntity<List<PaymentResponse>> getForDisable() throws PaymentException {
        List<PaymentResponse> responses = service.getForDisable();
        return ResponseEntity.ok(responses);
    }
}