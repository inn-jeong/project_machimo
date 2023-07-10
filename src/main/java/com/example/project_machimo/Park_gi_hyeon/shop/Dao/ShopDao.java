package com.example.project_machimo.Park_gi_hyeon.shop.Dao;

import com.example.project_machimo.Park_gi_hyeon.shop.Dto.*;
import com.example.project_machimo.inn_jeong.login.Dto.UsersDto;

import java.util.ArrayList;
import java.util.List;

public interface ShopDao {
//  모든 상품을 보는 메소드
    public ArrayList<ItemDto> allItemView();
//    상품에서 사용자의 닉네임을 찾는 메소드
    public ArrayList<UsersDto> findNickName(int usersId);
    //제품 번호에 맞게 이미지를 매칭시키는 메소드
    public ArrayList<ImgDto> viewImage(int productId);
    //제품 번호에 맞는 좋아요 갯수를 가져오는 메소드
    public ArrayList<WishlistDto> wishLike(int productId);
    public ArrayList<CategoryDto> getCategories();
    public ArrayList<CategoryDto> getSubCategories(Integer cId2);
    public List<ItemDto> getProductsBySubcategoryId(int cId);
    public List<ItemDto> getProductsBycategoryId(int cId2);
    public ArrayList<CategoryDto> getCategoryById(Integer cId);
    public Integer countProduct();
}
