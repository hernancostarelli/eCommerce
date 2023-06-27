package com.challenge.midas.controller;

import com.challenge.midas.dto.request.user.UserRequest;
import com.challenge.midas.dto.request.user.UserRequestModify;
import com.challenge.midas.dto.request.user.UserRequestModifyPassword;
import com.challenge.midas.dto.response.UserResponse;
import com.challenge.midas.exception.EmailAlreadyExistException;
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

import static com.challenge.midas.config.ApiConstants.USER_URI;

@Validated
@Tag(name = "USER CONTROLLER")
@RestController
@RequestMapping(USER_URI)
@CrossOrigin(origins = "*")
public interface IUserController {

    @PostMapping(path = "/create-user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserResponse> create(@Valid @RequestBody UserRequest request) throws UserException, EmailAlreadyExistException;

    @PutMapping(path = "/modify-user/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserResponse> modify(@PathVariable("id") @NotNull String idUser, @Valid @RequestBody UserRequestModify request) throws UserException, EmailAlreadyExistException;

    @PutMapping(path = "/modify-user-password/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> modifyPassword(@PathVariable("id") @NotNull String idUser, @Valid @RequestBody UserRequestModifyPassword request) throws UserException;

    @PatchMapping(path = "/enable-user/{id}")
    ResponseEntity<Void> enable(@PathVariable("id") @NotNull String idUser) throws UserException;

    @PatchMapping(path = "/disable-user/{id}")
    ResponseEntity<Void> disable(@PathVariable("id") @NotNull String idUser) throws UserException;

    @DeleteMapping(path = "/delete-user/{id}")
    ResponseEntity<Void> delete(@NotNull @PathVariable("id") String idUser) throws UserException;

    @GetMapping(path = "/get-user-by-id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserResponse> getById(@NotNull @PathVariable("id") String idUser) throws UserException;

    @GetMapping(path = "/get-all-user", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<UserResponse>> getAll(@RequestParam(required = false) String value) throws UserException;

    @GetMapping(path = "/get-enable-user", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<UserResponse>> getForEnable() throws UserException;

    @GetMapping(path = "/get-disable-user", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<UserResponse>> getForDisable() throws UserException;
}