package com.example.project_machimo.soldForm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private int productId;
    private int cId;
    private int cId2;
    private int userId;
    private String pName;
    private String pInfo;
    private int pDirect;
    private Timestamp pDur;
    private int pBPrice;
    private Timestamp pCreatedAt;
    private Timestamp pUpdatedAt;
    private int pHit;
    private int pSalesStatus;
    private int pSaleType;
    private String pAccount;
    private String pAddress;
    private String pBank;
}