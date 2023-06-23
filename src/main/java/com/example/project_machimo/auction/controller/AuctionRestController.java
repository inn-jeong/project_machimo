package com.example.project_machimo.auction.controller;

import com.example.project_machimo.auction.component.CheckResponseEntity;
import com.example.project_machimo.auction.dto.CheckDTO;
import com.example.project_machimo.auction.service.AuctionService;
import com.example.project_machimo.auction.service.BidsService;
import com.example.project_machimo.auction.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auction")
public class AuctionRestController {
    @Autowired
    private AuctionService auctionService;
    @Autowired
    private ProductService productService;
    @Autowired
    private BidsService bidsService;

    @Autowired
    private CheckResponseEntity tlqkf;

    @PostMapping("/action-list/amountCheck")
    public ResponseEntity<? extends Object> updateAmount(@RequestBody CheckDTO check) {

        return tlqkf.getResponseEntity(check);

    }

}
