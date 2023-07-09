package com.example.project_machimo.gyuha.basket.service;


import com.example.project_machimo.gyuha.basket.dto.BasketDTO;

import java.util.List;

public interface BasketService {
    int addBasket(BasketDTO basketDTO);

    Integer checkBasket(BasketDTO basketDTO);
    int deleteBasketList(List<Integer> productId);
}
