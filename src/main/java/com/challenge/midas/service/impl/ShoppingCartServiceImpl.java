package com.challenge.midas.service.impl;

import com.challenge.midas.dto.request.ShoppingCartRequest;
import com.challenge.midas.dto.response.ShoppingCartResponse;
import com.challenge.midas.enums.EExceptionMessage;
import com.challenge.midas.exception.ProductException;
import com.challenge.midas.exception.ShoppingCartException;
import com.challenge.midas.exception.UserException;
import com.challenge.midas.mapper.ShoppingCartMapper;
import com.challenge.midas.model.ShoppingCart;
import com.challenge.midas.model.User;
import com.challenge.midas.repository.ShoppingCartRepository;
import com.challenge.midas.repository.UserRepository;
import com.challenge.midas.service.IShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements IShoppingCartService {

    private final ShoppingCartRepository repository;
    private final ShoppingCartMapper mapper;
    private final UserRepository userRepository;


    @Override
    public ShoppingCartResponse create(ShoppingCartRequest request) throws ShoppingCartException, UserException, ProductException {
        ShoppingCart shoppingCart = mapper.convertToEntity(new ShoppingCart(), request);
        return mapper.convertToResponse(repository.save(shoppingCart));
    }

    @Override
    public ShoppingCartResponse modify(String idShoppingCart, ShoppingCartRequest request) throws ShoppingCartException, UserException, ProductException {
        Optional<ShoppingCart> optionalShoppingCart = repository.findById(idShoppingCart);
        if (optionalShoppingCart.isPresent()) {
            ShoppingCart shoppingCart = mapper.convertToEntity(optionalShoppingCart.get(), request);
            return mapper.convertToResponse(repository.save(shoppingCart));
        } else {
            throw new ShoppingCartException(EExceptionMessage.SHOPPING_CART_NOT_FOUND.toString());
        }
    }

    @Override
    public void enable(String idShoppingCart) throws ShoppingCartException {
        Optional<ShoppingCart> optionalShoppingCart = repository.findById(idShoppingCart);
        if (optionalShoppingCart.isPresent()) {
            ShoppingCart shoppingCart = optionalShoppingCart.get();
            if (shoppingCart.isDeleted()) {
                shoppingCart.setDeleted(false);
                shoppingCart.setModificationDate(new Date());
                repository.save(shoppingCart);
            } else {
                throw new ShoppingCartException(EExceptionMessage.THE_SHOPPING_CART_COULD_NOT_BE_ENABLED.toString());
            }
        } else {
            throw new ShoppingCartException(EExceptionMessage.SHOPPING_CART_NOT_FOUND.toString());
        }
    }

    @Override
    public void disable(String idShoppingCart) throws ShoppingCartException {
        Optional<ShoppingCart> optionalShoppingCart = repository.findById(idShoppingCart);
        if (optionalShoppingCart.isPresent()) {
            ShoppingCart shoppingCart = optionalShoppingCart.get();
            if (!shoppingCart.isDeleted()) {
                shoppingCart.setDeleted(true);
                shoppingCart.setModificationDate(new Date());
                repository.save(optionalShoppingCart.get());
            } else {
                throw new ShoppingCartException(EExceptionMessage.THE_SHOPPING_CART_COULD_NOT_BE_DISABLE.toString());
            }
        } else {
            throw new ShoppingCartException(EExceptionMessage.SHOPPING_CART_NOT_FOUND.toString());
        }
    }

    @Override
    public void delete(String idShoppingCart) throws ShoppingCartException {
        Optional<ShoppingCart> optionalShoppingCart = repository.findById(idShoppingCart);
        if (optionalShoppingCart.isPresent()) {
            repository.delete(optionalShoppingCart.get());
        } else {
            throw new ShoppingCartException(EExceptionMessage.SHOPPING_CART_NOT_FOUND.toString());
        }
    }

    @Override
    public ShoppingCartResponse getById(String idShoppingCart) throws ShoppingCartException {
        Optional<ShoppingCart> optionalShoppingCart = repository.findById(idShoppingCart);
        if (optionalShoppingCart.isPresent()) {
            return mapper.convertToResponse(optionalShoppingCart.get());
        } else {
            throw new ShoppingCartException(EExceptionMessage.SHOPPING_CART_NOT_FOUND.toString());
        }
    }

    @Override
    public List<ShoppingCartResponse> getAll(String value) throws ShoppingCartException {
        if (value == null) {
            value = "";
        }
        List<ShoppingCart> shoppingCartList = repository.getByValue("%" + value + "%");
        if (shoppingCartList.isEmpty()) {
            throw new ShoppingCartException(EExceptionMessage.THE_SHOPPING_CART_LIST_IS_EMPTY.toString());
        }
        return mapper.convertToResponseList(shoppingCartList);
    }

    @Override
    public List<ShoppingCartResponse> getShoppingCartByUser(String idUser) throws ShoppingCartException, UserException {
        Optional<User> optionalUser = userRepository.findById(idUser);
        if (optionalUser.isEmpty()) {
            throw new UserException(EExceptionMessage.USER_NOT_FOUND.toString());
        }
        List<ShoppingCart> shoppingCartList = repository.getShoppingCartLByUser(idUser);
        if (shoppingCartList.isEmpty()) {
            throw new ShoppingCartException(EExceptionMessage.SHOPPING_CART_NOT_FOUND.toString());
        }
        return mapper.convertToResponseList(shoppingCartList);
    }

    @Override
    public List<ShoppingCartResponse> getForEnable() throws ShoppingCartException {
        List<ShoppingCart> shoppingCartList = repository.getByEnable();
        if (shoppingCartList.isEmpty()) {
            throw new ShoppingCartException(EExceptionMessage.THE_SHOPPING_CART_LIST_IS_EMPTY.toString());
        }
        return mapper.convertToResponseList(shoppingCartList);
    }

    @Override
    public List<ShoppingCartResponse> getForDisable() throws ShoppingCartException {
        List<ShoppingCart> shoppingCartList = repository.getByDisable();
        if (shoppingCartList.isEmpty()) {
            throw new ShoppingCartException(EExceptionMessage.THE_SHOPPING_CART_LIST_IS_EMPTY.toString());
        }
        return mapper.convertToResponseList(shoppingCartList);
    }
}