package com.example.project_machimo.basket.Service;

import com.example.project_machimo.basket.Dto.BasketDto;
import com.example.project_machimo.basket.Dto.ProductsDto;

import java.util.ArrayList;

public interface BasketService {
    public ArrayList<BasketDto> getBasket(Integer user_id);
    public ArrayList<ProductsDto> getBasketItems(ArrayList<BasketDto> basket);
}
