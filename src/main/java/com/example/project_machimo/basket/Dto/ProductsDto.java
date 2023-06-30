package com.example.project_machimo.basket.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductsDto {
    private Integer productId;
    private Integer cId;
    private Integer cId2;
    private Integer userId;
    private String pName;
    private String pInfo;
    private Integer pDirect;
    private Timestamp pDur;
    private Integer pBPrice;
    private Timestamp pCreatedAt;
    private Timestamp pUpdatedAt;
    private Integer pHit;
    private Integer pSalesStatus;
    private Integer pSaleType;
    private String pAccount;
    private String pAddress;
    private String pBank;
}
