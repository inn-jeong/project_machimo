package com.example.project_machimo.gyuha.wishlists.service;

import com.example.project_machimo.gyuha.auction.dao.ProductsDAO;
import com.example.project_machimo.gyuha.wishlists.dao.WishListsDAO;
import com.example.project_machimo.gyuha.wishlists.dto.WishlistsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




/* - 최규하
찜목록 처리 Service
*/
@Service
public class WishListsServiceImpl implements WishListsService{

    private final WishListsDAO wishListsDAO;
    private final ProductsDAO productsDAO;

    @Autowired
    public WishListsServiceImpl(WishListsDAO wishListsDAO, ProductsDAO productsDAO) {
        this.wishListsDAO = wishListsDAO;
        this.productsDAO = productsDAO;
    }


    /* - 최규하
    제품테이블에 좋아요 갯수를 +1 업데이트 시킴
    그 후 위시리트에 추가
            */
    @Override
    public int insertWish(WishlistsDTO wishlistsDTO) {
        productsDAO.updateLikeUp(wishlistsDTO.getProductId());
        return wishListsDAO.insertWish(wishlistsDTO);
    }
    /* - 최규하
    제품테이블에 좋아요 갯수를 -1 업데이트 시킴
    그 후 위시리스트에서 제거
            */

    @Override
    public int deleteWish(WishlistsDTO wishlistsDTO) {
        productsDAO.updateLikeDown(wishlistsDTO.getProductId());
        return wishListsDAO.deleteWish(wishlistsDTO);
    }

    @Override
    public boolean hasLike(Integer userId, Integer productId) {
        return wishListsDAO.likeCheck(userId,productId)!=null;
    }
}
