package com.example.project_machimo.auction.dto;

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
