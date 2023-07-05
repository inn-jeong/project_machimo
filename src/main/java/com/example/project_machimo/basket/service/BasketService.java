package com.example.project_machimo.basket.service;


import com.example.project_machimo.basket.dto.BasketDTO;

public interface BasketService {
    int addBasket(BasketDTO basketDTO);

    Integer checkBasket(BasketDTO basketDTO);

}
