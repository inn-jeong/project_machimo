package com.example.project_machimo.gyuha.wishlists.dao;

import com.example.project_machimo.gyuha.wishlists.dto.WishlistsDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WishListsDAO {

    //좋아요한 내역이 있는지 확인
    Integer likeCheck(Integer userId, Integer productId);

    //찜을 할시에 db에 정보를 추가함
    int insertWish(WishlistsDTO wishlistsDTO);

    //찜을 취소할시에 db에 정보를 삭제함
    int deleteWish(WishlistsDTO wishlistsDTO);



}
