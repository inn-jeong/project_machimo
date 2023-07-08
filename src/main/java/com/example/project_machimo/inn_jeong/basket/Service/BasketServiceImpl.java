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

//    @Override
//    public ArrayList<BasketDto> getBasket(Integer userId) {
//        BasketDao dao = sqlSession.getMapper(BasketDao.class);
//        ArrayList<BasketDto> basket = dao.getBasket(userId);
//        return basket;
//    }

//    @Override
//    public ArrayList<BasketItemDto> getBasketItems(ArrayList<BasketDto> basket) {
//        BasketDao dao = sqlSession.getMapper(BasketDao.class);
//        ArrayList<BasketItemDto> basketItems = new ArrayList<>();
//        for(BasketDto dto:basket){
//            Integer productId = dto.getProductId();
////            log.info("@# dao getBasket id ===>"+dto.getProductId());
//            BasketItemDto itemDto = dao.getItemInfo(productId);
////            log.info("@# dao getBasket item_id ===>"+itemDto.getProductId());
//            basketItems.add(itemDto);
//        }
//        return basketItems;
//    }

    @Override
    public ArrayList<BasketItemDto> getBasketItems(Integer userId) {
        BasketDao dao = sqlSession.getMapper(BasketDao.class);
        return dao.getBasketItems(userId);
    }

//    @Override
//    public ArrayList<ProductsDto> getBasketItems(ArrayList<BasketDto> basket) {
//        BasketDao dao = sqlSession.getMapper(BasketDao.class);
//        ArrayList<ProductsDto> basketItems = new ArrayList<>();
//        for(BasketDto dto:basket){
//            Integer product_id = dto.getProduct_id();
//            ProductsDto productsDto = dao.getItemInfo(product_id);
//            basketItems.add(productsDto);
//        }
//        return basketItems;
//    }

    @Override
    public int deleteItem(Integer userId, Integer productId) {
        BasketDao dao = sqlSession.getMapper(BasketDao.class);
        int result = dao.deleteItem(userId, productId);

        return result;
    }
}
