package com.example.project_machimo.auction.service;

import com.example.project_machimo.auction.dto.BidsVO;
import com.example.project_machimo.auction.dto.CheckDTO;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;


@Slf4j
@Component
public class CheckResponseEntityImpl implements CheckResponseEntity {

    final private AuctionService auctionService;


    final private BidsService bidsService;

    @Autowired
    public CheckResponseEntityImpl(AuctionService auctionService, BidsService bidsService) {
        this.auctionService = auctionService;
        this.bidsService = bidsService;
    }

@Override
public ResponseEntity<?> getResponseEntityForCheck(CheckDTO check,Integer userSession) {
    CustomResponse response = new CustomResponse();
    Long bids = check.getBids();
    if (bids == null) {
        response.setMessage("금액을 입력해주세요");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    int productId = check.getProductId();
    Long firstPrice = check.getFirstPrice();
    Integer userId =  userSession;
    if (!isNonZeroAndNotNull(firstPrice)) {
        response.setMessage("입찰금액을 입력해주세요");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    return processBid(bids, productId, firstPrice,userId);
}



private boolean isNonZeroAndNotNull(Long num){
    if(num != null && num != 0){
        return true;
    }
    return false;
}

private ResponseEntity<?> processBid(Long bids, int productId, Long firstPrice,Integer userId) {
    Long amount = bidsService.maxAmount(productId);
    log.info("firstPrice = " + firstPrice);
    log.info("bids = " + bids);

    if (bids<amount||bids<firstPrice){
        CustomResponse response = new CustomResponse();
        response.setMessage("입찰가가 기존 금액보다 낮습니다.");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

     if (bids > amount && bids > firstPrice) {
         return updateExistingBid(bids, productId, userId);
     }else if(bids.equals(amount)){
        CustomResponse response = new CustomResponse();
        response.setMessage("입찰가가 기존 금액과 동일합니다.");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
     }else if(bids.equals(firstPrice)){
        CustomResponse response = new CustomResponse();
        response.setMessage("입찰가가 기존 금액과 동일합니다.");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

     return null;
}

private ResponseEntity<?> processNewBid(Long bids, int productId, Long firstPrice,Integer userId) {
    bidsService.write(bids, productId, firstPrice,userId);
    auctionService.highestBidUpdate(bids, productId,userId);
    log.info("새로운 입찰자 나왔슴");
    List<BidsVO> bidsVOS = bidsService.bList(productId);
    return ResponseEntity.ok(bidsVOS);
}

private ResponseEntity<?> updateExistingBid(Long bids, int productId,Integer userId) {
    bidsService.amountUpdate(bids, productId,userId);
    auctionService.highestBidUpdate(bids, productId,userId);
    log.info("입찰가 업데이트됨");
    List<BidsVO> bidsVOS = bidsService.bList(productId);
    return ResponseEntity.ok(bidsVOS);
}

    @Override
    public ResponseEntity<?> sessionEntityForCheck() {
        CustomResponse response = new CustomResponse();
        response.setMessage("로그인이 필요한 서비스입니다.");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @Data
    static class CustomResponse {
        private String message;
    }
}

//
//    CustomResponse response = new CustomResponse();
//        if (check.getBids() == null) {
//
//            response.setMessage("금액을 입력해주세요");
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
//        }
//        Long bids = 0L;
//        if (check.getBids() != null) bids = check.getBids();
//        int productId = check.getProductId();
//        boolean bidsHistory = check.getBidsHistory();
//        Long firstPrice = 0L;
//
//
//
//        if (check.getFirstPrice() != null) {
//            firstPrice = check.getFirstPrice();
//        }
//
//        Long amount = bidsService.maxAmount(productId);
//        System.out.println("firstPrice = " + firstPrice);
//        System.out.println("bids" + bids);
//        if (firstPrice != 0 || firstPrice !=null) {
//            if (firstPrice < bids) {
//                bidsService.write(bids, productId, firstPrice);
//                auctionService.highestBidUpdate(bids, productId);
//                amount = (long) bidsService.maxAmount(productId);
//                List<BidsVO> bidsVOS = bidsService.bList(productId);
//                log.info("이게 동작합니다 121231242131231231243213");
//                return ResponseEntity.ok(bidsVOS);
//            } else if (bids > amount && bids>firstPrice) {
//
//                log.info("ㄴㄴ 이게 동장함 @!##!@#!@$^&!@&*#^!@&#%*!@^&");
//                bidsService.amountUpdate(bids, productId);
//                auctionService.highestBidUpdate(bids, productId);
//                amount = bidsService.maxAmount(productId);
//                List<BidsVO> bidsVOS = bidsService.bList(productId);
//                return ResponseEntity.ok(bidsVOS);
//            } else {
//
//                response.setMessage("입찰가가 기존 금액보다 낮습니다");
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
//
//            }
//        } else {
//
//            response.setMessage("입찰금액을 입력해주세요");
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
//        }
//