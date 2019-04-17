package com.gl.demo_producer.exception;

import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Optional;

@ControllerAdvice
public class UserExceptionController  {
    @ExceptionHandler(UserException.class) public ResponseEntity <VndErrors> badRequestException(final UserException e) {
        return error(e, HttpStatus.BAD_REQUEST, e.getMessage().toString());
    }
    private ResponseEntity < VndErrors > error(final Exception exception, final HttpStatus httpStatus, final String logRef) {
        final String message = Optional.of(exception.getMessage()).orElse(exception.getClass().getSimpleName());
        return new ResponseEntity < > (new VndErrors(logRef, message), httpStatus);
    }
    @ExceptionHandler(IllegalArgumentException.class) public ResponseEntity < VndErrors > assertionException(final IllegalArgumentException e) {
        return error(e, HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
    }

}
