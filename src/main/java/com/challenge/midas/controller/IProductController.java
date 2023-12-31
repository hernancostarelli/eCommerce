package com.challenge.midas.controller;

import com.challenge.midas.dto.request.ProductRequest;
import com.challenge.midas.dto.response.ProductResponse;
import com.challenge.midas.exception.ProductException;
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

import static com.challenge.midas.config.ApiEndpoints.PRODUCT_URI;

@Validated
@Tag(name = "PRODUCT CONTROLLER")
@RestController
@RequestMapping(PRODUCT_URI)
@CrossOrigin(origins = "*")
public interface IProductController {

    @PostMapping(path = "/create-product", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ProductResponse> create(@Valid @RequestBody ProductRequest request) throws ProductException, UserException;

    @PutMapping(path = "/modify-product/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ProductResponse> modify(@PathVariable("id") @NotNull String idProduct, @Valid @RequestBody ProductRequest request) throws ProductException, UserException;

    @PatchMapping(path = "/enable-product/{id}")
    ResponseEntity<Void> enable(@PathVariable("id") @NotNull String idProduct) throws ProductException;

    @PatchMapping(path = "/disable-product/{id}")
    ResponseEntity<Void> disable(@PathVariable("id") @NotNull String idProduct) throws ProductException;

    @DeleteMapping(path = "/delete-product/{id}")
    ResponseEntity<Void> delete(@PathVariable("id") @NotNull String idProduct) throws ProductException;

    @GetMapping(path = "/get-product-by-id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ProductResponse> getById(@PathVariable("id") @NotNull String idProduct) throws ProductException;

    @GetMapping(path = "/get-all-product", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ProductResponse>> getAll(@RequestParam(required = false) String value) throws ProductException;

    @GetMapping(path = "/get-enable-product", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ProductResponse>> getForEnable() throws ProductException;

    @GetMapping(path = "/get-disable-product", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ProductResponse>> getForDisable() throws ProductException;
}