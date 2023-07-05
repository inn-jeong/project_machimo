package com.example.project_machimo.productEnroll.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private int productId;//시퀀스, 트리거로 증가, 필수
    private int cId;//사용자가 입력, 필수
    private int cId2;//사용자가 입력
    private int userId;//사용자가 입력, 필수
    private String pName;//사용자가 입력, 필수
    private String pInfo;//사용자가 입력, 필수
    private int pDirect;//사용자가 입력한 즉시판매가- 경매라면 0
    private Timestamp pDur;//sysdate+ pDurDate
    private int pDurDate;//7/14/21일 중에 사용자가 고른 숫자, 필수
    private int pBPrice;//사용자가 입력한 경매 시작가격- 즉시판매라면 0
    private Timestamp pCreatedAt;//sysdate로 입력, 필수
    private Timestamp pUpdatedAt;
    private int pHit;//기본 0
    private int pSalesStatus;//기본값 0 => 승인대기
    private int pSaleType;//사용자가 드롭박스에서 고른 판매형태, 필수
    private String pAccount;//사용자가 입력, 필수
    private String pAddress;//사용자가 입력, 필수
    private String pBank;//사용자가 입력, 필수
    private String pAddressSub;//사용자가 입력, 필수
    private int pAddrPostcode;//사용자가 입력, 필수
    private int pLike;//0

}