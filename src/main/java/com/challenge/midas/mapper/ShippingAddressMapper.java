package com.challenge.midas.mapper;

import com.challenge.midas.dto.request.ShippingAddressRequest;
import com.challenge.midas.dto.response.ShippingAddressResponse;
import com.challenge.midas.enums.EExceptionMessage;
import com.challenge.midas.exception.ShippingAddressException;
import com.challenge.midas.model.ShippingAddress;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ShippingAddressMapper {

    public ShippingAddress convertToEntity(ShippingAddress shippingAddress, ShippingAddressRequest request) throws ShippingAddressException {
        validateRequest(request);
        shippingAddress.setStreet(request.getStreet());
        shippingAddress.setCity(request.getCity());
        shippingAddress.setZipCode(request.getZipCode());
        shippingAddress.setCountry(request.getCountry());
        return shippingAddress;
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

    public ShippingAddressResponse convertToResponse(ShippingAddress shippingAddress) {
        return null;
    }
}
