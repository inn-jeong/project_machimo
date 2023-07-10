package com.example.project_machimo.inn_jeong.mypage.Service;

import com.example.project_machimo.gyuha.auction.dao.ProductsDAO;
import com.example.project_machimo.gyuha.auction.vo.ProductsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductGetImageImpl implements ProductGetImage{
    final private ProductsDAO productsDAO;

    @Autowired
    public ProductGetImageImpl(ProductsDAO productsDAO) {
        this.productsDAO = productsDAO;
    }
    @Override
    public ProductsVO pView(int id) {
        return productsDAO.pView(id);
    }
}
