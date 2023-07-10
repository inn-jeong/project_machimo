package com.example.project_machimo.gyuha.auction.handler;

import com.example.project_machimo.gyuha.auction.controller.AuctionController;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



@ControllerAdvice(basePackageClasses = {AuctionController.class})
public class AuctionExceptionHandler {

    @ExceptionHandler(value = {NullPointerException.class})
    public String error(){
        return "auctions/error";
    }





}




