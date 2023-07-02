package com.challenge.midas.mapper;

import com.challenge.midas.dto.request.ShippingAddressRequest;
import com.challenge.midas.dto.response.ShippingAddressResponse;
import com.challenge.midas.enums.EExceptionMessage;
import com.challenge.midas.exception.ShippingAddressException;
import com.challenge.midas.model.ShippingAddress;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ShippingAddressMapper {

    public static final String DD_MM_YYYY = "dd/MM/yyyy";

    public ShippingAddress convertToEntity(ShippingAddress shippingAddress, ShippingAddressRequest request) throws ShippingAddressException {
        validateRequest(request);
        shippingAddress.setStreet(request.getStreet());
        shippingAddress.setCity(request.getCity());
        shippingAddress.setZipCode(request.getZipCode());
        shippingAddress.setCountry(request.getCountry());
        if (shippingAddress.getId() != null)
            shippingAddress.setModificationDate(new Date());
        return shippingAddress;
    }

    public ShippingAddressResponse convertToResponse(ShippingAddress shippingAddress) {
        ShippingAddressResponse response = new ShippingAddressResponse();
        response.setId(shippingAddress.getId());
        response.setStreet(shippingAddress.getStreet());
        response.setCity(shippingAddress.getCity());
        response.setZipCode(shippingAddress.getZipCode());
        response.setCountry(shippingAddress.getCountry());
        response.setCreationDate(shippingAddress.getCreationDate() != null ?
                new SimpleDateFormat(DD_MM_YYYY).format(shippingAddress.getCreationDate()) : null);
        response.setModificationDate(shippingAddress.getModificationDate() != null ?
                new SimpleDateFormat(DD_MM_YYYY).format(shippingAddress.getModificationDate()) : null);
        response.setDeleted(String.valueOf(shippingAddress.isDeleted()));
        return response;
    }

    public List<ShippingAddressResponse> convertToResponseList(List<ShippingAddress> shippingAddressList) {
        return shippingAddressList.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    private void validateRequest(ShippingAddressRequest request) throws ShippingAddressException {
        String street = request.getStreet();
        String city = request.getCity();
        String zipCode = request.getZipCode();
        String country = request.getCountry();
        if (StringUtils.isBlank(street))
            throw new ShippingAddressException(EExceptionMessage.THE_STREET_CANNOT_BE_EMPTY_OR_BE_NULL.toString());
        if (StringUtils.isBlank(city))
            throw new ShippingAddressException(EExceptionMessage.THE_CITY_CANNOT_BE_EMPTY_OR_BE_NULL.toString());
        if (StringUtils.isBlank(zipCode))
            throw new ShippingAddressException(EExceptionMessage.THE_ZIP_CODE_CANNOT_BE_EMPTY_OR_BE_NULL.toString());
        if (StringUtils.isBlank(country))
            throw new ShippingAddressException(EExceptionMessage.THE_COUNTRY_CANNOT_BE_EMPTY_OR_BE_NULL.toString());
    }
}