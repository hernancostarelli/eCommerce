package com.challenge.midas.controller;

import com.challenge.midas.dto.request.OrderDetailRequest;
import com.challenge.midas.dto.response.OrderDetailResponse;
import com.challenge.midas.exception.OrderDetailException;
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

import static com.challenge.midas.config.ApiEndpoints.ORDER_DETAIL_URI;

@Validated
@Tag(name = "ORDER DETAIL CONTROLLER")
@RestController
@RequestMapping(ORDER_DETAIL_URI)
@CrossOrigin(origins = "*")
public interface IOrderDetailController {

    @PostMapping(path = "/create-order-detail", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<OrderDetailResponse> create(@Valid @RequestBody OrderDetailRequest request) throws OrderDetailException;

    @PutMapping(path = "/modify-order-detail/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<OrderDetailResponse> modify(@PathVariable("id") @NotNull String idOrderDetail, @Valid @RequestBody OrderDetailRequest request) throws OrderDetailException;

    @PatchMapping(path = "/enable-order-detail/{id}")
    ResponseEntity<Void> enable(@PathVariable("id") @NotNull String idOrderDetail) throws OrderDetailException;

    @PatchMapping(path = "/disable-order-detail/{id}")
    ResponseEntity<Void> disable(@PathVariable("id") @NotNull String idOrderDetail) throws OrderDetailException;

    @DeleteMapping(path = "/delete-order-detail/{id}")
    ResponseEntity<Void> delete(@PathVariable("id") @NotNull String idOrderDetail) throws OrderDetailException;

    @GetMapping(path = "/get-order-detail-by-id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<OrderDetailResponse> getById(@PathVariable("id") @NotNull String idOrderDetail) throws OrderDetailException;

    @GetMapping(path = "/get-all-order-detail", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<OrderDetailResponse>> getAll(@RequestParam(required = false) String value) throws OrderDetailException;

    @GetMapping(path = "/get-enable-order-detail", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<OrderDetailResponse>> getForEnable() throws OrderDetailException;

    @GetMapping(path = "/get-disable-order-detail", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<OrderDetailResponse>> getForDisable() throws OrderDetailException;
}