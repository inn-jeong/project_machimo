package com.example.project_machimo.gyuha.wishlists.service;

import com.example.project_machimo.gyuha.wishlists.dto.WishlistsDTO;

public interface WishListsService {

    int insertWish(WishlistsDTO wishlistsDTO);


    int deleteWish(WishlistsDTO wishlistsDTO);

}
