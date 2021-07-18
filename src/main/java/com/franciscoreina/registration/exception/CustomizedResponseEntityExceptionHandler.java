package com.franciscoreina.registration.exception;

import com.franciscoreina.registration.dto.ResponseDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

import static com.franciscoreina.registration.constants.Messages.ERROR_INVALID_DATE_OF_BIRTH;
import static com.franciscoreina.registration.constants.Messages.ERROR_INVALID_PASSWORD;
import static com.franciscoreina.registration.constants.Messages.ERROR_INVALID_USERNAME;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomizedResponseEntityExceptionHandler.class);

    @Getter
    @NoArgsConstructor
    static class ErrorResult {
        private final List<ResponseDTO> fieldErrors = new ArrayList<>();
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ErrorResult handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        final ErrorResult errorResult = new ErrorResult();

        for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
            ResponseDTO responseDTO;
            switch (fieldError.getField()) {
                case "username":
                    LOGGER.error("MethodArgumentNotValidException, Field {}, Message: {}",
                            fieldError.getField(), fieldError.getDefaultMessage());
                    responseDTO = ResponseDTO.builder().description(ERROR_INVALID_USERNAME).build();
                    break;
                case "password":
                    LOGGER.error("MethodArgumentNotValidException, Field {}, Message: {}",
                            fieldError.getField(), fieldError.getDefaultMessage());
                    responseDTO = ResponseDTO.builder().description(ERROR_INVALID_PASSWORD).build();
                    break;
                case "dateOfBirth":
                    LOGGER.error("MethodArgumentNotValidException, Field {}, Message: {}",
                            fieldError.getField(), fieldError.getDefaultMessage());
                    responseDTO = ResponseDTO.builder().description(ERROR_INVALID_DATE_OF_BIRTH).build();
                    break;
                default:
                    LOGGER.error("MethodArgumentNotValidException, Field {}, Message: {}",
                            fieldError.getField(), fieldError.getDefaultMessage());
                    responseDTO = ResponseDTO.builder().description(fieldError.getDefaultMessage()).build();
            }
            errorResult.getFieldErrors().add(responseDTO);
        }

        return errorResult;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(UnsupportedOperationException.class)
    public final ResponseDTO handleNotFoundException(UnsupportedOperationException exception) {
        LOGGER.error("UnsupportedOperationException, Message: {}", exception.getMessage());
        return ResponseDTO.builder().description(exception.getMessage()).build();
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DuplicateUserException.class)
    public final ResponseDTO handleDuplicateUserException(DuplicateUserException exception) {
        LOGGER.error("DuplicateUserException, Message: {}", exception.getMessage());
        return ResponseDTO.builder().description(exception.getMessage()).build();
    }

}
