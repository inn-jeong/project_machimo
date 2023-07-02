package com.example.project_machimo.shop.Dao;

import com.example.project_machimo.shop.Dto.*;

import java.util.ArrayList;
import java.util.List;

public interface ShopDao {
//    public ArrayList<ProductDto> categoryContect();
//  모든 상품을 보는 메소드
    public ArrayList<ProductDto> allItemView();
//    상품에서 사용자의 닉네임을 찾는 메소드
    public ArrayList<UsersDto> findNickName(int users_id);
    //제품 번호에 맞게 이미지를 매칭시키는 메소드
    public ArrayList<ImgDto> viewImage(int product_id);
    //제품 번호에 맞는 좋아요 갯수를 가져오는 메소드
    public ArrayList<WishlistDto> wishLike(int product_id);
    List<ProductDto> getProductsByCategoryId(int c_id);
    public ArrayList<CategoryDto> getCategories();
    public ArrayList<CategoryDto> getSubCategories(Integer c_id2);
}
