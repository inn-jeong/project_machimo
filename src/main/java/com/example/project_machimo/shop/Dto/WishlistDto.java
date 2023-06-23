package com.example.project_machimo.shop.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WishlistDto {
    private Integer wish_id;//좋아요테이블 코드
    private Integer user_id;//사용자테이블 코드
    private Integer product_id;//상품테이블 코드
    private Integer wish_like;//좋아요 갯수

}