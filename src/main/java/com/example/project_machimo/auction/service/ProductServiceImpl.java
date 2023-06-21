package com.example.project_machimo.auction.service;

import com.example.project_machimo.auction.dao.ProductsDAO;
import com.example.project_machimo.auction.dto.ProductsDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private SqlSession session;

    @Override
    public List<ProductsDTO> pView(int id) {
        ProductsDAO productsDAO = session.getMapper(ProductsDAO.class);
        return productsDAO.pView(id);
    }

    @Override
    public List<ProductsDTO> pList() {
        ProductsDAO productsDAO = session.getMapper(ProductsDAO.class);

        return productsDAO.pList();
    }
}
