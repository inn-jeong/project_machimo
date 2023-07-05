package com.example.project_machimo.wishlists.service;

import com.example.project_machimo.wishlists.dto.WishlistsDTO;

public interface WishListsService {

    int insertWish(WishlistsDTO wishlistsDTO);


    int deleteWish(WishlistsDTO wishlistsDTO);

}
