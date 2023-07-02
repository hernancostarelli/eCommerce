package com.challenge.midas.mapper;

import com.challenge.midas.dto.request.OrderDetailRequest;
import com.challenge.midas.dto.response.OrderDetailResponse;
import com.challenge.midas.model.OrderDetail;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrderDetailMapper {

    public static final String DD_MM_YYYY = "dd/MM/yyyy";

    public OrderDetail convertToEntity(OrderDetail orderDetail, OrderDetailRequest request) {
        orderDetail.setQuantity(request.getQuantity());
        orderDetail.setUnitPrice(request.getUnitPrice());
        orderDetail.setProduct(request.getProduct());
        orderDetail.setOrder(request.getOrder());
        return orderDetail;
    }

    public OrderDetailResponse convertToResponse(OrderDetail orderDetail) {
        OrderDetailResponse response = new OrderDetailResponse();
        response.setId(orderDetail.getId());
        response.setQuantity(orderDetail.getQuantity());

        String stringUnitPrice = getUnitPrice(orderDetail);
        response.setUnitPrice(Double.parseDouble(stringUnitPrice));

        response.setProduct(orderDetail.getProduct());
        response.setOrder(orderDetail.getOrder());
        response.setCreationDate(orderDetail.getCreationDate() != null ?
                new SimpleDateFormat(DD_MM_YYYY).format(orderDetail.getCreationDate()) : null);
        response.setModificationDate(orderDetail.getModificationDate() != null ?
                new SimpleDateFormat(DD_MM_YYYY).format(orderDetail.getModificationDate()) : null);
        response.setDeleted(String.valueOf(orderDetail.isDeleted()));
        return response;
    }

    public List<OrderDetailResponse> convertToResponseList(List<OrderDetail> orderDetails) {
        List<OrderDetailResponse> responseList = new ArrayList<>();
        for (OrderDetail orderDetail : orderDetails) {
            responseList.add(convertToResponse(orderDetail));
        }
        return responseList;
    }

    private static String getUnitPrice(OrderDetail orderDetail) {
        DecimalFormat df = new DecimalFormat("#.##");
        double unitPrice = orderDetail.getUnitPrice();
        return df.format(unitPrice);
    }
}