package com.example.project_machimo.gyuha.order.vo;

public record BuyerVO(
        Integer userId,
        String uName,
        Integer uPoint,
        String uAddress,
        String uAddress_sub,
        String uPhone,
        String uEmail,
        String uAddrPostCode

) {
}
