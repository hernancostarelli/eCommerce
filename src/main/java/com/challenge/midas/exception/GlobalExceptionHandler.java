package com.challenge.midas.exception;
/*
import com.example.model.enums.EExceptionMessage;
import com.example.model.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static ErrorResponse buildErrorResponse(HttpStatus httpStatus, String typeException, String message, Exception exception) {
        return new ErrorResponse(httpStatus.value(), typeException, message, exception.getMessage());
    }

    private static ErrorResponse buildErrorResponse(HttpStatus httpStatus, String typeException, String message, List<String> moreInfo) {
        return new ErrorResponse(httpStatus.value(), typeException, message, moreInfo);
    }

    @ExceptionHandler(value = DocumentAlreadyExistException.class)
    protected ResponseEntity<ErrorResponse> handlerDocumentAlreadyExistException(DocumentAlreadyExistException exception) {
        com.example.model.response.ErrorResponse errorResponse = null;
        HttpStatus status = null;
        String typeException = "DOCUMENT EXCEPTION";
        String message = "DOCUMENT ALREADY EXIST EXCEPTION";

        errorResponse = buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, typeException,
                message,
                exception);
        status = HttpStatus.INTERNAL_SERVER_ERROR;

        assert status != null;
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(value = EmailAlreadyExistException.class)
    protected ResponseEntity<ErrorResponse> handlerEmailAlreadyExistException(EmailAlreadyExistException exception) {
        com.example.model.response.ErrorResponse errorResponse = null;
        HttpStatus status = null;
        String typeException = "EMAIL EXCEPTION";
        String message = "EMAIL ALREADY EXIST EXCEPTION";

        errorResponse = buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, typeException,
                message,
                exception);
        status = HttpStatus.INTERNAL_SERVER_ERROR;

        assert status != null;
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(value = WorkshopException.class)
    protected ResponseEntity<ErrorResponse> handlerWorkshopException(WorkshopException exception) {

        ErrorResponse errorResponse = null;
        HttpStatus status = null;
        String typeException = "WORKSHOP EXCEPTION";

        if (exception.getMessage().equals(EExceptionMessage.WORKSHOP_NOT_FOUND.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.NOT_FOUND, typeException,
                    EExceptionMessage.WORKSHOP_NOT_FOUND.toString(),
                    exception);
            status = HttpStatus.NOT_FOUND;
        }
        if (exception.getMessage().equals(EExceptionMessage.REQUEST_WRONG_DATA.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.BAD_REQUEST, typeException,
                    EExceptionMessage.REQUEST_WRONG_DATA.toString(),
                    exception);
            status = HttpStatus.BAD_REQUEST;
        }
        if (exception.getMessage().equals(EExceptionMessage.ERROR_WHEN_DISPLAYING_ACTIVE_WORKSHOPS.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, typeException,
                    EExceptionMessage.ERROR_WHEN_DISPLAYING_ACTIVE_WORKSHOPS.toString(),
                    exception);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        if (exception.getMessage().equals(EExceptionMessage.ERROR_WHEN_DISPLAYING_INACTIVE_WORKSHOPS.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, typeException,
                    EExceptionMessage.ERROR_WHEN_DISPLAYING_INACTIVE_WORKSHOPS.toString(),
                    exception);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        if (exception.getMessage().equals(EExceptionMessage.ERROR_WHEN_DISPLAYING_THE_LIST_OF_AVAILABLE_WORKSHOPS.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, typeException,
                    EExceptionMessage.ERROR_WHEN_DISPLAYING_THE_LIST_OF_AVAILABLE_WORKSHOPS.toString(),
                    exception);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        if (exception.getMessage().equals(EExceptionMessage.UNASSIGNED_WORKSHOP.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, typeException,
                    EExceptionMessage.UNASSIGNED_WORKSHOP.toString(),
                    exception);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        if (exception.getMessage().equals(EExceptionMessage.THE_WORKSHOP_ALREADY_CONTAINS_AN_IMAGE.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.EXPECTATION_FAILED, typeException,
                    EExceptionMessage.THE_WORKSHOP_ALREADY_CONTAINS_AN_IMAGE.toString(),
                    exception);
            status = HttpStatus.EXPECTATION_FAILED;
        }
        if (exception.getMessage().equals(EExceptionMessage.THE_STUDENT_IS_ALREADY_REGISTERED_TO_THE_WORKSHOP.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.EXPECTATION_FAILED, typeException,
                    EExceptionMessage.THE_STUDENT_IS_ALREADY_REGISTERED_TO_THE_WORKSHOP.toString(),
                    exception);
            status = HttpStatus.EXPECTATION_FAILED;
        }
        if (exception.getMessage().equals(EExceptionMessage.THE_WORKSHOP_LIST_COULD_NOT_BE_REMOVED.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, typeException,
                    EExceptionMessage.THE_WORKSHOP_LIST_COULD_NOT_BE_REMOVED.toString(),
                    exception);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        assert status != null;
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(value = StudentException.class)
    protected ResponseEntity<ErrorResponse> handlerStudentException(StudentException exception) {

        ErrorResponse errorResponse = null;
        HttpStatus status = null;
        String typeException = "STUDENT EXCEPTION";

        if (exception.getMessage().equals(EExceptionMessage.STUDENT_NOT_FOUND.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.NOT_FOUND, typeException,
                    EExceptionMessage.STUDENT_NOT_FOUND.toString(),
                    exception);
            status = HttpStatus.NOT_FOUND;
        }
        if (exception.getMessage().equals(EExceptionMessage.REQUEST_WRONG_DATA.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.BAD_REQUEST, typeException,
                    EExceptionMessage.REQUEST_WRONG_DATA.toString(),
                    exception);
            status = HttpStatus.BAD_REQUEST;
        }
        if (exception.getMessage().equals(EExceptionMessage.ERROR_WHEN_DISPLAYING_ACTIVE_STUDENTS.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, typeException,
                    EExceptionMessage.ERROR_WHEN_DISPLAYING_ACTIVE_STUDENTS.toString(),
                    exception);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        if (exception.getMessage().equals(EExceptionMessage.ERROR_WHEN_DISPLAYING_INACTIVE_STUDENTS.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, typeException,
                    EExceptionMessage.ERROR_WHEN_DISPLAYING_INACTIVE_STUDENTS.toString(),
                    exception);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        if (exception.getMessage().equals(EExceptionMessage.ERROR_WHEN_DISPLAYING_THE_LIST_OF_AVAILABLE_STUDENTS.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, typeException,
                    EExceptionMessage.ERROR_WHEN_DISPLAYING_THE_LIST_OF_AVAILABLE_STUDENTS.toString(),
                    exception);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        if (exception.getMessage().equals(EExceptionMessage.THE_STUDENT_ALREADY_EXISTS_IN_THAT_WORKSHOP.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, typeException,
                    EExceptionMessage.THE_STUDENT_ALREADY_EXISTS_IN_THAT_WORKSHOP.toString(),
                    exception);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        if (exception.getMessage().equals(EExceptionMessage.THE_STUDENT_IS_ON_SCHOLARSHIP_DOES_NOT_PAY_FEES.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, typeException,
                    EExceptionMessage.THE_STUDENT_IS_ON_SCHOLARSHIP_DOES_NOT_PAY_FEES.toString(),
                    exception);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        if (exception.getMessage().equals(EExceptionMessage.STUDENT_NOT_ASSIGNED_TO_THE_WORKSHOP.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, typeException,
                    EExceptionMessage.STUDENT_NOT_ASSIGNED_TO_THE_WORKSHOP.toString(),
                    exception);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        if (exception.getMessage().equals(EExceptionMessage.STUDENT_NOT_REGISTERED_FOR_THE_WORKSHOP.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, typeException,
                    EExceptionMessage.STUDENT_NOT_REGISTERED_FOR_THE_WORKSHOP.toString(),
                    exception);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        if (exception.getMessage().equals(EExceptionMessage.THE_STUDENT_COULD_NOT_BE_ENABLED.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, typeException,
                    EExceptionMessage.THE_STUDENT_COULD_NOT_BE_ENABLED.toString(),
                    exception);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        if (exception.getMessage().equals(EExceptionMessage.THE_STUDENT_COULD_NOT_BE_DISABLED.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, typeException,
                    EExceptionMessage.THE_STUDENT_COULD_NOT_BE_DISABLED.toString(),
                    exception);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        assert status != null;
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(value = DailyFeeException.class)
    protected ResponseEntity<ErrorResponse> handlerDailyFeeException(DailyFeeException exception) {

        ErrorResponse errorResponse = null;
        HttpStatus status = null;
        String typeException = "DAILY FEE EXCEPTION";

        if (exception.getMessage().equals(EExceptionMessage.DAILY_FEE_NOT_FOUND.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.NOT_FOUND, typeException,
                    EExceptionMessage.DAILY_FEE_NOT_FOUND.toString(),
                    exception);
            status = HttpStatus.NOT_FOUND;
        }
        if (exception.getMessage().equals(EExceptionMessage.REQUEST_WRONG_DATA.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.BAD_REQUEST, typeException,
                    EExceptionMessage.REQUEST_WRONG_DATA.toString(),
                    exception);
            status = HttpStatus.BAD_REQUEST;
        }
        if (exception.getMessage().equals(EExceptionMessage.ERROR_WHEN_DISPLAYING_THE_LIST_OF_AVAILABLE_DAILY_FEES.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.BAD_REQUEST, typeException,
                    EExceptionMessage.ERROR_WHEN_DISPLAYING_THE_LIST_OF_AVAILABLE_DAILY_FEES.toString(),
                    exception);
            status = HttpStatus.BAD_REQUEST;
        }
        if (exception.getMessage().equals(EExceptionMessage.THE_DAILY_FEE_LIST_COULD_NOT_BE_REMOVED.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, typeException,
                    EExceptionMessage.THE_DAILY_FEE_LIST_COULD_NOT_BE_REMOVED.toString(),
                    exception);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        if (exception.getMessage().equals(EExceptionMessage.THE_DAILY_FEE_LIST_COULD_NOT_BE_RESTARTED.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, typeException,
                    EExceptionMessage.THE_DAILY_FEE_LIST_COULD_NOT_BE_RESTARTED.toString(),
                    exception);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        assert status != null;
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(value = MonthlyFeeException.class)
    protected ResponseEntity<ErrorResponse> handlerMonthlyFeeException(MonthlyFeeException exception) {

        ErrorResponse errorResponse = null;
        HttpStatus status = null;
        String typeException = "MONTHLY FEE EXCEPTION";

        if (exception.getMessage().equals(EExceptionMessage.MONTHLY_FEE_NOT_FOUND.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.NOT_FOUND, typeException,
                    EExceptionMessage.MONTHLY_FEE_NOT_FOUND.toString(),
                    exception);
            status = HttpStatus.NOT_FOUND;
        }
        if (exception.getMessage().equals(EExceptionMessage.REQUEST_WRONG_DATA.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.BAD_REQUEST, typeException,
                    EExceptionMessage.REQUEST_WRONG_DATA.toString(),
                    exception);
            status = HttpStatus.BAD_REQUEST;
        }
        if (exception.getMessage().equals(EExceptionMessage.ERROR_WHEN_DISPLAYING_THE_LIST_OF_AVAILABLE_MONTHLY_FEES.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.BAD_REQUEST, typeException,
                    EExceptionMessage.ERROR_WHEN_DISPLAYING_THE_LIST_OF_AVAILABLE_MONTHLY_FEES.toString(),
                    exception);
            status = HttpStatus.BAD_REQUEST;
        }
        if (exception.getMessage().equals(EExceptionMessage.THE_MONTHLY_FEE_LIST_COULD_NOT_BE_REMOVED.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, typeException,
                    EExceptionMessage.THE_MONTHLY_FEE_LIST_COULD_NOT_BE_REMOVED.toString(),
                    exception);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        if (exception.getMessage().equals(EExceptionMessage.THE_MONTHLY_FEE_LIST_COULD_NOT_BE_RESTARTED.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, typeException,
                    EExceptionMessage.THE_MONTHLY_FEE_LIST_COULD_NOT_BE_RESTARTED.toString(),
                    exception);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        assert status != null;
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(value = InternalException.class)
    protected ResponseEntity<ErrorResponse> handlerInternalException(InternalException exception) {

        ErrorResponse errorResponse = null;
        HttpStatus status = null;
        String typeException = "INTERNAL EXCEPTION";

        if (exception.getMessage().equals(EExceptionMessage.INTERNAL_NOT_FOUND.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.NOT_FOUND, typeException,
                    EExceptionMessage.INTERNAL_NOT_FOUND.toString(),
                    exception);
            status = HttpStatus.NOT_FOUND;
        }
        if (exception.getMessage().equals(EExceptionMessage.REQUEST_WRONG_DATA.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.BAD_REQUEST, typeException,
                    EExceptionMessage.REQUEST_WRONG_DATA.toString(),
                    exception);
            status = HttpStatus.BAD_REQUEST;
        }
        if (exception.getMessage().equals(EExceptionMessage.ERROR_WHEN_DISPLAYING_ACTIVE_INTERNALS.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.BAD_REQUEST, typeException,
                    EExceptionMessage.ERROR_WHEN_DISPLAYING_ACTIVE_INTERNALS.toString(),
                    exception);
            status = HttpStatus.BAD_REQUEST;
        }
        if (exception.getMessage().equals(EExceptionMessage.ERROR_WHEN_DISPLAYING_INACTIVE_INTERNALS.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.BAD_REQUEST, typeException,
                    EExceptionMessage.ERROR_WHEN_DISPLAYING_INACTIVE_INTERNALS.toString(),
                    exception);
            status = HttpStatus.BAD_REQUEST;
        }
        if (exception.getMessage().equals(EExceptionMessage.ERROR_WHEN_DISPLAYING_THE_LIST_OF_AVAILABLE_INTERNALS.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.BAD_REQUEST, typeException,
                    EExceptionMessage.ERROR_WHEN_DISPLAYING_THE_LIST_OF_AVAILABLE_INTERNALS.toString(),
                    exception);
            status = HttpStatus.BAD_REQUEST;
        }
        if (exception.getMessage().equals(EExceptionMessage.PASSWORDS_DO_NOT_MATCH.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.BAD_REQUEST, typeException,
                    EExceptionMessage.PASSWORDS_DO_NOT_MATCH.toString(),
                    exception);
            status = HttpStatus.BAD_REQUEST;
        }
        if (exception.getMessage().equals(EExceptionMessage.WRONG_PASSWORD.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.BAD_REQUEST, typeException,
                    EExceptionMessage.WRONG_PASSWORD.toString(),
                    exception);
            status = HttpStatus.BAD_REQUEST;
        }
        if (exception.getMessage().equals(EExceptionMessage.THE_INTERNAL_ALREADY_EXISTS_IN_THAT_WORKSHOP.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, typeException,
                    EExceptionMessage.THE_INTERNAL_ALREADY_EXISTS_IN_THAT_WORKSHOP.toString(),
                    exception);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        if (exception.getMessage().equals(EExceptionMessage.INADEQUATE_ROLE_OF_THE_INTERNAL.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, typeException,
                    EExceptionMessage.INADEQUATE_ROLE_OF_THE_INTERNAL.toString(),
                    exception);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        if (exception.getMessage().equals(EExceptionMessage.THE_INTERNAL_COULD_NOT_BE_ENABLED.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, typeException,
                    EExceptionMessage.THE_INTERNAL_COULD_NOT_BE_ENABLED.toString(),
                    exception);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        if (exception.getMessage().equals(EExceptionMessage.THE_INTERNAL_COULD_NOT_BE_DISABLED.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, typeException,
                    EExceptionMessage.THE_INTERNAL_COULD_NOT_BE_DISABLED.toString(),
                    exception);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        assert status != null;
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(value = FileException.class)
    protected ResponseEntity<ErrorResponse> handlerFileException(FileException exception) {

        ErrorResponse errorResponse = null;
        HttpStatus status = null;
        String typeException = "FILE EXCEPTION";

        if (exception.getMessage().equals(EExceptionMessage.FILE_NOT_FOUND.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.NOT_FOUND, typeException,
                    EExceptionMessage.FILE_NOT_FOUND.toString(),
                    exception);
            status = HttpStatus.NOT_FOUND;
        }
        if (exception.getMessage().equals(EExceptionMessage.THE_FOLDER_CANNOT_BE_INITIALIZED.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, typeException,
                    EExceptionMessage.THE_FOLDER_CANNOT_BE_INITIALIZED.toString(),
                    exception);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        if (exception.getMessage().equals(EExceptionMessage.THE_FILE_CANNOT_BE_SAVED.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, typeException,
                    EExceptionMessage.THE_FILE_CANNOT_BE_SAVED.toString(),
                    exception);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        if (exception.getMessage().equals(EExceptionMessage.FILE_DELETED.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.ACCEPTED, typeException,
                    EExceptionMessage.FILE_DELETED.toString(),
                    exception);
            status = HttpStatus.ACCEPTED;
        }
        if (exception.getMessage().equals(EExceptionMessage.ERROR_DELETING_FILE.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, typeException,
                    EExceptionMessage.ERROR_DELETING_FILE.toString(),
                    exception);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        assert status != null;
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ErrorResponse> handlerMaxUploadSizeException(MaxUploadSizeExceededException exception) {

        String typeException = "MAX UPLOAD SIZE EXCEEDED EXCEPTION";

        ErrorResponse errorResponse = buildErrorResponse(HttpStatus.BAD_REQUEST, typeException,
                EExceptionMessage.REQUEST_WRONG_DATA.toString(), exception);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handlerNotValidController(MethodArgumentNotValidException exception) {

        String typeException = "NOT VALID EXCEPTION";

        ErrorResponse errorResponse = buildErrorResponse(HttpStatus.BAD_REQUEST, typeException,
                EExceptionMessage.REQUEST_WRONG_DATA.toString(),
                collectErrors(exception));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    private List<String> collectErrors(MethodArgumentNotValidException exception) {
        return exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(this::formatErrorField)
                .toList();
    }

    private String formatErrorField(FieldError fieldError) {
        return fieldError.getDefaultMessage();
    }
}*/
