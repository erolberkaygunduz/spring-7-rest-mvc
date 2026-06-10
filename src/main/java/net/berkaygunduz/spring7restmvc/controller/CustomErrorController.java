package net.berkaygunduz.spring7restmvc.controller;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomErrorController {

    @ExceptionHandler
    ResponseEntity handleJPAViolations(TransactionSystemException exception){

        ResponseEntity.BodyBuilder responseEntity = ResponseEntity.badRequest();
        if(exception.getCause().getCause() instanceof ConstraintViolationException){
            ConstraintViolationException violationException = (ConstraintViolationException) exception.getCause().getCause();

            List error = violationException.getConstraintViolations().stream()
                    .map(constraintViolation -> {
                        Map<String,String> errMap = new HashMap<>();
                        errMap.put(constraintViolation.getPropertyPath().toString(),constraintViolation.getMessage());
                        return errMap;
                    }).collect(Collectors.toList());
            return responseEntity.body(error);
        }
        return responseEntity.build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity handleBindErrors(MethodArgumentNotValidException exception){
        List errorList = exception.getFieldErrors().stream()
                .map(fieldError -> {
                    Map<String,String> errorMap = new HashMap<>();
                    errorMap.put(fieldError.getField(),fieldError.getDefaultMessage());
                    return errorMap;
                }).collect(Collectors.toList());
        return ResponseEntity.badRequest().body(errorList);

    }
}
