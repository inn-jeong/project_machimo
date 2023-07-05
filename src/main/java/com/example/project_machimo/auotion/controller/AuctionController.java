package com.example.project_machimo.auotion.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auoction")
public class AuctionController {

    @GetMapping("/product")
    public String showProduct(){


        return "auotionsTest";
    }
}
