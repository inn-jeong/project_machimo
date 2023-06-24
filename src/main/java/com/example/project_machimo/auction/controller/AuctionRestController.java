package com.example.project_machimo.auction.controller;

import com.example.project_machimo.auction.service.CheckResponseEntity;
import com.example.project_machimo.auction.service.CheckResponseEntityImpl;
import com.example.project_machimo.auction.dto.CheckDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auction")
public class AuctionRestController {



    final private CheckResponseEntity response;

    @Autowired
    public AuctionRestController(CheckResponseEntity response) {
        this.response = response;
    }

    @PostMapping("/action-list/amountCheck")
    public ResponseEntity<? extends Object> updateAmount(@RequestBody CheckDTO check) {

        return response.getResponseEntityForCheck(check);

    }

}
