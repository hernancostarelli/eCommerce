package com.challenge.midas.service;

import com.challenge.midas.dto.request.ShippingAddressRequest;
import com.challenge.midas.dto.response.ShippingAddressResponse;
import com.challenge.midas.exception.ShippingAddressException;
import com.challenge.midas.exception.UserException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface IShippingAddressService {

    @Transactional
    ShippingAddressResponse create(ShippingAddressRequest request) throws ShippingAddressException;

    @Transactional
    ShippingAddressResponse modify(String idShippingAddress, ShippingAddressRequest request) throws ShippingAddressException;

    @Transactional
    void enable(String idShippingAddress) throws ShippingAddressException;

    @Transactional
    void disable(String idShippingAddress) throws ShippingAddressException;

    @Transactional
    void delete(String idShippingAddress) throws ShippingAddressException;

    @Transactional(readOnly = true)
    ShippingAddressResponse getById(String idShippingAddress) throws ShippingAddressException;

    @Transactional(readOnly = true)
    List<ShippingAddressResponse> getAll(String value) throws ShippingAddressException;

    @Transactional(readOnly = true)
    List<ShippingAddressResponse> getShippingAddressByUser(String idUser) throws ShippingAddressException, UserException;

    @Transactional(readOnly = true)
    List<ShippingAddressResponse> getForEnable() throws ShippingAddressException;

    @Transactional(readOnly = true)
    List<ShippingAddressResponse> getForDisable() throws ShippingAddressException;
}