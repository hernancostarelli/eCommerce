package com.challenge.midas.controller;

import com.challenge.midas.dto.request.ShoppingCartRequest;
import com.challenge.midas.dto.response.ShoppingCartResponse;
import com.challenge.midas.exception.ProductException;
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

import static com.challenge.midas.config.ApiEndpoints.SHOPPING_CARD_URI;

@Validated
@Tag(name = "SHOPPING CART CONTROLLER")
@RestController
@RequestMapping(SHOPPING_CARD_URI)
@CrossOrigin(origins = "*")
public interface IShoppingCartController {

    @PostMapping(path = "/create-shopping-cart", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ShoppingCartResponse> create(@Valid @RequestBody ShoppingCartRequest request) throws ShoppingCartException, UserException, ProductException;

    @PutMapping(path = "/modify-shopping-cart/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ShoppingCartResponse> modify(@PathVariable("id") @NotNull String idShoppingCart, @Valid @RequestBody ShoppingCartRequest request) throws ShoppingCartException, UserException, ProductException;

    @PatchMapping(path = "/enable-shopping-cart/{id}")
    ResponseEntity<Void> enable(@PathVariable("id") @NotNull String idShoppingCart) throws ShoppingCartException;

    @PatchMapping(path = "/disable-shopping-cart/{id}")
    ResponseEntity<Void> disable(@PathVariable("id") @NotNull String idShoppingCart) throws ShoppingCartException;

    @DeleteMapping(path = "/delete-shopping-cart/{id}")
    ResponseEntity<Void> delete(@PathVariable("id") @NotNull String idShoppingCart) throws ShoppingCartException;

    @GetMapping(path = "/get-shopping-cart-by-id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ShoppingCartResponse> getById(@PathVariable("id") @NotNull String idShoppingCart) throws ShoppingCartException;

    @GetMapping(path = "/get-all-shopping-cart", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ShoppingCartResponse>> getAll(@RequestParam(required = false) String value) throws ShoppingCartException;

    @GetMapping(path = "/get-shopping-cart-by-user", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ShoppingCartResponse>> getShoppingCartByUser(@PathVariable("id") @NotNull String idUser) throws ShoppingCartException, UserException;

    @GetMapping(path = "/get-enable-shopping-cart", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ShoppingCartResponse>> getForEnable() throws ShoppingCartException;

    @GetMapping(path = "/get-disable-shopping-cart", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ShoppingCartResponse>> getForDisable() throws ShoppingCartException;
}