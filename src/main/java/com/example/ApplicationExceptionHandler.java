package com.example;


import com.example.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.lang.reflect.Field;

@ControllerAdvice
public class ApplicationExceptionHandler {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResourceNotFoundResponse> handleResourceNotFoundException(ResourceNotFoundException ex){
           ResourceNotFoundResponse errorResponse = new ResourceNotFoundResponse(ex.getMessage());
           return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SaveFailureException.class)
    public ResponseEntity<SaveFailureResponse> handleMessageNotSavedResponse(
            SaveFailureException ex
    ){
        SaveFailureResponse<?> response = new SaveFailureResponse<>(ex.getMetaData(),ex.getMessage());
        return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<UnknownExceptionResponse> handleUnknownException(
            Exception ex
    ){
        UnknownExceptionResponse errorResponse = new UnknownExceptionResponse("Unhandled Exception",ex.getMessage());
        return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
