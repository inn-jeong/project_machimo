package com.example.project_machimo.wishlists.dao;

import com.example.project_machimo.wishlists.dto.WishlistsDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WishListsDAO {

    Integer likeCheck(Integer userId, Integer productId);

    int insertWish(WishlistsDTO wishlistsDTO);


    int deleteWish(WishlistsDTO wishlistsDTO);



}
