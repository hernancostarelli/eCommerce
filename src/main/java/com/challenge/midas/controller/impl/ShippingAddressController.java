package com.challenge.midas.controller.impl;

import com.challenge.midas.controller.IShippingAddressController;
import com.challenge.midas.dto.request.ShippingAddressRequest;
import com.challenge.midas.dto.response.ShippingAddressResponse;
import com.challenge.midas.exception.ShippingAddressException;
import com.challenge.midas.exception.UserException;
import com.challenge.midas.service.IShippingAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ShippingAddressController implements IShippingAddressController {

    private final IShippingAddressService service;


    @Override
    public ResponseEntity<ShippingAddressResponse> create(ShippingAddressRequest request) throws ShippingAddressException {
        ShippingAddressResponse response = service.create(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ShippingAddressResponse> modify(String idShippingAddress, ShippingAddressRequest request) throws ShippingAddressException {
        ShippingAddressResponse response = service.modify(idShippingAddress, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> enable(String idShippingAddress) throws ShippingAddressException {
        service.enable(idShippingAddress);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> disable(String idShippingAddress) throws ShippingAddressException {
        service.disable(idShippingAddress);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> delete(String idShippingAddress) throws ShippingAddressException {
        service.delete(idShippingAddress);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<ShippingAddressResponse> getById(String idShippingAddress) throws ShippingAddressException {
        ShippingAddressResponse response = service.getById(idShippingAddress);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ShippingAddressResponse>> getAll(String value) throws ShippingAddressException {
        List<ShippingAddressResponse> response = service.getAll(value);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ShippingAddressResponse>> getShippingAddressByUser(String idShippingAddress) throws ShippingAddressException, UserException {
        List<ShippingAddressResponse> response = service.getShippingAddressByUser(idShippingAddress);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ShippingAddressResponse>> getForEnable() throws ShippingAddressException {
        List<ShippingAddressResponse> responses = service.getForEnable();
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ShippingAddressResponse>> getForDisable() throws ShippingAddressException {
        List<ShippingAddressResponse> responses = service.getForDisable();
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }
}