package com.example.project_machimo.productEnroll.service;


import com.example.project_machimo.productEnroll.dao.CategoryMapper;
import com.example.project_machimo.productEnroll.dto.CategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CategoryService {
    @Autowired
    private CategoryMapper mapper;

    public ArrayList<CategoryVO> getCategories(int cId){
        return mapper.getCategories(cId);
    };
    public ArrayList<CategoryVO> getSubCategories(int cId2){
        return mapper.getSubCategories(cId2);
    };
}
