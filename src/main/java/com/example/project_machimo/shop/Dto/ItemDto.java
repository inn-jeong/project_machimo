package com.example.project_machimo.shop.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {
    private Integer products_id;//제품번호
    private Integer users_id;//유저 번호
    private Integer c_id;//카테고리 번호
    private String p_name;//제품 이름
    private String p_info;//제품 정보
    private Integer p_direct;//즉시 판매가
    private Timestamp p_dur;//경매기간
    private Integer p_b_price;//경매가
    private Timestamp p_created_at;//제품 게시물 작성일
    private Timestamp p_updated_at;//제품 게시물 수정날짜
    private Integer p_hit;//제품 게시물 조회수
    private Integer p_sales_status;//판매 상태
    private Integer p_sale_type;//판매 유형
    private String p_account;//판매정산계좌
    private String p_address;//반송주소
    private String p_bank;//은행명
    private String u_nickname; //사용자의 닉네임
    private String i_sub_image; //제품의 서브 이미지
    private Integer wish_like;//좋아요수
}
