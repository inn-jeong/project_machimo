package com.example.project_machimo.Park_gi_hyeon.shop.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WishlistDto {
    private Integer wishId;//좋아요테이블 코드
    private Integer userId;//사용자테이블 코드
    private Integer productId;//상품테이블 코드
    private Integer wishLike;//좋아요 갯수

}