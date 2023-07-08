package com.example.project_machimo.inn_jeong.basket.Service;

import com.example.project_machimo.inn_jeong.basket.Dto.BasketItemDto;

import java.util.ArrayList;

public interface BasketService {
//    public ArrayList<BasketDto> getBasket(Integer userId);
//    public ArrayList<ProductsDto> getBasketItems(ArrayList<BasketDto> basket);
//    public ArrayList<BasketItemDto> getBasketItems(ArrayList<BasketDto> basket);
    public ArrayList<BasketItemDto> getBasketItems(Integer userId);
    public int deleteItem(Integer userId, Integer productId);
}
