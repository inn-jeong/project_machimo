<<<<<<<< HEAD:src/main/java/com/example/project_machimo/jomuragi/shop/Dto/ItemDto.java
package com.example.project_machimo.jomuragi.shop.Dto;
========
package com.example.project_machimo.Park_gi_hyeon.main.Dto;
>>>>>>>> origin/Park-gi-Hyeon:src/main/java/com/example/project_machimo/Park_gi_hyeon/main/Dto/ItemDto.java

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {
    private Integer productId;//제품번호
    private Integer userId;//유저 번호
    private Integer cId;//카테고리 번호
    private Integer cId2;//자기참조 카테고리 번호
    private String pName;//제품 이름
    private String pInfo;//제품 정보
    private Integer pDirect;//즉시 판매가
    private Timestamp pDur;//경매기간
    private Integer pBPrice;//경매가
    private Timestamp pCreatedAt;//제품 게시물 작성일
    private Timestamp pUpdatedAt;//제품 게시물 수정날짜
    private Integer pHit;//제품 게시물 조회수
    private Integer pSalesStatus;//판매 상태
    private Integer pSaleType;//판매 유형
    private String pAccount;//판매정산계좌
    private String pAddress;//반송주소
    private String pBank;//은행명
    private String uNickname; //사용자의 닉네임
    private String iSubImage; //제품의 서브 이미지
    private Integer pLike;//좋아요수
    private String cName;//카테고리 이름
}
