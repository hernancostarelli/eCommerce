package com.challenge.midas.mapper;

import com.challenge.midas.dto.request.OrderRequest;
import com.challenge.midas.dto.request.ShippingAddressRequest;
import com.challenge.midas.dto.response.OrderResponse;
import com.challenge.midas.enums.EExceptionMessage;
import com.challenge.midas.exception.ProductException;
import com.challenge.midas.exception.ShippingAddressException;
import com.challenge.midas.exception.ShoppingCartException;
import com.challenge.midas.exception.UserException;
import com.challenge.midas.model.Order;
import com.challenge.midas.model.OrderDetail;
import com.challenge.midas.model.Product;
import com.challenge.midas.model.ShippingAddress;
import com.challenge.midas.model.ShoppingCart;
import com.challenge.midas.model.User;
import com.challenge.midas.repository.OrderRepository;
import com.challenge.midas.repository.ProductRepository;
import com.challenge.midas.repository.ShoppingCartRepository;
import com.challenge.midas.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderMapper {

    public static final String DD_MM_YYYY = "dd/MM/yyyy";
    private final UserMapper userMapper;
    private final OrderRepository repository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final ShippingAddressMapper shippingAddressMapper;
    private final OrderDetailMapper orderDetailMapper;
    private final PaymentMapper paymentMapper;

    public Order convertToEntity(Order order, OrderRequest request) throws UserException, ShoppingCartException, ProductException, ShippingAddressException {
        order.setOrdenNumber(generateOrderNumber());
        Optional<User> optionalUser = userRepository.findById(request.getIdUser());
        if (optionalUser.isPresent()) {
            Optional<ShoppingCart> optionalShoppingCart = shoppingCartRepository.findById(request.getIdShoppingCart());
            if (optionalShoppingCart.isPresent()) {
                ShoppingCart shoppingCart = optionalShoppingCart.get();
                List<Product> productList = shoppingCart.getProducts();
                double totalAmount = getTotalAmount(order, productList);
                order.setTotalAmount(totalAmount);
                order.setUser(optionalUser.get());
                order.setShippingAddress(getShippingAddress(request));
                repository.save(order);
            } else {
                throw new ShoppingCartException(EExceptionMessage.SHOPPING_CART_NOT_FOUND.getMessage());
            }
        } else {
            throw new UserException(EExceptionMessage.USER_NOT_FOUND.getMessage());
        }
        return order;
    }

    public OrderResponse convertToResponse(Order order) {
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setId(order.getId());
        orderResponse.setOrdenNumber(order.getOrdenNumber());
        orderResponse.setOrderDetails(orderDetailMapper.convertToResponseList(order.getOrderDetails()));
        orderResponse.setTotalAmount(order.getTotalAmount());
        orderResponse.setUser(userMapper.convertToResponse(order.getUser()));
        orderResponse.setShippingAddress(shippingAddressMapper.convertToResponse(order.getShippingAddress()));
        orderResponse.setPayments(paymentMapper.converToResponse(order.getPayment()));
        orderResponse.setCreationDate(order.getCreationDate() != null ?
                new SimpleDateFormat(DD_MM_YYYY).format(order.getCreationDate()) : null);
        orderResponse.setModificationDate(order.getModificationDate() != null ?
                new SimpleDateFormat(DD_MM_YYYY).format(order.getModificationDate()) : null);
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

    private double getTotalAmount(Order order, List<Product> productList) throws ProductException {
        // CREAR UN MAPA PARA ALMACENAR LOS PRODUCTOS Y SUS CANTIDADES.
        Map<String, Integer> productQuantities = new HashMap<>();
        // RECORRER LA LISTA DE PRODUCTOS Y SUMAR LAS CANTIDADES DE CADA UNO.
        for (Product product : productList) {
            String idProduct = product.getId();
            if (productQuantities.containsKey(idProduct)) {
                productQuantities.put(idProduct, productQuantities.get(idProduct) + 1);
            } else {
                productQuantities.put(idProduct, 1);
            }
        }
        // CREAR UN DETALLE DE ORDEN PARA CADA PRODUCTO Y ESTABLECER LA CANTIDAD CORRESPONDIENTE.
        for (Map.Entry<String, Integer> entry : productQuantities.entrySet()) {
            String productId = entry.getKey();
            Integer quantity = entry.getValue();
            Optional<Product> optionalProduct = productRepository.findById(productId);
            if (optionalProduct.isPresent()) {
                Product product = optionalProduct.get();
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setProduct(product);
                orderDetail.setUnitPrice(product.getPrice());
                orderDetail.setQuantity(quantity);
                order.getOrderDetails().add(orderDetail);
            } else {
                throw new ProductException(EExceptionMessage.PRODUCT_NOT_FOUND.getMessage());
            }
        }
        // OBTENGO EL VALOR DEL MONTO TOTAL DE LA ORDEN
        double totalAmount = 0.0;
        for (OrderDetail orderDetail : order.getOrderDetails()) {
            double price = orderDetail.getUnitPrice() * orderDetail.getQuantity();
            totalAmount += price;
        }
        return totalAmount;
    }

    private ShippingAddress getShippingAddress(OrderRequest request) throws ShippingAddressException {
        ShippingAddressRequest shippingAddressRequest = request.getShippingAddress();
        return shippingAddressMapper.convertToEntity(new ShippingAddress(), shippingAddressRequest);
    }
}