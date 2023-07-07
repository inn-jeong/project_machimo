package com.example.project_machimo.jolocal.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
//    product
    private int productId;
    private int cId;
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
    private String pBank;
    private String pAddress;
    private String pAddressSub;
    private Integer uAddrPostcode;
    private int pLike;
    private int pDurDate;
//    category
//    private int cId;
    private int cId2;
    private String cName;

//    product_images
//    private int i_id;
//    private int productId;
//    private String iImage;
//    private String iSubImg;






}
