package com.challenge.midas.service.impl;

import com.challenge.midas.dto.request.OrderDetailRequest;
import com.challenge.midas.dto.response.OrderDetailResponse;
import com.challenge.midas.enums.EExceptionMessage;
import com.challenge.midas.exception.OrderDetailException;
import com.challenge.midas.mapper.OrderDetailMapper;
import com.challenge.midas.model.OrderDetail;
import com.challenge.midas.repository.OrderDetailRepository;
import com.challenge.midas.service.IOrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements IOrderDetailService {

    private final OrderDetailRepository repository;
    private final OrderDetailMapper mapper;

    @Override
    public OrderDetailResponse create(OrderDetailRequest request) throws OrderDetailException {
        OrderDetail orderDetail = mapper.convertToEntity(new OrderDetail(), request);
        return mapper.convertToResponse(repository.save(orderDetail));
    }

    @Override
    public OrderDetailResponse modify(String idOrderDetail, OrderDetailRequest request) throws OrderDetailException {
        Optional<OrderDetail> optionalOrderDetail = repository.findById(idOrderDetail);
        if (optionalOrderDetail.isPresent()) {
            OrderDetail orderDetail = mapper.convertToEntity(optionalOrderDetail.get(), request);
            return mapper.convertToResponse(repository.save(orderDetail));
        } else {
            throw new OrderDetailException(EExceptionMessage.ORDER_DETAIL_NOT_FOUND.toString());
        }
    }

    @Override
    public void enable(String idOrder) throws OrderDetailException {
        Optional<OrderDetail> optionalOrderDetail = repository.findById(idOrder);
        if (optionalOrderDetail.isPresent()) {
            OrderDetail orderDetail = optionalOrderDetail.get();
            if (orderDetail.isDeleted()) {
                orderDetail.setDeleted(true);
                orderDetail.setModificationDate(new Date());
                repository.save(orderDetail);
            } else {
                throw new OrderDetailException(EExceptionMessage.THE_ORDER_DETAIL_COULD_NOT_BE_ENABLED.toString());
            }
        } else {
            throw new OrderDetailException(EExceptionMessage.ORDER_DETAIL_NOT_FOUND.toString());
        }
    }

    @Override
    public void disable(String idOrder) throws OrderDetailException {
        Optional<OrderDetail> optionalOrderDetail = repository.findById(idOrder);
        if (optionalOrderDetail.isPresent()) {
            OrderDetail orderDetail = optionalOrderDetail.get();
            if (orderDetail.isDeleted()) {
                orderDetail.setDeleted(false);
                orderDetail.setModificationDate(new Date());
                repository.save(orderDetail);
            } else {
                throw new OrderDetailException(EExceptionMessage.THE_ORDER_DETAIL_COULD_NOT_BE_DISABLED.toString());
            }
        } else {
            throw new OrderDetailException(EExceptionMessage.ORDER_DETAIL_NOT_FOUND.toString());
        }
    }

    @Override
    public void delete(String idOrder) throws OrderDetailException {
        Optional<OrderDetail> optionalOrderDetail = repository.findById(idOrder);
        if (optionalOrderDetail.isPresent()) {
            repository.delete(optionalOrderDetail.get());
        } else {
            throw new OrderDetailException(EExceptionMessage.ORDER_DETAIL_NOT_FOUND.toString());
        }
    }

    @Override
    public OrderDetailResponse getById(String idOrder) throws OrderDetailException {
        Optional<OrderDetail> optionalOrderDetail = repository.findById(idOrder);
        if (optionalOrderDetail.isPresent()) {
            return mapper.convertToResponse(optionalOrderDetail.get());
        } else {
            throw new OrderDetailException(EExceptionMessage.ORDER_DETAIL_NOT_FOUND.toString());
        }
    }

    @Override
    public List<OrderDetailResponse> getAll(String value) throws OrderDetailException {
        if (value == null) {
            value = "";
        }
        List<OrderDetail> orderDetails = repository.getByValue("%" + value + "%");
        if (orderDetails.isEmpty()) {
            throw new OrderDetailException(EExceptionMessage.THE_ORDER_DETAIL_LIST_IS_EMPTY.toString());
        }
        return mapper.convertToResponseList(orderDetails);
    }

    @Override
    public List<OrderDetailResponse> getForEnable() throws OrderDetailException {
        List<OrderDetail> orderDetailList = repository.getByEnable();
        if (orderDetailList.isEmpty()) {
            throw new OrderDetailException(EExceptionMessage.THE_ORDER_DETAIL_LIST_IS_EMPTY.toString());
        }
        return mapper.convertToResponseList(orderDetailList);
    }

    @Override
    public List<OrderDetailResponse> getForDisable() throws OrderDetailException {
        List<OrderDetail> orderDetailList = repository.getByDisable();
        if (orderDetailList.isEmpty()) {
            throw new OrderDetailException(EExceptionMessage.THE_ORDER_DETAIL_LIST_IS_EMPTY.toString());
        }
        return mapper.convertToResponseList(orderDetailList);
    }
}