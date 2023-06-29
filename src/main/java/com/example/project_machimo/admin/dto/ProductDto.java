package com.example.project_machimo.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
//    product
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

//    productImg
    private int iId;
//    private int productId;
    private String iImage;
    private String IsubImg; //썸네일-> 이거 쓸 예정

//    category
//    private int cId;
//    private int cId2;
    private String cName;
    private String cName2;

}
