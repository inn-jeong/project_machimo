package com.example.project_machimo.basket.Service;

import com.example.project_machimo.basket.Dto.BasketDto;
import com.example.project_machimo.basket.Dto.BasketItemDto;
import com.example.project_machimo.basket.Dto.ProductsDto;

import java.util.ArrayList;

public interface BasketService {
    public ArrayList<BasketDto> getBasket(Integer userId);
//    public ArrayList<ProductsDto> getBasketItems(ArrayList<BasketDto> basket);
    public ArrayList<BasketItemDto> getBasketItems(ArrayList<BasketDto> basket);
    public ArrayList<BasketItemDto> getBasketItems(Integer userId);
    public int deleteItem(Integer userId, Integer productId);
}
