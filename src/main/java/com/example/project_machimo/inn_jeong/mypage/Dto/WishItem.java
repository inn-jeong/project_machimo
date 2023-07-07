package com.example.project_machimo.inn_jeong.mypage.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WishItem {
    private Integer wishlistId;
    private Integer productId;
    private String iSubImg;
    private Integer userId;
    private String uName;
    private String uNickname;
    private String pName;
    private Integer pDirect;
    private Integer pBPrice;
    private Integer pSaleType;
    private Integer pSalesStatus;
}
