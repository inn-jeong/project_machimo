package com.example.project_machimo.auotion.dto;

public record BidsDTO(
        Integer auoctionId,
        Integer userId,
        Integer productsId,
        Integer amount,
        Integer startPrice,
        String bids_code
) {
}
