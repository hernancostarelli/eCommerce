package com.challenge.midas.service.impl;

import com.challenge.midas.dto.request.ShippingAddressRequest;
import com.challenge.midas.dto.response.ShippingAddressResponse;
import com.challenge.midas.enums.EExceptionMessage;
import com.challenge.midas.exception.ShippingAddressException;
import com.challenge.midas.exception.UserException;
import com.challenge.midas.mapper.ShippingAddressMapper;
import com.challenge.midas.model.ShippingAddress;
import com.challenge.midas.model.User;
import com.challenge.midas.repository.ShippingAddressRepository;
import com.challenge.midas.repository.UserRepository;
import com.challenge.midas.service.IShippingAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShippingAddressImpl implements IShippingAddressService {

    private final ShippingAddressRepository repository;
    private final ShippingAddressMapper mapper;
    private final UserRepository userRepository;


    @Override
    public ShippingAddressResponse create(ShippingAddressRequest request) throws ShippingAddressException {
        ShippingAddress response = mapper.convertToEntity(new ShippingAddress(), request);
        return mapper.convertToResponse(repository.save(response));
    }

    @Override
    public ShippingAddressResponse modify(String idShippingAddress, ShippingAddressRequest request) throws ShippingAddressException {
        Optional<ShippingAddress> shippingAddressOptional = repository.findById(idShippingAddress);
        if (shippingAddressOptional.isPresent()) {
            ShippingAddress response = mapper.convertToEntity(shippingAddressOptional.get(), request);
            return mapper.convertToResponse(repository.save(response));
        } else {
            throw new ShippingAddressException(EExceptionMessage.SHIPPING_ADDRESS_NOT_FOUND.toString());
        }
    }

    @Override
    public void enable(String idShippingAddress) throws ShippingAddressException {
        Optional<ShippingAddress> shippingAddressOptional = repository.findById(idShippingAddress);
        if (shippingAddressOptional.isPresent()) {
            ShippingAddress shippingAddress = shippingAddressOptional.get();
            if (shippingAddress.isDeleted()) {
                shippingAddress.setDeleted(true);
                shippingAddress.setModificationDate(new Date());
                repository.save(shippingAddressOptional.get());
            } else {
                throw new ShippingAddressException(EExceptionMessage.THE_SHIPPING_ADDRESS_COULD_NOT_BE_ENABLED.toString());
            }
        } else {
            throw new ShippingAddressException(EExceptionMessage.SHIPPING_ADDRESS_NOT_FOUND.toString());
        }
    }

    @Override
    public void disable(String idShippingAddress) throws ShippingAddressException {
        Optional<ShippingAddress> shippingAddressOptional = repository.findById(idShippingAddress);
        if (shippingAddressOptional.isPresent()) {
            ShippingAddress shippingAddress = shippingAddressOptional.get();
            if (!shippingAddress.isDeleted()) {
                shippingAddress.setDeleted(false);
                shippingAddress.setModificationDate(new Date());
                repository.save(shippingAddressOptional.get());
            } else {
                throw new ShippingAddressException(EExceptionMessage.THE_SHIPPING_ADDRESS_COULD_NOT_BE_DISABLED.toString());
            }
        } else {
            throw new ShippingAddressException(EExceptionMessage.SHIPPING_ADDRESS_NOT_FOUND.toString());
        }
    }

    @Override
    public void delete(String idShippingAddress) throws ShippingAddressException {
        Optional<ShippingAddress> shippingAddressOptional = repository.findById(idShippingAddress);
        if (shippingAddressOptional.isPresent()) {
            repository.delete(shippingAddressOptional.get());
        } else {
            throw new ShippingAddressException(EExceptionMessage.SHIPPING_ADDRESS_NOT_FOUND.toString());
        }
    }

    @Override
    public ShippingAddressResponse getById(String idShippingAddress) throws ShippingAddressException {
        Optional<ShippingAddress> shippingAddressOptional = repository.findById(idShippingAddress);
        if (shippingAddressOptional.isPresent()) {
            return mapper.convertToResponse(shippingAddressOptional.get());
        } else {
            throw new ShippingAddressException(EExceptionMessage.SHIPPING_ADDRESS_NOT_FOUND.toString());
        }
    }

    @Override
    public List<ShippingAddressResponse> getAll(String value) throws ShippingAddressException {
        List<ShippingAddress> shippingAddressList = repository.getByValue("%" + (value != null ? value : "") + "%");
        if (shippingAddressList.isEmpty()) {
            throw new ShippingAddressException(EExceptionMessage.THE_SHIPPING_ADDRESS_LIST_IS_EMPTY.toString());
        }
        return mapper.convertToResponseList(shippingAddressList);
    }

    @Override
    public List<ShippingAddressResponse> getShippingAddressByUser(String idUser) throws ShippingAddressException, UserException {
        Optional<User> optionalUser = userRepository.findById(idUser);
        if (optionalUser.isEmpty()) {
            throw new UserException(EExceptionMessage.USER_NOT_FOUND.toString());
        }
        List<ShippingAddress> shippingAddressList = repository.getShippingAddressByUser(idUser);
        if (shippingAddressList.isEmpty()) {
            throw new ShippingAddressException(EExceptionMessage.THE_SHIPPING_ADDRESS_LIST_IS_EMPTY.toString());
        }
        return mapper.convertToResponseList(shippingAddressList);
    }

    @Override
    public List<ShippingAddressResponse> getForEnable() throws ShippingAddressException {
        List<ShippingAddress> shippingAddressList = repository.getByEnable();
        if (shippingAddressList.isEmpty()) {
            throw new ShippingAddressException(EExceptionMessage.THE_SHIPPING_ADDRESS_LIST_IS_EMPTY.toString());
        }
        return mapper.convertToResponseList(shippingAddressList);
    }

    @Override
    public List<ShippingAddressResponse> getForDisable() throws ShippingAddressException {
        List<ShippingAddress> shippingAddressList = repository.getByDisable();
        if (shippingAddressList.isEmpty()) {
            throw new ShippingAddressException(EExceptionMessage.THE_SHIPPING_ADDRESS_LIST_IS_EMPTY.toString());
        }
        return mapper.convertToResponseList(shippingAddressList);
    }
}