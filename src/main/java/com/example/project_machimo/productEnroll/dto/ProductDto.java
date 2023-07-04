package com.example.project_machimo.productEnroll.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private int productId;//시퀀스, 트리거로 증가
    private int cId;
    private int cId2;
    private int userId;//일단 0
    private String pName;
    private String pInfo;
    private int pDirect;//사용자가 입력한 즉시판매가- 경매라면 0
    private Timestamp pDur;//사용자가 달력에서 고른 날짜-sysdate
    private int pBPrice;//사용자가 입력한 경매 시작가격- 즉시판매라면 0
    private Timestamp pCreatedAt;
    private Timestamp pUpdatedAt;
    private int pHit;//기본 0
    private int pSalesStatus;//기본값 0 => 승인대기
    private int pSaleType;//사용자가 드롭박스에서 고른 판매형태
    private String pAccount;//사용자가 입력, 필수
    private String pAddress;//사용자가 입력, 필수
    private String pBank;//사용자가 입력, 필수
}