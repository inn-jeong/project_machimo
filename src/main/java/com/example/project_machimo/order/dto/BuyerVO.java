package com.example.project_machimo.order.dto;

public record BuyerVO(
        Integer userId,
        String uName,
        Integer userPoint,
        String uAddress,
        String uAddress_sub,
        String uPhone,
        String uEmail,
        String uAddrPostCode
) {
}
