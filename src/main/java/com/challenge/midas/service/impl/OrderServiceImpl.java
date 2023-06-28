package com.challenge.midas.service.impl;

import com.challenge.midas.dto.request.OrderRequest;
import com.challenge.midas.dto.response.OrderResponse;
import com.challenge.midas.enums.EExceptionMessage;
import com.challenge.midas.exception.OrderException;
import com.challenge.midas.exception.ProductException;
import com.challenge.midas.exception.ShippingAddressException;
import com.challenge.midas.exception.ShoppingCartException;
import com.challenge.midas.exception.UserException;
import com.challenge.midas.mapper.OrderMapper;
import com.challenge.midas.model.Order;
import com.challenge.midas.model.User;
import com.challenge.midas.repository.OrderRepository;
import com.challenge.midas.repository.UserRepository;
import com.challenge.midas.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements IOrderService {

    private final OrderRepository repository;
    private final OrderMapper mapper;
    private final UserRepository userRepository;


    @Override
    public OrderResponse create(OrderRequest request) throws OrderException, UserException, ShoppingCartException, ShippingAddressException, ProductException {
        Order order = mapper.convertToEntity(new Order(), request);
        return mapper.convertToResponse(repository.save(order));
    }

    @Override
    public OrderResponse modify(String idOrder, OrderRequest request) throws OrderException, UserException, ShoppingCartException, ShippingAddressException, ProductException {
        Optional<Order> optionalOrder = repository.findById(idOrder);
        if (optionalOrder.isPresent()) {
            Order order = mapper.convertToEntity(optionalOrder.get(), request);
            return mapper.convertToResponse(repository.save(order));
        } else {
            throw new OrderException(EExceptionMessage.ORDER_NOT_FOUND.toString());
        }
    }

    @Override
    public void enable(String idOrder) throws OrderException {
        Optional<Order> optionalOrder = repository.findById(idOrder);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            if (order.isDeleted()) {
                order.setDeleted(true);
                order.setModificationDate(new Date());
                repository.save(order);
            } else {
                throw new OrderException(EExceptionMessage.THE_ORDER_COULD_NOT_BE_ENABLED.toString());
            }
        } else {
            throw new OrderException(EExceptionMessage.ORDER_NOT_FOUND.toString());
        }
    }

    @Override
    public void disable(String idOrder) throws OrderException {
        Optional<Order> optionalOrder = repository.findById(idOrder);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            if (order.isDeleted()) {
                order.setDeleted(false);
                order.setModificationDate(new Date());
                repository.save(order);
            } else {
                throw new OrderException(EExceptionMessage.THE_ORDER_COULD_NOT_BE_DISABLE.toString());
            }
        } else {
            throw new OrderException(EExceptionMessage.ORDER_NOT_FOUND.toString());
        }
    }

    @Override
    public void delete(String idOrder) throws OrderException {
        Optional<Order> optionalOrder = repository.findById(idOrder);
        if (optionalOrder.isPresent()) {
            repository.delete(optionalOrder.get());
        } else {
            throw new OrderException(EExceptionMessage.ORDER_NOT_FOUND.toString());
        }
    }

    @Override
    public OrderResponse getById(String idOrder) throws OrderException {
        Optional<Order> optionalOrder = repository.findById(idOrder);
        if (optionalOrder.isPresent()) {
            return mapper.convertToResponse(optionalOrder.get());
        } else {
            throw new OrderException(EExceptionMessage.ORDER_NOT_FOUND.toString());
        }
    }

    @Override
    public List<OrderResponse> getAll(String value) throws OrderException {
        if (value == null) {
            value = "";
        }
        List<Order> orderList = repository.getByValue("%" + value + "%");
        if (orderList.isEmpty()) {
            throw new OrderException(EExceptionMessage.ORDER_NOT_FOUND.toString());
        }
        return mapper.convertToResponseList(orderList);
    }

    @Override
    public List<OrderResponse> getOrderByUser(String idUser) throws OrderException, UserException {
        Optional<User> optionalUser = userRepository.findById(idUser);
        if (optionalUser.isEmpty()) {
            throw new UserException(EExceptionMessage.USER_NOT_FOUND.toString());
        }
        List<Order> orderList = repository.getOrderByUser(idUser);
        if (orderList.isEmpty()) {
            throw new OrderException(EExceptionMessage.ORDER_NOT_FOUND.toString());
        }
        return mapper.convertToResponseList(orderList);
    }

    @Override
    public List<OrderResponse> getForEnable() throws OrderException {
        List<Order> orderList = repository.getByEnable();
        if (orderList.isEmpty()) {
            throw new OrderException(EExceptionMessage.THE_ORDER_LIST_IS_EMPTY.toString());
        }
        return mapper.convertToResponseList(orderList);
    }

    @Override
    public List<OrderResponse> getForDisable() throws OrderException {
        List<Order> orderList = repository.getByDisable();
        if (orderList.isEmpty()) {
            throw new OrderException(EExceptionMessage.THE_ORDER_LIST_IS_EMPTY.toString());
        }
        return mapper.convertToResponseList(orderList);
    }
}