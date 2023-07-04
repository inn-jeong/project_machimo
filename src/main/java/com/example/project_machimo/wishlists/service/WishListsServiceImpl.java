package com.example.project_machimo.wishlists.service;

import com.example.project_machimo.wishlists.dao.WishListsDAO;
import com.example.project_machimo.wishlists.dto.WishlistsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishListsServiceImpl implements WishListsService {

    private final WishListsDAO wishListsDAO;

    @Autowired
    public WishListsServiceImpl(WishListsDAO wishListsDAO) {
        this.wishListsDAO = wishListsDAO;
    }


    @Override
    public boolean hasWishLists(WishlistsDTO wishlistsDTO) {
        return   wishListsDAO.checkId(wishlistsDTO) != null;
    }
}
