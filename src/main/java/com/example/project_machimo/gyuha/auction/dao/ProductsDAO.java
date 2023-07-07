package com.example.project_machimo.gyuha.auction.dao;

import com.example.project_machimo.gyuha.auction.vo.ProductsVO;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Timestamp;
import java.util.List;


@Mapper
public interface ProductsDAO {

    List<ProductsVO> pList();
    ProductsVO pView(int id);

    void succeedsSale(Timestamp timestamp,int productId );
    void failedSale(Timestamp timestamp,int productId);

    void updatePrice();

    void updateProductStatusCompletedCase(int productId);

    void updateLikeUp(int productId);
    void updateLikeDown(int productId);

    void updateHit(int id);

    void updateBPrice(int id,Long price);
}
