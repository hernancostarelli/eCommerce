package com.challenge.midas.service;

import com.challenge.midas.dto.request.user.UserRequest;
import com.challenge.midas.dto.request.user.UserRequestModify;
import com.challenge.midas.dto.request.user.UserRequestModifyPassword;
import com.challenge.midas.dto.response.UserResponse;
import com.challenge.midas.exception.EmailAlreadyExistException;
import com.challenge.midas.exception.UserException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface IUserService {

    @Transactional
    UserResponse create(UserRequest request) throws UserException, EmailAlreadyExistException;

    @Transactional
    UserResponse modify(String idUser, UserRequestModify request) throws UserException, EmailAlreadyExistException;

    @Transactional
    void modifyPassword(String idUser, UserRequestModifyPassword request) throws UserException;

    @Transactional
    void enable(String idUser) throws UserException;

    @Transactional
    void disable(String idUser) throws UserException;

    @Transactional
    void delete(String idUser) throws UserException;

    @Transactional(readOnly = true)
    UserResponse getById(String idUser) throws UserException;

    @Transactional(readOnly = true)
    List<UserResponse> getAll(String value) throws UserException;

    @Transactional(readOnly = true)
    List<UserResponse> getForEnable() throws UserException;

    @Transactional(readOnly = true)
    List<UserResponse> getForDisable() throws UserException;
}