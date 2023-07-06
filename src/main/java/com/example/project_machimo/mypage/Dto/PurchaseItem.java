package com.example.project_machimo.mypage.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseItem {
    private Integer orderId;
    private Integer productId;
    private Integer userId;
    private String uName;
    private String pName;
    private Integer pDirect;
    private String iSubImg;
    private String createdAt;
    private String orderStatus;
}