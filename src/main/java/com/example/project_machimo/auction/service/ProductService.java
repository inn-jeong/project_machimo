package com.example.project_machimo.auction.service;


import com.example.project_machimo.auction.dto.ProductsDTO;

import java.util.List;

public interface ProductService {
    List<ProductsDTO> pList();
    List<ProductsDTO> pView(int id);
}
