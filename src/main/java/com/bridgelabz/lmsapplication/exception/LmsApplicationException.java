package com.bridgelabz.lmsapplication.exception;

import com.bridgelabz.lmsapplication.dto.ErrorMessageDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

public class LmsApplicationException extends ResponseEntityExceptionHandler {
    //GLOBAL EXCEPTION HANDLER
    @ExceptionHandler(UserException.class)
    public final ResponseEntity<ErrorMessageDto> handleAnyException(Exception ex, WebRequest request) {
        ErrorMessageDto errorMessageDto = new ErrorMessageDto(new Date(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorMessageDto, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //METHOD ARGUMENT NOT VALID EXCEPTION
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorMessageDto errorMessageDto = new ErrorMessageDto(new Date(), "From method argument not valid exception",
                request.getDescription(false));
        return new ResponseEntity<>(errorMessageDto, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
