package com.example.project_machimo.gyuha.order.vo;
/*
-최규하
구매한 제품 정보를 담는 테이블
*/

public record BuyProductVO(
         Integer productId
        ,String pName
        , Integer userId
        ,Integer pDirect
         ,Integer pBPrice
         ,Integer pSaleType

) {

}
