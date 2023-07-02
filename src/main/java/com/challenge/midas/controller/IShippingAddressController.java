package com.challenge.midas.controller;

import com.challenge.midas.dto.request.ShippingAddressRequest;
import com.challenge.midas.dto.response.ShippingAddressResponse;
import com.challenge.midas.exception.ShippingAddressException;
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

import static com.challenge.midas.config.ApiConstants.SHIPPING_ADDRESS_URI;

@Validated
@Tag(name = "SHIPPING ADDRESS CONTROLLER")
@RestController
@RequestMapping(SHIPPING_ADDRESS_URI)
@CrossOrigin(origins = "*")
public interface IShippingAddressController {

    @PostMapping(path = "/create-shipping-address", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ShippingAddressResponse> create(@Valid @RequestBody ShippingAddressRequest request) throws ShippingAddressException;

    @PutMapping(path = "/modify-shipping-address/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ShippingAddressResponse> modify(@PathVariable("id") @NotNull String idShippingAddress, @Valid @RequestBody ShippingAddressRequest request) throws ShippingAddressException;

    @PatchMapping(path = "/enable-shipping-address/{id}")
    ResponseEntity<Void> enable(@PathVariable("id") @NotNull String idShippingAddress) throws ShippingAddressException;

    @PatchMapping(path = "/disable-shipping-address/{id}")
    ResponseEntity<Void> disable(@PathVariable("id") @NotNull String idShippingAddress) throws ShippingAddressException;

    @DeleteMapping(path = "/delete-shipping-address/{id}")
    ResponseEntity<Void> delete(@NotNull @PathVariable("id") String idShippingAddress) throws ShippingAddressException;

    @GetMapping(path = "/get-shipping-address-by-id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ShippingAddressResponse> getById(@NotNull @PathVariable("id") String idShippingAddress) throws ShippingAddressException;

    @GetMapping(path = "/get-all-shipping-address", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ShippingAddressResponse>> getAll(@RequestParam(required = false) String value) throws ShippingAddressException;

    @GetMapping(path = "/get-shipping-address-by-user", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ShippingAddressResponse>> getShippingAddressByUser(@PathVariable("id") @NotNull String idShippingAddress) throws ShippingAddressException, UserException;

    @GetMapping(path = "/get-enable-shipping-address", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ShippingAddressResponse>> getForEnable() throws ShippingAddressException;

    @GetMapping(path = "/get-disable-shipping-address", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ShippingAddressResponse>> getForDisable() throws ShippingAddressException;
}