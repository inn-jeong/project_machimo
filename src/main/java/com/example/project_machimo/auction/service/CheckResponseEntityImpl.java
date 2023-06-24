package com.example.project_machimo.auction.service;

import com.example.project_machimo.auction.dto.BidsVO;
import com.example.project_machimo.auction.dto.CheckDTO;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CheckResponseEntityImpl implements CheckResponseEntity{

   final private AuctionService auctionService;


    final private BidsService bidsService;

    @Autowired
    public CheckResponseEntityImpl(AuctionService auctionService, BidsService bidsService) {
        this.auctionService = auctionService;
        this.bidsService = bidsService;
    }

    @Override
    public ResponseEntity<?> getResponseEntityForCheck(CheckDTO check) {
        CustomResponse response = new CustomResponse();
        if (check.getBids() == null) {

            response.setMessage("금액을 입력해주세요");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
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
                    List<BidsVO> bidsVOS = bidsService.bList(productId);

                    return ResponseEntity.ok(bidsVOS);
                } else if (bids > amount) {
                    bidsService.amountUpdate(bids, productId);
                    auctionService.highestBidUpdate(bids, productId);
                    amount = bidsService.maxAmount(productId);
                    List<BidsVO> bidsVOS = bidsService.bList(productId);
                    return ResponseEntity.ok(bidsVOS);
                } else {

                    response.setMessage("입찰가가 기존 금액보다 낮습니다");
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

                }
            } else {

                response.setMessage("입찰금액을 입력해주세요");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }




    }
@Data
    static class CustomResponse {
        private String message;
    }

}
