package com.example.project_machimo.gyuha.auction.vo;

import java.sql.Timestamp;


public record ProductsVO(
        Integer productsId
        ,Integer cId
        ,Integer cId2
        ,Integer userId
        ,String pName
        ,String pInfo
        ,Integer pDirect
        ,Timestamp pDur
        ,Integer pBPrice
        ,Timestamp pCreatedAt
        ,Timestamp pUpdatedAt
        ,Integer pHit
        ,Integer pSalesStatus
        ,Integer pSaleType
        ,String urlImg
        ) {
}
