package com.example.project_machimo.gyuha.wishlists.service;

import com.example.project_machimo.gyuha.auction.dao.ProductsDAO;
import com.example.project_machimo.gyuha.wishlists.dao.WishListsDAO;
import com.example.project_machimo.gyuha.wishlists.dto.WishlistsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




/* - 최규하
찜목록 처리 Service*/
@Service
public class WishListsServiceImpl implements WishListsService{

    private final WishListsDAO wishListsDAO;
    private final ProductsDAO productsDAO;

    @Autowired
    public WishListsServiceImpl(WishListsDAO wishListsDAO, ProductsDAO productsDAO) {
        this.wishListsDAO = wishListsDAO;
        this.productsDAO = productsDAO;
    }



    @Override
    public int insertWish(WishlistsDTO wishlistsDTO) {
        productsDAO.updateLikeUp(wishlistsDTO.getProductId());
        return wishListsDAO.insertWish(wishlistsDTO);
    }

    @Override
    public int deleteWish(WishlistsDTO wishlistsDTO) {
        productsDAO.updateLikeDown(wishlistsDTO.getProductId());
        return wishListsDAO.deleteWish(wishlistsDTO);
    }
}
