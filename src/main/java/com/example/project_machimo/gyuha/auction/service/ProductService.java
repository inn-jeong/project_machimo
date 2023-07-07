package com.example.project_machimo.gyuha.auction.service;


import com.example.project_machimo.gyuha.auction.dto.ProductsVO;

import java.util.List;

public interface ProductService {
    List<ProductsVO> pList();
    ProductsVO pView(int id);
}
