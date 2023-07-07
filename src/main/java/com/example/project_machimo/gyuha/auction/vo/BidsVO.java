package com.example.project_machimo.gyuha.auction.vo;

import java.sql.Timestamp;

public record BidsVO(
        Integer auctionId,
        Integer userId,
        Integer productsId,
        Integer amount,
        Integer startPrice,
        Integer bidsCode,
        Timestamp bidsAt


) {
}
