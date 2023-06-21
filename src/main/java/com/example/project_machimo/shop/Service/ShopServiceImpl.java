package com.example.project_machimo.shop.Service;

import com.example.project_machimo.shop.Dao.ShopDao;
import com.example.project_machimo.shop.Dto.ImgDto;
import com.example.project_machimo.shop.Dto.ItemDto;
import com.example.project_machimo.shop.Dto.ProductDto;
import com.example.project_machimo.shop.Dto.UsersDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Slf4j
@Service("ShopService")
public class ShopServiceImpl implements ShopService{
    @Autowired
    private SqlSession sqlSession;
    @Override
    public ArrayList<ProductDto> allItemView() {
        log.info("@# allItemView start");
        ShopDao dao = sqlSession.getMapper(ShopDao.class);
        ArrayList<ProductDto> list = dao.allItemView();
        log.info("@# allItemView end");
        return list;
    }

    @Override
    public ArrayList<UsersDto> findNickName(int user_id) {
        log.info("@# findNickName()");
        ShopDao dao = sqlSession.getMapper(ShopDao.class);
        ArrayList<UsersDto> nick = dao.findNickName(user_id);
        return nick;
    }

    @Override
    public ArrayList<ImgDto> viewImage(int product_id) {
        log.info("@# viewImage");
        ShopDao dao = sqlSession.getMapper(ShopDao.class);
        ArrayList<ImgDto> img = dao.viewImage(product_id);
        return null;
    }
}
