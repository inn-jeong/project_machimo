package com.example.project_machimo.auction.dto;

import java.sql.Date;
import java.sql.Timestamp;

public record AuctionDTO(
        Integer auctionId
        , Integer userId
        ,Integer productsId
        , Timestamp startTime
        , Timestamp endTime
        , Integer highestBid
        ) {
}
