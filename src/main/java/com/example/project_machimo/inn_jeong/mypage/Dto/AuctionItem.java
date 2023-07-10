package com.example.project_machimo.inn_jeong.mypage.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuctionItem {
    private Integer auctionId;
    private Integer productsId;
    private String pName;
    private Integer userId;
    private String uNickname;
    private Integer userBidsId;
    private String uBidsNickname;
    private Integer currentAmount;
    private String pDur;
    private String bidsAt;
    private Integer amount;
    private Integer pBPrice;
    private Integer pSalesStatus;
    private String iSubImg;
}
