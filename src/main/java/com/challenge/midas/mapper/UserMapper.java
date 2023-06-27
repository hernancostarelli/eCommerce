package com.challenge.midas.mapper;

import com.challenge.midas.dto.request.user.UserRequest;
import com.challenge.midas.dto.request.user.UserRequestModify;
import com.challenge.midas.dto.request.user.UserRequestModifyPassword;
import com.challenge.midas.dto.response.UserResponse;
import com.challenge.midas.enums.EExceptionMessage;
import com.challenge.midas.exception.EmailAlreadyExistException;
import com.challenge.midas.exception.UserException;
import com.challenge.midas.model.User;
import com.challenge.midas.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
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
        String requestEmail = request.getEmail();
        boolean isEmailChanged = !requestEmail.equals(user.getEmail());
        boolean isEmailAlreadyInUse = repository.existsByEmail(requestEmail);
        if (isEmailChanged && isEmailAlreadyInUse) {
            validateRequestModifyFull(request);
        } else {
            validateRequestModifyBasic(request);
        }
        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setEmail(requestEmail);
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
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String stringCreationDate = user.getCreationDate() != null ? sdf.format(user.getCreationDate()) : null;
        String stringModificationDate = user.getModificationDate() != null ? sdf.format(user.getModificationDate()) : null;
        response.setCreationDate(stringCreationDate);
        response.setModificationDate(stringModificationDate);
        response.setDeleted(String.valueOf(user.isDeleted()));
        return response;
    }

    public List<UserResponse> convertToResponseList(List<User> userList) {
        List<UserResponse> userResponseList = new ArrayList<>();
        for (User entity : userList) {
            userResponseList.add(this.convertToResponse(entity));
        }
        return userResponseList;
    }

    private void validateRequestCreate(UserRequest request) throws UserException, EmailAlreadyExistException {
        String name = request.getName();
        String surname = request.getSurname();
        String email = request.getEmail();
        String password = request.getPassword();
        String confirmPassword = request.getConfirmPassword();
        if (StringUtils.isBlank(name))
            throw new UserException(EExceptionMessage.THE_USER_NAME_CANNOT_BE_EMPTY_OR_BE_NULL.toString());
        if (StringUtils.isBlank(surname))
            throw new UserException(EExceptionMessage.THE_USER_SURNAME_CANNOT_BE_EMPTY_OR_BE_NULL.toString());
        if (StringUtils.isBlank(email))
            throw new UserException(EExceptionMessage.THE_USER_EMAIL_CANNOT_BE_EMPTY_OR_BE_NULL.toString());
        if (repository.existsByEmail(email))
            throw new EmailAlreadyExistException(EExceptionMessage.EMAIL_ALREADY_EXISTS.getMessage(email));
        if (StringUtils.isBlank(password))
            throw new UserException(EExceptionMessage.THE_USER_PASSWORD_CANNOT_BE_EMPTY_OR_BE_NULL.toString());
        if (StringUtils.isBlank(confirmPassword))
            throw new UserException(EExceptionMessage.THE_PASSWORD_CONFIRMATION_CANNOT_BE_EMPTY_OR_NULL.toString());
        if (!password.equals(confirmPassword))
            throw new UserException(EExceptionMessage.THE_ENTERED_PASSWORDS_DO_NOT_MATCH.toString());
    }

    private void validateRequestModifyBasic(UserRequestModify request) throws UserException {
        String name = request.getName();
        String surname = request.getSurname();
        String email = request.getEmail();
        if (StringUtils.isBlank(name))
            throw new UserException(EExceptionMessage.THE_USER_NAME_CANNOT_BE_EMPTY_OR_BE_NULL.toString());
        if (StringUtils.isBlank(surname))
            throw new UserException(EExceptionMessage.THE_USER_SURNAME_CANNOT_BE_EMPTY_OR_BE_NULL.toString());
        if (StringUtils.isBlank(email))
            throw new UserException(EExceptionMessage.THE_USER_EMAIL_CANNOT_BE_EMPTY_OR_BE_NULL.toString());
    }

    private void validateRequestModifyFull(UserRequestModify request) throws UserException, EmailAlreadyExistException {
        validateRequestModifyBasic(request);
        if (repository.existsByEmail(request.getEmail()))
            throw new EmailAlreadyExistException(EExceptionMessage.EMAIL_ALREADY_EXISTS.getMessage(request.getEmail()));
    }

    private void validateRequestModifyPassword(User user, UserRequestModifyPassword request) throws UserException {
        String oldPassword = request.getOldPassword();
        String newPassword = request.getNewPassword();
        String confirmPassword = request.getConfirmPassword();
        if (StringUtils.isBlank(oldPassword))
            throw new UserException(EExceptionMessage.THE_USER_PASSWORD_CANNOT_BE_EMPTY_OR_BE_NULL.toString());
        if (!passwordEncoder.matches(oldPassword, user.getPassword()))
            throw new UserException(EExceptionMessage.WRONG_PASSWORD.toString());
        if (StringUtils.isBlank(newPassword))
            throw new UserException(EExceptionMessage.THE_USER_PASSWORD_CANNOT_BE_EMPTY_OR_BE_NULL.toString());
        if (StringUtils.isBlank(confirmPassword))
            throw new UserException(EExceptionMessage.THE_PASSWORD_CONFIRMATION_CANNOT_BE_EMPTY_OR_NULL.toString());
        if (!newPassword.equals(confirmPassword))
            throw new UserException(EExceptionMessage.THE_ENTERED_PASSWORDS_DO_NOT_MATCH.toString());
    }
}