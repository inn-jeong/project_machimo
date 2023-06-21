package com.example.project_machimo.auction.dao;

import com.example.project_machimo.auction.dto.ProductsDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface ProductsDAO {

    List<ProductsDTO> pList();
    List<ProductsDTO> pView(int id);

}
