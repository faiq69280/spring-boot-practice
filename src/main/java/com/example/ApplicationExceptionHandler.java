package com.example;


import com.example.exceptions.MessageNotFoundException;
import com.example.exceptions.MessageNotFoundResponse;
import com.example.exceptions.MessageNotSavedException;
import com.example.exceptions.MessageNotSavedResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler {


    @ExceptionHandler(MessageNotFoundException.class)
    public ResponseEntity<MessageNotFoundResponse> handleMessageNotFoundException(MessageNotFoundException ex){
           MessageNotFoundResponse errorResponse = new MessageNotFoundResponse(ex.getMessage());
           return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MessageNotSavedException.class)
    public ResponseEntity<MessageNotSavedResponse> handleMessageNotSavedResponse(
            MessageNotSavedException ex
    ){
        MessageNotSavedResponse errorResponse = new MessageNotSavedResponse(ex.getMetaData(),ex.getMessage());
        return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
