package com.challenge.midas.service.impl;

import com.challenge.midas.dto.request.User.UserRequest;
import com.challenge.midas.dto.request.User.UserRequestModify;
import com.challenge.midas.dto.request.User.UserRequestModifyPassword;
import com.challenge.midas.dto.response.UserResponse;
import com.challenge.midas.enums.EExceptionMessage;
import com.challenge.midas.exception.EmailAlreadyExistException;
import com.challenge.midas.exception.UserException;
import com.challenge.midas.mapper.UserMapper;
import com.challenge.midas.model.User;
import com.challenge.midas.repository.UserRepository;
import com.challenge.midas.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    public UserServiceImpl(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public UserResponse create(UserRequest request) throws UserException, EmailAlreadyExistException {
        User userForConvert = mapper.convertToEntity(new User(), request);
        User userForSave = repository.save(userForConvert);
        return mapper.convertToResponse(userForSave);
    }

    @Override
    public UserResponse modify(String idUser, UserRequestModify request) throws UserException, EmailAlreadyExistException {
        Optional<User> optionalUser = repository.findById(idUser);
        if (optionalUser.isPresent()) {
            User userForConvert = mapper.convertToEntityModify(optionalUser.get(), request);
            User userForSave = repository.save(userForConvert);
            return mapper.convertToResponse(userForSave);
        } else {
            throw new UserException(EExceptionMessage.USER_NOT_FOUND.toString());
        }
    }

    @Override
    public void modifyPassword(String idUser, UserRequestModifyPassword request) throws UserException {
        Optional<User> optionalUser = repository.findById(idUser);
        if (optionalUser.isPresent()) {
            User userForConvert = mapper.convertToEntityModifyPassword(optionalUser.get(), request);
            repository.save(userForConvert);
        }
    }

    @Override
    public void enable(String idUser) throws UserException {
        Optional<User> optionalUser = repository.findById(idUser);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.isDeleted()) {
                user.setDeleted(true);
                user.setModificationDate(new Date());
                repository.save(user);
            } else {
                throw new UserException(EExceptionMessage.THE_USER_COULD_NOT_BE_ENABLED.toString());
            }
        } else {
            throw new UserException(EExceptionMessage.USER_NOT_FOUND.toString());
        }
    }

    @Override
    public void disable(String idUser) throws UserException {
        Optional<User> optionalUser = repository.findById(idUser);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.isDeleted()) {
                user.setDeleted(false);
                user.setModificationDate(new Date());
                repository.save(user);
            } else {
                throw new UserException(EExceptionMessage.THE_USER_COULD_NOT_BE_DISABLE.toString());
            }
        } else {
            throw new UserException(EExceptionMessage.USER_NOT_FOUND.toString());
        }
    }

    @Override
    public void delete(String idUser) throws UserException {
        User user = repository.findById(idUser).orElseThrow(() -> new UserException(EExceptionMessage.USER_NOT_FOUND.toString()));
        repository.delete(user);
    }

    @Override
    public UserResponse getById(String idUser) throws UserException {
        return repository.findById(idUser)
                .map(mapper::convertToResponse)
                .orElseThrow(() -> new UserException(EExceptionMessage.USER_NOT_FOUND.toString()));
    }

    @Override
    public List<UserResponse> getAll(String value) throws UserException {
        if (value == null) {
            value = "";
        }
        List<User> userList = repository.getByValue("%" + value + "%");
        if (userList.isEmpty()) {
            throw new UserException(EExceptionMessage.THE_USER_LIST_IS_EMPTY.toString());
        }
        return mapper.convertToResponseList(userList);
    }

    @Override
    public List<UserResponse> getForEnable() throws UserException {
        List<User> userList = repository.getByEnable();
        if (userList.isEmpty()) {
            throw new UserException(EExceptionMessage.THE_USER_LIST_IS_EMPTY.toString());
        }
        return mapper.convertToResponseList(userList);
    }

    @Override
    public List<UserResponse> getForDisable() throws UserException {
        List<User> userList = repository.getByDisable();
        if (userList.isEmpty()) {
            throw new UserException(EExceptionMessage.THE_USER_LIST_IS_EMPTY.toString());
        }
        return mapper.convertToResponseList(userList);
    }
}