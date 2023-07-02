package com.challenge.midas.controller;

import com.challenge.midas.dto.request.OrderRequest;
import com.challenge.midas.dto.response.OrderResponse;
import com.challenge.midas.exception.OrderException;
import com.challenge.midas.exception.ProductException;
import com.challenge.midas.exception.ShippingAddressException;
import com.challenge.midas.exception.ShoppingCartException;
import com.challenge.midas.exception.UserException;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

import static com.challenge.midas.config.ApiConstants.ORDER_URI;

@Validated
@Tag(name = "ORDER CONTROLLER")
@RestController
@RequestMapping(ORDER_URI)
@CrossOrigin(origins = "*")
public interface IOrderController {

    @PostMapping(path = "/create-order", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<OrderResponse> create(@Valid @RequestBody OrderRequest request) throws OrderException, ShoppingCartException, ShippingAddressException, UserException, ProductException;

    @PutMapping(path = "/modify-order/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<OrderResponse> modify(@PathVariable("id") @NotNull String idOrder, @Valid @RequestBody OrderRequest request) throws OrderException, ShoppingCartException, ShippingAddressException, UserException, ProductException;

    @PatchMapping(path = "/enable-order/{id}")
    ResponseEntity<Void> enable(@PathVariable("id") @NotNull String idOrder) throws OrderException;

    @PatchMapping(path = "/disable-order/{id}")
    ResponseEntity<Void> disable(@PathVariable("id") @NotNull String idOrder) throws OrderException;

    @DeleteMapping(path = "/delete-order/{id}")
    ResponseEntity<Void> delete(@PathVariable("id") @NotNull String idOrder) throws OrderException;

    @GetMapping(path = "/get-order-by-id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<OrderResponse> getById(@PathVariable("id") @NotNull String idOrder) throws OrderException;

    @GetMapping(path = "/get-all-order", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<OrderResponse>> getAll(@RequestParam(required = false) String value) throws OrderException;

    @GetMapping(path = "/get-order-by-user", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<OrderResponse>> getOrderByUser(@PathVariable("id") @NotNull String idOrder) throws OrderException, UserException;

    @GetMapping(path = "/get-enable-order", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<OrderResponse>> getForEnable() throws OrderException;

    @GetMapping(path = "/get-disable-order", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<OrderResponse>> getForDisable() throws OrderException;
}