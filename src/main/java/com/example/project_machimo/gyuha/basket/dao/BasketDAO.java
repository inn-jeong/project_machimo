package com.example.project_machimo.gyuha.basket.dao;

import com.example.project_machimo.gyuha.basket.dto.BasketDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BasketDAO {

    int addBasket(BasketDTO basketDTO);
    Integer checkBasket(BasketDTO basketDTO);

    int deleteBasketList(Integer productId);

}
