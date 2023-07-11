package com.example.project_machimo.inn_jeong.basket.Service;

import com.example.project_machimo.inn_jeong.basket.Dao.BasketDao;
import com.example.project_machimo.inn_jeong.basket.Dto.BasketItemDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Slf4j
@Service("BasketService1")
public class BasketServiceImpl implements BasketService {
    @Autowired
    private SqlSession sqlSession;

    @Override
    public ArrayList<BasketItemDto> getBasketItems(Integer userId) {
        BasketDao dao = sqlSession.getMapper(BasketDao.class);
        return dao.getBasketItems(userId);
    }


    @Override
    public int deleteItem(Integer userId, Integer productId) {
        BasketDao dao = sqlSession.getMapper(BasketDao.class);
        return dao.deleteItem(userId, productId);
    }
}
