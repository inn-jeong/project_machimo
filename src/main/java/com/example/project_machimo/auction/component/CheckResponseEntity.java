package com.example.project_machimo.auction.component;

import com.example.project_machimo.auction.dto.BidsDTO;
import com.example.project_machimo.auction.dto.CheckDTO;
import com.example.project_machimo.auction.service.AuctionService;
import com.example.project_machimo.auction.service.BidsService;
import com.example.project_machimo.auction.service.ProductService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CheckResponseEntity {
    @Autowired
    private AuctionService auctionService;
    @Autowired
    private ProductService productService;
    @Autowired
    private BidsService bidsService;

    @Bean
    public ResponseEntity<?> getResponseEntity(CheckDTO check) {
        CustomResponse response = new CustomResponse();
        if (check.getBids() == null) {

            response.setMessage("금액을 입력해주세요");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        try {
            Long bids = 0L;
            if (check.getBids() != null) bids = check.getBids();
            int productId = check.getProductId();
            boolean bidsHistory = check.getBidsHistory();
            Long firstPrice = 0L;

            if (check.getFirstPrice() != null) {
                firstPrice = check.getFirstPrice();
            }

            Long amount = bidsService.maxAmount(productId);
            System.out.println("firstPrice = " + firstPrice);
            System.out.println("bids" + bids);
            if (firstPrice != 0) {
                if (firstPrice < bids) {
                    bidsService.write(bids, productId, firstPrice);
                    auctionService.highestBidUpdate(bids, productId);
                    amount = (long) bidsService.maxAmount(productId);
                    List<BidsDTO> bidsDTOS = bidsService.bList(productId);

                    return ResponseEntity.ok(bidsDTOS);
                } else if (bids > amount) {
                    bidsService.amountUpdate(bids, productId);
                    auctionService.highestBidUpdate(bids, productId);
                    amount = bidsService.maxAmount(productId);
                    List<BidsDTO> bidsDTOS = bidsService.bList(productId);
                    return ResponseEntity.ok(bidsDTOS);
                } else {

                    response.setMessage("입찰가가 기존 금액보다 낮습니다");
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

                }
            } else {

                response.setMessage("입찰금액을 입력해주세요");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }



        } catch (StackOverflowError e) {

            response.setMessage("숫자가 너무 높음");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @Data
    static class CustomResponse {
        private String message;
    }

}
