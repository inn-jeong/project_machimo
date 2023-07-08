package com.example.project_machimo.inn_jeong.mypage.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalesItem {
    private Integer productId;
    private String pName;
    private Integer pDirect;
    private Integer pBPrice;
    private String pCreatedAt;
    private Integer pSalesStatus;
    private Integer pSaleType;
    private String iSubImg;
}
