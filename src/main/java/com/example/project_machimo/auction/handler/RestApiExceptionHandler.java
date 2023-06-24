package com.example.project_machimo.auction.handler;

import lombok.Data;
import org.springframework.web.bind.annotation.ControllerAdvice;


public class RestApiExceptionHandler {
//    @ExceptionHandler
//    public ResponseEntity<CustomResponse> validationException(MethodArgumentNotValidException exception){
//        CustomResponse response = new CustomResponse();
//     String errorMsg = exception.getBindingResult().
//
//
//    }


    @Data
    static class CustomResponse {
        private String message;
    }
}




