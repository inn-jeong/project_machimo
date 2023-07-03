package com.example.project_machimo.auction.dao;

import com.example.project_machimo.auction.dto.ProductsVO;
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
}