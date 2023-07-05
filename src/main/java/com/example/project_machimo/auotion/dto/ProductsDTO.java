package com.example.project_machimo.auotion.dto;

import java.sql.Timestamp;

public record ProductsDTO(
        Integer productsId
        ,Integer userId
        ,Byte categoryId
        ,String name
        ,Timestamp createAt
        ,Timestamp upadateAt
        ,Integer hit
        ,String productsInfo
        ,Integer priceI
        ,Timestamp auction_dur
        ,Integer priceA
        ,Integer salesStatus
        ,Integer saleType
        ) {
}
