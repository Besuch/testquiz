package com.green.testquiz.web.controllers;

import com.green.testquiz.exceptions.InvalidOperationException;
import com.green.testquiz.exceptions.EntityNotFoundException;
import com.green.testquiz.exceptions.UnauthorizedException;
import com.green.testquiz.presentation.RestExceptionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class QuizControllerAdvice {
    @ExceptionHandler(value = UnauthorizedException.class)
    public ResponseEntity<String> handleUnauthorizedException(Exception e) {
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<RestExceptionDto> handleEntityNotFoundException(EntityNotFoundException e) {
        RestExceptionDto restExceptionDto = new RestExceptionDto(
                String.format("%s. Invalid field '%s' with value '%s'", e.getMessage(), e.getEntityName(), e.getEntityValue()));
        return new ResponseEntity<>(restExceptionDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = InvalidOperationException.class)
    public ResponseEntity<RestExceptionDto> handleInvalidOperationException(InvalidOperationException e) {
        RestExceptionDto restExceptionDto = new RestExceptionDto(e.getMessage());
        return new ResponseEntity<>(restExceptionDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<RestExceptionDto> handleException(Exception e) {
        long currentTime = System.currentTimeMillis();
        log.error(String.valueOf(currentTime), e);
        RestExceptionDto restExceptionDto = new RestExceptionDto(
                String.format("Oops! Something went wrong. Try later! Server exception time code = %d", currentTime)
        );
        return new ResponseEntity<>(restExceptionDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
