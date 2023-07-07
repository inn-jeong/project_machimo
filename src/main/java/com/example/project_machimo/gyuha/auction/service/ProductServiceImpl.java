package com.example.project_machimo.gyuha.auction.service;

import com.example.project_machimo.gyuha.auction.dao.ProductsDAO;
import com.example.project_machimo.gyuha.auction.dto.ProductsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    final private ProductsDAO productsDAO;

    @Autowired
    public ProductServiceImpl(ProductsDAO productsDAO) {
        this.productsDAO = productsDAO;
    }


    @Override
    public ProductsVO pView(int id) {
        productsDAO.updateHit(id);
        return productsDAO.pView(id);
    }

    @Override
    public List<ProductsVO> pList() {

        return productsDAO.pList();
    }
}
