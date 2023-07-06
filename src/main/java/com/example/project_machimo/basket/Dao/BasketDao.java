package com.example.project_machimo.basket.Dao;

import com.example.project_machimo.basket.Dto.BasketDto;
import com.example.project_machimo.basket.Dto.BasketItemDto;
import com.example.project_machimo.basket.Dto.ProductsDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface BasketDao {
    public ArrayList<BasketDto> getBasket(Integer userId);
//    public ProductsDto getItemInfo(Integer product_id);
    public BasketItemDto getItemInfo(Integer productId);
    public int deleteItem(Integer userId, Integer productId);
    ArrayList<BasketItemDto> getBasketItems(Integer userId);
}
