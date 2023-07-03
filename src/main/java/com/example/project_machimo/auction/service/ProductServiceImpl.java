package com.example.project_machimo.auction.service;

import com.example.project_machimo.auction.dao.ProductsDAO;
import com.example.project_machimo.auction.dto.ProductsVO;
import org.apache.ibatis.session.SqlSession;
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
        return productsDAO.pView(id);
    }

    @Override
    public List<ProductsVO> pList() {

        return productsDAO.pList();
    }
}
