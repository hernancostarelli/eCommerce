package com.challenge.midas.mapper;

import com.challenge.midas.dto.response.PaymentResponse;
import com.challenge.midas.model.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentMapper {
    public PaymentResponse converToResponse(Payment payment) {
        return null;
    }
}