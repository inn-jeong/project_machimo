package com.example.project_machimo.inn_jeong.basket.Dao;

import com.example.project_machimo.inn_jeong.basket.Dto.BasketItemDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface BasketDao {
//    public ArrayList<BasketDto> getBasket(Integer userId);
//    public ProductsDto getItemInfo(Integer product_id);
//    public BasketItemDto getItemInfo(Integer productId);
    public int deleteItem(Integer userId, Integer productId);
    ArrayList<BasketItemDto> getBasketItems(Integer userId);
}
