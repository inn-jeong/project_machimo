package com.example.project_machimo.auction.handler;

import lombok.Data;
import org.springframework.web.bind.annotation.ExceptionHandler;


public class AuctionExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    private String test(){
        return "auctions/error";
    }




    @Data
    static class CustomResponse {
        private String message;
    }
}




