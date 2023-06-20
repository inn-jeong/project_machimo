package com.example.project_machimo.auction.dto;

import java.sql.Date;
import java.sql.Timestamp;

public record AuctionDTO(
        Integer auoctionId
        ,Integer productsId
        , Integer userId
        , Timestamp startTime
        , Timestamp endTime
        , Integer highestBid
        ) {
}
