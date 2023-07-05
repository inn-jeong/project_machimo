package com.example.project_machimo.shop.Service;

import com.example.project_machimo.shop.Dao.ShopDao;
import com.example.project_machimo.shop.Dto.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service("ShopService")
public class ShopServiceImpl implements ShopService{
    @Autowired
    private SqlSession sqlSession;
    @Override
    public ArrayList<ItemDto> allItemView() {
        log.info("@# allItemView start");
        ShopDao dao = sqlSession.getMapper(ShopDao.class);
        ArrayList<ItemDto> list = dao.allItemView();
        log.info("@# allItemView end");
        return list;
    }

    @Override
    public ArrayList<UsersDto> findNickName(int userId) {
        log.info("@# findNickName()");
        ShopDao dao = sqlSession.getMapper(ShopDao.class);
        ArrayList<UsersDto> nick = dao.findNickName(userId);
        return nick;
    }

    @Override
    public ArrayList<ImgDto> viewImage(int productId) {
        log.info("@# viewImage");
        ShopDao dao = sqlSession.getMapper(ShopDao.class);
        ArrayList<ImgDto> img = dao.viewImage(productId);
        return img;
    }

    @Override
    public ArrayList<WishlistDto> wishLike(int productId) {
        log.info("@# viewImage");
        ShopDao dao = sqlSession.getMapper(ShopDao.class);
        ArrayList<WishlistDto> wish = dao.wishLike(productId);
        return wish;
    }

    @Override
    public ArrayList<CategoryDto> getCategories() {
        log.info("@# getCategories");
        ShopDao dao = sqlSession.getMapper(ShopDao.class);
        ArrayList<CategoryDto> categories = dao.getCategories();
        return categories;
    }

    @Override
    public ArrayList<CategoryDto> getSubCategories(Integer cId2) {
        log.info("@# getSubCategories");
        ShopDao dao = sqlSession.getMapper(ShopDao.class);
        ArrayList<CategoryDto> subCategories = dao.getSubCategories(cId2);
        return subCategories;
    }

    @Override
    public List<ItemDto> getProductsBySubcategoryId(int cId) {
        ShopDao dao = sqlSession.getMapper(ShopDao.class);
        List<ItemDto> getProductsBySubcategoryId = dao.getProductsBySubcategoryId(cId);
        return getProductsBySubcategoryId;
    }

    @Override
    public List<ItemDto> getProductsBycategoryId(int cId2) {
        ShopDao dao = sqlSession.getMapper(ShopDao.class);
        List<ItemDto> getProductsBycategoryId = dao.getProductsBycategoryId(cId2);
        return getProductsBycategoryId;
    }

    @Override
    public ArrayList<CategoryDto> getCategoryById(Integer cId) {
        ShopDao dao = sqlSession.getMapper(ShopDao.class);
        ArrayList<CategoryDto> getCategoryById = dao.getCategoryById(cId);
        return getCategoryById;
    }
}
