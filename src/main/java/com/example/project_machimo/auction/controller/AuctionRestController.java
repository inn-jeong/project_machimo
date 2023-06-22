package com.example.project_machimo.auction.controller;

import com.example.project_machimo.auction.dto.BidsDTO;
import com.example.project_machimo.auction.dto.CheckDTO;
import com.example.project_machimo.auction.service.AuctionService;
import com.example.project_machimo.auction.service.BidsService;
import com.example.project_machimo.auction.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auction")
public class AuctionRestController {
    @Autowired
    private AuctionService auctionService;
    @Autowired
    private ProductService productService;
    @Autowired
    private BidsService bidsService;


      @PostMapping("/action-list/amountCheck")
    public ResponseEntity<Map<String,Integer>> updateAmount(@RequestBody CheckDTO check) {
          Map<String,Integer> response = new HashMap<>();

        int bids = 0;

        if (check.getBids() != null) bids = check.getBids();
        int productId = check.getProductId();
        boolean bidsHistory = check.getBidsHistory();

        Integer firstPrice = 0;

        if (check.getFirstPrice() != null) {
            firstPrice = check.getFirstPrice();
        }

        Integer amount = bidsService.maxAmount(productId);
          System.out.println("firstPrice = " + firstPrice);
          System.out.println("bids" + bids);
          if (firstPrice != 0) {
              if (firstPrice < bids& bids>amount) {
                  bidsService.write(bids, productId, firstPrice);
                  auctionService.highestBidUpdate(bids, productId);
                  amount =bidsService.maxAmount(productId);
                  response.put("highestPrice",amount);
                  return ResponseEntity.ok(response);
              }else{
                  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
              }
          }
            if (amount < bids) {
                bidsService.amountUpdate(bids, productId);
                auctionService.highestBidUpdate(bids, productId);
                amount =bidsService.maxAmount(productId);
                response.put("highestPrice",amount);
                return ResponseEntity.ok(response);
            }else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }

    }

}
