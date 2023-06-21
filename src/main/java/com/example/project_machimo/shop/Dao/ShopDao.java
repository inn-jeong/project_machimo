package com.example.project_machimo.shop.Dao;

import com.example.project_machimo.shop.Dto.*;

import java.util.ArrayList;

public interface ShopDao {
//    public ArrayList<ProductDto> categoryContect();
//  모든 상품을 보는 메소드
    public ArrayList<ProductDto> allItemView();
//    상품에서 사용자의 닉네임을 찾는 메소드
    public ArrayList<UsersDto> findNickName(int user_id);
    //제품 번호에 맞게 이미지를 매칭시키는 메소드
    public ArrayList<ImgDto> viewImage(int product_id);
}
