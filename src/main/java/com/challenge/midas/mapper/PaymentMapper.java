package com.challenge.midas.mapper;

import com.challenge.midas.dto.request.PaymentRequest;
import com.challenge.midas.dto.response.PaymentResponse;
import com.challenge.midas.model.Order;
import com.challenge.midas.model.Payment;
import com.challenge.midas.model.User;
import com.challenge.midas.repository.OrderRepository;
import com.challenge.midas.repository.UserRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PaymentMapper {

    public static final String DD_MM_YYYY = "dd/MM/yyyy";
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public PaymentMapper(@Lazy UserRepository userRepository, @Lazy OrderRepository orderRepository, @Lazy OrderMapper orderMapper) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    public Payment convertToEntity(Payment payment, PaymentRequest request) {
        Optional<User> optionalUser = userRepository.findById(request.getIdUser());
        if (optionalUser.isPresent()) {
            Optional<Order> optionalOrder = orderRepository.findById(request.getIdOrder());
            if (optionalOrder.isPresent()) {
                payment.setUser(optionalUser.get());
                payment.setOrder(optionalOrder.get());
                double amount = optionalOrder.get().getTotalAmount();
                payment.setAmount(amount);
            }
        }
        return payment;
    }

    public PaymentResponse convertToResponse(Payment payment) {
        PaymentResponse response = new PaymentResponse();
        response.setId(payment.getId());

        String formattedAmount = String.format("%.2f", payment.getAmount());
        response.setAmount(formattedAmount);

        response.setPaymentDate(payment.getPaymentDate() != null ?
                new SimpleDateFormat(DD_MM_YYYY).format(payment.getPaymentDate()) : null);
        response.setUser(payment.getUser().getFullName());
        response.setOrder(orderMapper.convertToResponse(payment.getOrder()));
        response.setCreationDate(payment.getCreationDate() != null ?
                new SimpleDateFormat(DD_MM_YYYY).format(payment.getCreationDate()) : null);
        response.setModificationDate(payment.getModificationDate() != null ?
                new SimpleDateFormat(DD_MM_YYYY).format(payment.getModificationDate()) : null);
        response.setDeleted(String.valueOf(payment.isDeleted()));
        return response;
    }

    public List<PaymentResponse> convertToResponseList(List<Payment> paymentList) {
        return paymentList.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
}