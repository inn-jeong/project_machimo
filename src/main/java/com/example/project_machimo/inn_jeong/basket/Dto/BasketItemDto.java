package com.example.project_machimo.inn_jeong.basket.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//장바구니 아이템
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasketItemDto {
    private Integer productId;
    private Integer userId;
    private String uName;
    private String pName;
    private Integer pDirect;
    private Integer pSalesStatus;
    private String iSubImg;
}
