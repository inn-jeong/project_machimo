package com.example.project_machimo.gyuha.search.dto;


/*- 최규하
dao에서 서치 결과를 담기 위한 VO 편의성을 위해 record로 정의함*/
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
