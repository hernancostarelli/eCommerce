package com.challenge.midas.exception;


import com.challenge.midas.dto.response.ErrorResponse;
import com.challenge.midas.enums.EExceptionMessage;
import lombok.Builder;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Builder
public class GlobalExceptionHandler {

    private static ErrorResponse buildErrorResponse(HttpStatus httpStatus, String typeCode, String typeException, String message, Exception exception) {
        return ErrorResponse.builder()
                .statusCode(httpStatus.value())
                .typeCode(typeCode)
                .typeException(typeException)
                .message(message)
                .moreInfo(Collections.singletonList(exception.getMessage()))
                .build();
    }

    private static ErrorResponse buildErrorResponse(HttpStatus httpStatus, String typeCode, String typeException, String message, List<String> moreInfo) {
        return ErrorResponse.builder()
                .statusCode(httpStatus.value())
                .typeCode(typeCode)
                .typeException(typeException)
                .message(message)
                .moreInfo(moreInfo)
                .build();
    }

    @ExceptionHandler(EmailAlreadyExistException.class)
    protected ResponseEntity<ErrorResponse> handlerEmailAlreadyExistException(EmailAlreadyExistException exception) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String typeException = "EMAIL EXCEPTION";
        String message = "EL EMAIL INGRESADO YA EXISTE";
        ErrorResponse errorResponse = buildErrorResponse(status, status.name(), typeException, message, exception);
        return ResponseEntity.status(status).body(errorResponse);
    }

    @ExceptionHandler(UserException.class)
    protected ResponseEntity<ErrorResponse> handlerUserException(UserException exception) {

        ErrorResponse errorResponse = null;
        HttpStatus status = null;
        String typeException = "USER EXCEPTION";

        if (exception.getMessage().equals(EExceptionMessage.USER_NOT_FOUND.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.name(), typeException,
                    EExceptionMessage.USER_NOT_FOUND.toString(),
                    exception);
            status = HttpStatus.NOT_FOUND;
        }
        if (exception.getMessage().equals(EExceptionMessage.THE_USER_NAME_CANNOT_BE_EMPTY_OR_BE_NULL.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.name(), typeException,
                    EExceptionMessage.THE_USER_NAME_CANNOT_BE_EMPTY_OR_BE_NULL.toString(),
                    exception);
            status = HttpStatus.BAD_REQUEST;
        }
        if (exception.getMessage().equals(EExceptionMessage.THE_USER_SURNAME_CANNOT_BE_EMPTY_OR_BE_NULL.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.name(), typeException,
                    EExceptionMessage.THE_USER_SURNAME_CANNOT_BE_EMPTY_OR_BE_NULL.toString(),
                    exception);
            status = HttpStatus.BAD_GATEWAY;
        }
        if (exception.getMessage().equals(EExceptionMessage.THE_USER_EMAIL_CANNOT_BE_EMPTY_OR_BE_NULL.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.name(), typeException,
                    EExceptionMessage.THE_USER_EMAIL_CANNOT_BE_EMPTY_OR_BE_NULL.toString(),
                    exception);
            status = HttpStatus.BAD_REQUEST;
        }
        if (exception.getMessage().equals(EExceptionMessage.THE_USER_PASSWORD_CANNOT_BE_EMPTY_OR_BE_NULL.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.name(), typeException,
                    EExceptionMessage.THE_USER_PASSWORD_CANNOT_BE_EMPTY_OR_BE_NULL.toString(),
                    exception);
            status = HttpStatus.BAD_REQUEST;
        }
        if (exception.getMessage().equals(EExceptionMessage.THE_PASSWORD_CONFIRMATION_CANNOT_BE_EMPTY_OR_NULL.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.name(), typeException,
                    EExceptionMessage.THE_PASSWORD_CONFIRMATION_CANNOT_BE_EMPTY_OR_NULL.toString(),
                    exception);
            status = HttpStatus.BAD_REQUEST;
        }
        if (exception.getMessage().equals(EExceptionMessage.THE_ENTERED_PASSWORDS_DO_NOT_MATCH.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.name(), typeException,
                    EExceptionMessage.THE_ENTERED_PASSWORDS_DO_NOT_MATCH.toString(),
                    exception);
            status = HttpStatus.BAD_REQUEST;
        }
        if (exception.getMessage().equals(EExceptionMessage.WRONG_PASSWORD.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.UNAUTHORIZED, HttpStatus.UNAUTHORIZED.name(), typeException,
                    EExceptionMessage.WRONG_PASSWORD.toString(),
                    exception);
            status = HttpStatus.UNAUTHORIZED;
        }
        if (exception.getMessage().equals(EExceptionMessage.THE_USER_COULD_NOT_BE_ENABLED.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.name(), typeException,
                    EExceptionMessage.THE_USER_COULD_NOT_BE_ENABLED.toString(),
                    exception);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        if (exception.getMessage().equals(EExceptionMessage.THE_USER_COULD_NOT_BE_DISABLE.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.name(), typeException,
                    EExceptionMessage.THE_USER_COULD_NOT_BE_DISABLE.toString(),
                    exception);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        if (exception.getMessage().equals(EExceptionMessage.THE_USER_LIST_IS_EMPTY.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.NO_CONTENT, HttpStatus.NO_CONTENT.name(), typeException,
                    EExceptionMessage.THE_USER_LIST_IS_EMPTY.toString(),
                    exception);
            status = HttpStatus.NO_CONTENT;
        }

        assert status != null;
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(ProductException.class)
    protected ResponseEntity<ErrorResponse> handlerProductException(ProductException exception) {

        ErrorResponse errorResponse = null;
        HttpStatus status = null;
        String typeException = "PRODUCT EXCEPTION";

        if (exception.getMessage().equals(EExceptionMessage.PRODUCT_NOT_FOUND.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.name(), typeException,
                    EExceptionMessage.PRODUCT_NOT_FOUND.toString(),
                    exception);
            status = HttpStatus.NOT_FOUND;
        }
        if (exception.getMessage().equals(EExceptionMessage.THE_PRODUCT_NAME_CANNOT_BE_EMPTY_OR_BE_NULL.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.name(), typeException,
                    EExceptionMessage.THE_PRODUCT_NAME_CANNOT_BE_EMPTY_OR_BE_NULL.toString(),
                    exception);
            status = HttpStatus.BAD_REQUEST;
        }
        if (exception.getMessage().equals(EExceptionMessage.THE_PRODUCT_DESCRIPTION_CANNOT_BE_EMPTY_OR_BE_NULL.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.name(), typeException,
                    EExceptionMessage.THE_PRODUCT_DESCRIPTION_CANNOT_BE_EMPTY_OR_BE_NULL.toString(),
                    exception);
            status = HttpStatus.BAD_REQUEST;
        }
        if (exception.getMessage().equals(EExceptionMessage.THE_PRODUCT_PRICE_CANNOT_BE_EMPTY_OR_BE_NULL.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.name(), typeException,
                    EExceptionMessage.THE_PRODUCT_PRICE_CANNOT_BE_EMPTY_OR_BE_NULL.toString(),
                    exception);
            status = HttpStatus.BAD_REQUEST;
        }
        if (exception.getMessage().equals(EExceptionMessage.THE_PRODUCT_PRICE_MUST_BE_POSITIVE.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.name(), typeException,
                    EExceptionMessage.THE_PRODUCT_PRICE_MUST_BE_POSITIVE.toString(),
                    exception);
            status = HttpStatus.BAD_REQUEST;
        }
        if (exception.getMessage().equals(EExceptionMessage.THE_PRODUCT_QUANTITY_CANNOT_BE_EMPTY_OR_BE_NULL.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.name(), typeException,
                    EExceptionMessage.THE_PRODUCT_QUANTITY_CANNOT_BE_EMPTY_OR_BE_NULL.toString(),
                    exception);
            status = HttpStatus.BAD_REQUEST;
        }
        if (exception.getMessage().equals(EExceptionMessage.THE_PRODUCT_QUANTITY_MUST_BE_POSITIVE.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.BAD_REQUEST, HttpStatus.UNAUTHORIZED.name(), typeException,
                    EExceptionMessage.THE_PRODUCT_QUANTITY_MUST_BE_POSITIVE.toString(),
                    exception);
            status = HttpStatus.BAD_REQUEST;
        }
        if (exception.getMessage().equals(EExceptionMessage.THE_PRODUCT_COULD_NOT_BE_ENABLED.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.name(), typeException,
                    EExceptionMessage.THE_PRODUCT_COULD_NOT_BE_ENABLED.toString(),
                    exception);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        if (exception.getMessage().equals(EExceptionMessage.THE_PRODUCT_COULD_NOT_BE_DISABLE.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.name(), typeException,
                    EExceptionMessage.THE_PRODUCT_COULD_NOT_BE_DISABLE.toString(),
                    exception);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        if (exception.getMessage().equals(EExceptionMessage.THE_PRODUCT_LIST_IS_EMPTY.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.NO_CONTENT, HttpStatus.NO_CONTENT.name(), typeException,
                    EExceptionMessage.THE_PRODUCT_LIST_IS_EMPTY.toString(),
                    exception);
            status = HttpStatus.NO_CONTENT;
        }

        assert status != null;
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ErrorResponse> handlerMaxUploadSizeException(MaxUploadSizeExceededException exception) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String typeException = "MAX UPLOAD SIZE EXCEEDED EXCEPTION";
        ErrorResponse errorResponse = buildErrorResponse(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.name(), typeException, EExceptionMessage.REQUEST_WRONG_DATA.toString(), exception);
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String typeException = "REQUEST EXCEPTION";
        List<String> errors = collectErrors(exception);
        ErrorResponse errorResponse = buildErrorResponse(status, status.name(), typeException, EExceptionMessage.REQUEST_WRONG_DATA.toString(), errors);
        return new ResponseEntity<>(errorResponse, status);
    }

    private List<String> collectErrors(MethodArgumentNotValidException exception) {
        return exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());
    }
}