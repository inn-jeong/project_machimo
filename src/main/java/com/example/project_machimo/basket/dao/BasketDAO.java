package com.example.project_machimo.basket.dao;

import com.example.project_machimo.basket.dto.BasketDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BasketDAO {

    int addBasket(BasketDTO basketDTO);
    Integer checkBasket(BasketDTO basketDTO);


}
