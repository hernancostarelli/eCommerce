package com.challenge.midas.mapper;

import com.challenge.midas.dto.request.OrderRequest;
import com.challenge.midas.dto.request.ShippingAddressRequest;
import com.challenge.midas.dto.response.OrderResponse;
import com.challenge.midas.model.Order;
import com.challenge.midas.model.OrderDetail;
import com.challenge.midas.model.Payment;
import com.challenge.midas.repository.OrderRepository;
import com.challenge.midas.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderMapper {

    private final UserMapper userMapper;
    private final ProductMapper productMapper;
    private final UserRepository userRepository;
    private final OrderRepository repository;

    public Order convertToEntity(Order order, OrderRequest request) {

        /*private List<OrderDetail> orderDetails;
        private String totalAmount;
        private String idUser;
        private ShippingAddressRequest shippingAddress;
        private List<Payment> payments;*/

        order.setOrdenNumber(generateOrderNumber());
        double totalAmount = Double.parseDouble(request.getTotalAmount());
        order.setTotalAmount(totalAmount);
        order.setUser(userRepository.getReferenceById(request.getIdUser()));

        //order.setShippingAddress(request.getShippingAddress());

        //order.setPayment(request.getPayments());


       /* List<OrderDetail> orderDetails = new ArrayList<>();
        for (OrderDetailRequest orderDetailRequest : request.getOrderDetails()) {
            OrderDetail orderDetail = orderDetailRequestToOrderDetailConverter.convert(orderDetailRequest);
            orderDetails.add(orderDetail);
        }
        order.setOrderDetails(orderDetails);*/
        return order;
    }

    public OrderResponse convertToResponse(Order order) {
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setId(order.getId());
        orderResponse.setOrdenNumber(order.getOrdenNumber());
        orderResponse.setTotalAmount(order.getTotalAmount());
        orderResponse.setUser(userMapper.convertToResponse(order.getUser()));

        //orderResponse.setShippingAddress(order.getShippingAddress());

        //orderResponse.setProducts(productMapper.convertToResponseList(order.getProducts()));

        //orderResponse.setPayments(order.getPayments());

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String stringCreationDate = order.getCreationDate() != null ? sdf.format(order.getCreationDate()) : null;
        String stringModificationDate = order.getModificationDate() != null ? sdf.format(order.getModificationDate()) : null;
        orderResponse.setCreationDate(stringCreationDate);
        orderResponse.setModificationDate(stringModificationDate);
        orderResponse.setDeleted(String.valueOf(order.isDeleted()));
        return orderResponse;
    }

    public List<OrderResponse> convertToResponseList(List<Order> orderList) {
        return orderList.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    private String generateOrderNumber() {
        List<Order> orders = repository.findAll();
        int maxOrderNumber = orders.isEmpty() ? 0 : orders.stream().mapToInt(o -> Integer.parseInt(o.getOrdenNumber())).max().orElse(0);
        return String.format("%09d", maxOrderNumber + 1);
    }
}