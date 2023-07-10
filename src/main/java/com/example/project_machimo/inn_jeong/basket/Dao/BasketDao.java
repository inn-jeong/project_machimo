package com.example.project_machimo.inn_jeong.basket.Dao;

import com.example.project_machimo.inn_jeong.basket.Dto.BasketItemDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface BasketDao {
    //장바구니에서 제품 삭제
    public int deleteItem(Integer userId, Integer productId);
    //장바구니 테이블을 통해 제품 조회
    ArrayList<BasketItemDto> getBasketItems(Integer userId);
}
