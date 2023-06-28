package com.challenge.midas.mapper;

import com.challenge.midas.dto.response.OrderDetailResponse;
import com.challenge.midas.model.OrderDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderDetailMapper {
    public List<OrderDetailResponse> convertToResponseList(List<OrderDetail> orderDetails) {
        return null;
    }
}