package com.challenge.midas.controller;

import com.challenge.midas.dto.request.PaymentRequest;
import com.challenge.midas.dto.response.PaymentResponse;
import com.challenge.midas.exception.PaymentException;
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

import static com.challenge.midas.config.ApiEndpoints.PAYMENT_URI;

@Validated
@Tag(name = "PAYMENT CONTROLLER")
@RestController
@RequestMapping(PAYMENT_URI)
@CrossOrigin(origins = "*")
public interface IPaymentController {

    @PostMapping(path = "/create-payment", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PaymentResponse> create(@Valid @RequestBody PaymentRequest request) throws PaymentException;

    @PutMapping(path = "/modify-payment/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PaymentResponse> modify(@PathVariable("id") @NotNull String idPayment, @Valid @RequestBody PaymentRequest request) throws PaymentException;

    @PatchMapping(path = "/enable-payment/{id}")
    ResponseEntity<Void> enable(@PathVariable("id") @NotNull String idPayment) throws PaymentException;

    @PatchMapping(path = "/disable-payment/{id}")
    ResponseEntity<Void> disable(@PathVariable("id") @NotNull String idPayment) throws PaymentException;

    @DeleteMapping(path = "/delete-payment/{id}")
    ResponseEntity<Void> delete(@PathVariable("id") @NotNull String idPayment) throws PaymentException;

    @GetMapping(path = "/get-payment-by-id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PaymentResponse> getById(@PathVariable("id") @NotNull String idPayment) throws PaymentException;

    @GetMapping(path = "/get-all-payment", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<PaymentResponse>> getAll(@RequestParam(required = false) String value) throws PaymentException;

    @GetMapping(path = "/get-payment-by-user", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<PaymentResponse>> getPaymentByUser(@PathVariable("id") @NotNull String idPayment) throws PaymentException, UserException;

    @GetMapping(path = "/get-enable-payment", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<PaymentResponse>> getForEnable() throws PaymentException;

    @GetMapping(path = "/get-disable-payment", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<PaymentResponse>> getForDisable() throws PaymentException;
}