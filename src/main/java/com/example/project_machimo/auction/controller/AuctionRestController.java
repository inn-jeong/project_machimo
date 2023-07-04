package com.example.project_machimo.auction.controller;

import com.example.project_machimo.auction.service.CheckResponseEntity;
import com.example.project_machimo.auction.service.CheckResponseEntityImpl;
import com.example.project_machimo.auction.dto.CheckDTO;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auction")
public class AuctionRestController {



     private final CheckResponseEntity response;

    @Autowired
    public AuctionRestController(CheckResponseEntity response) {
        this.response = response;
    }

    @PostMapping("/auction-list/amountCheck")
    public ResponseEntity<? extends Object> updateAmount(@RequestBody CheckDTO check, HttpSession session) {

        Integer userId = (Integer)session.getAttribute("user");
        if (userId == null ){
            return response.sessionEntityForCheck();
        }

        return response.getResponseEntityForCheck(check,userId);

    }




}
