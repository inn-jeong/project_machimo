package com.example.project_machimo.auotion.dto;

public record AuctionDTO(
        Integer auoctionId
        ,Integer userId
        ,Integer startTime
        ,Integer highestBid
) {
}
