package com.example.project_machimo.search.dto;

public record SearchVO(
          Long productId
        , Long userId
        , String uNickName
        , Long iId
        , String iImage
        , String iSubImage
        ,String pName
        ,String pInfo
        ,Integer pSalesStatus
        ,Integer pSaleType
        ,Long pDirect
        ,Long pBPrice
) {
}
