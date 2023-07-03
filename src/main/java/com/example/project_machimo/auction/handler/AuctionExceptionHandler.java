package com.example.project_machimo.auction.handler;

import com.example.project_machimo.auction.controller.AuctionController;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



@ControllerAdvice(basePackageClasses = {AuctionController.class})
public class AuctionExceptionHandler {

    @ExceptionHandler(value = {NullPointerException.class})
    public String error(){
        return "auctions/error";
    }





}




