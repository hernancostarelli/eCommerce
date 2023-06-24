package com.challenge.midas.mapper;

import com.challenge.midas.dto.request.User.UserRequest;
import com.challenge.midas.dto.request.User.UserRequestModify;
import com.challenge.midas.dto.request.User.UserRequestModifyPassword;
import com.challenge.midas.dto.response.UserResponse;
import com.challenge.midas.enums.EExceptionMessage;
import com.challenge.midas.exception.EmailAlreadyExistException;
import com.challenge.midas.exception.UserException;
import com.challenge.midas.model.User;
import com.challenge.midas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class UserMapper {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;

    @Autowired
    public UserMapper(@Lazy PasswordEncoder passwordEncoder, @Lazy UserRepository repository) {
        this.passwordEncoder = passwordEncoder;
        this.repository = repository;
    }

    public User convertToEntity(User user, UserRequest request) throws UserException, EmailAlreadyExistException {
        validateRequestCreate(request);
        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return user;
    }

    public User convertToEntityModify(User user, UserRequestModify request) throws UserException, EmailAlreadyExistException {
        validateRequestModify(request);
        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setEmail(request.getEmail());
        user.setModificationDate(new Date());
        return user;
    }

    public User convertToEntityModifyPassword(User user, UserRequestModifyPassword request) throws UserException {
        validateRequestModifyPassword(user, request);
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        user.setModificationDate(new Date());
        return user;
    }

    public UserResponse convertToResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setSurname(user.getSurname());
        response.setFullName(user.getFullName());
        response.setEmail(user.getEmail());
        response.setRole(String.valueOf(user.getRole()));
        response.setCreationDate(String.valueOf(user.getCreationDate()));
        response.setModificationDate(String.valueOf(user.getModificationDate()));
        response.setDeleted(String.valueOf(user.isDeleted()));
        return response;
    }

    public List<UserResponse> convertToResponseList(List<User> list) {
        List<UserResponse> userList = new ArrayList<>();
        for (User entity : list) {
            userList.add(this.convertToResponse(entity));
        }
        return userList;
    }

    private void validateRequestCreate(UserRequest request) throws UserException, EmailAlreadyExistException {
        String name = request.getName();
        String surname = request.getSurname();
        String email = request.getEmail();
        String password = request.getPassword();
        String confirmPassword = request.getConfirmPassword();
        if (name == null || name.isEmpty())
            throw new UserException(EExceptionMessage.THE_USER_NAME_CANNOT_BE_EMPTY_OR_BE_NULL.toString());
        if (surname == null || surname.isEmpty())
            throw new UserException(EExceptionMessage.THE_USER_SURNAME_CANNOT_BE_EMPTY_OR_BE_NULL.toString());
        if (email == null || email.isEmpty())
            throw new UserException(EExceptionMessage.THE_USER_EMAIL_CANNOT_BE_EMPTY_OR_BE_NULL.toString());
        if (repository.existsByEmail(email))
            throw new EmailAlreadyExistException(EExceptionMessage.EMAIL_ALREADY_EXISTS.getMessage(email));
        if (password == null || password.isEmpty() || confirmPassword == null || confirmPassword.isEmpty()
                || !password.equals(confirmPassword))
            throw new UserException(EExceptionMessage.THE_USER_PASSWORD_CANNOT_BE_EMPTY_OR_BE_NULL.toString());
    }

    private void validateRequestModify(UserRequestModify request) throws UserException, EmailAlreadyExistException {
        String name = request.getName();
        String surname = request.getSurname();
        String email = request.getEmail();
        if (name == null || name.isEmpty())
            throw new UserException(EExceptionMessage.THE_USER_NAME_CANNOT_BE_EMPTY_OR_BE_NULL.toString());
        if (surname == null || surname.isEmpty())
            throw new UserException(EExceptionMessage.THE_USER_SURNAME_CANNOT_BE_EMPTY_OR_BE_NULL.toString());
        if (email == null || email.isEmpty())
            throw new UserException(EExceptionMessage.THE_USER_EMAIL_CANNOT_BE_EMPTY_OR_BE_NULL.toString());
        if (repository.existsByEmail(email))
            throw new EmailAlreadyExistException(EExceptionMessage.EMAIL_ALREADY_EXISTS.getMessage(email));
    }

    private void validateRequestModifyPassword(User user, UserRequestModifyPassword request) throws UserException {
        String oldPassword = request.getOldPassword();
        String newPassword = request.getNewPassword();
        String confirmPassword = request.getConfirmPassword();
        if (oldPassword == null || oldPassword.isEmpty())
            throw new UserException(EExceptionMessage.THE_USER_PASSWORD_CANNOT_BE_EMPTY_OR_BE_NULL.toString());
        if (!passwordEncoder.matches(oldPassword, user.getPassword()))
            throw new UserException(EExceptionMessage.WRONG_PASSWORD.toString());
        if (newPassword == null || newPassword.isEmpty())
            throw new UserException(EExceptionMessage.THE_USER_PASSWORD_CANNOT_BE_EMPTY_OR_BE_NULL.toString());
        if (confirmPassword == null || confirmPassword.isEmpty())
            throw new UserException(EExceptionMessage.THE_PASSWORD_CONFIRMATION_CANNOT_BE_EMPTY_OR_VOID.toString());
        if (!newPassword.equals(confirmPassword))
            throw new UserException(EExceptionMessage.THE_ENTERED_PASSWORDS_DO_NOT_MATCH.toString());
    }
}