package com.example.project_machimo.gyuha.auction.service;


import com.example.project_machimo.gyuha.auction.vo.ProductsVO;
import com.example.project_machimo.gyuha.auction.vo.Test;

import java.util.List;

public interface ProductService {
    List<ProductsVO> pList();
    ProductsVO pView(int id);

    void updateHit(int id);

    List<Test> cataPList(int cid);
}
