package com.example.project_machimo.auction.dao;

import com.example.project_machimo.auction.dto.ProductsDTO;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Timestamp;
import java.util.List;


@Mapper
public interface ProductsDAO {

    List<ProductsDTO> pList();
    List<ProductsDTO> pView(int id);

    void succeedsSale(Timestamp timestamp,int productId );
    void failedSale(Timestamp timestamp,int productId);
}
