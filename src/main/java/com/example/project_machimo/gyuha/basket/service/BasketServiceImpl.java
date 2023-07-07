package com.example.project_machimo.gyuha.basket.service;

import com.example.project_machimo.gyuha.basket.dao.BasketDAO;
import com.example.project_machimo.gyuha.basket.dto.BasketDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasketServiceImpl implements BasketService {

    private final BasketDAO basketDAO;

    @Autowired
    public BasketServiceImpl(BasketDAO basketDAO) {
        this.basketDAO = basketDAO;
    }

    @Override
    public int addBasket(BasketDTO basketDTO) {

        return basketDAO.addBasket(basketDTO);
    }

    @Override
    public Integer checkBasket(BasketDTO basketDTO) {
        return basketDAO.checkBasket(basketDTO);
    }
}
