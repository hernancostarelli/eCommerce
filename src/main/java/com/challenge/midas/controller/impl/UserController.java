package com.challenge.midas.controller.impl;

import com.challenge.midas.controller.IUserController;
import com.challenge.midas.dto.request.user.UserRequest;
import com.challenge.midas.dto.request.user.UserRequestModify;
import com.challenge.midas.dto.request.user.UserRequestModifyPassword;
import com.challenge.midas.dto.response.UserResponse;
import com.challenge.midas.exception.EmailAlreadyExistException;
import com.challenge.midas.exception.UserException;
import com.challenge.midas.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController implements IUserController {

    private final IUserService service;

    @Override
    public ResponseEntity<UserResponse> create(UserRequest request) throws UserException, EmailAlreadyExistException {
        UserResponse response = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<UserResponse> modify(String idUser, UserRequestModify request) throws UserException, EmailAlreadyExistException {
        UserResponse response = service.modify(idUser, request);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> modifyPassword(String idUser, UserRequestModifyPassword request) throws UserException {
        service.modifyPassword(idUser, request);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> enable(String idUser) throws UserException {
        service.enable(idUser);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> disable(String idUser) throws UserException {
        service.disable(idUser);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> delete(String idUser) throws UserException {
        service.delete(idUser);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<UserResponse> getById(String idUser) throws UserException {
        UserResponse response = service.getById(idUser);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<List<UserResponse>> getAll(String value) throws UserException {
        List<UserResponse> response = service.getAll(value);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<List<UserResponse>> getForEnable() throws UserException {
        List<UserResponse> response = service.getForEnable();
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<List<UserResponse>> getForDisable() throws UserException {
        List<UserResponse> response = service.getForDisable();
        return ResponseEntity.ok(response);
    }
}