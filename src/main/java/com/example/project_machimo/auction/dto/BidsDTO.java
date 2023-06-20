package com.example.project_machimo.auction.dto;

public record BidsDTO(
        Integer auoctionId,
        Integer userId,
        Integer productsId,
        Integer amount,
        Integer startPrice,
        String bids_code
) {
}
