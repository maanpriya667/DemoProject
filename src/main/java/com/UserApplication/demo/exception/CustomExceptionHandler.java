package com.UserApplication.demo.exception;

import com.UserApplication.demo.model.response.ResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class CustomExceptionHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleAllException(Exception ex, WebRequest webRequest) {
        String errorMessage = ex.getLocalizedMessage();
        if (errorMessage == null) {
            errorMessage = ex.toString();
        }
        return new ResponseEntity<Object>(new ResponseModel<>(HttpStatus.BAD_REQUEST, errorMessage, "An error occured"
                , null), HttpStatus.BAD_REQUEST);
    }


}

