package com.example.project_machimo.productEnroll.dao;

import com.example.project_machimo.productEnroll.dto.CategoryVO;
import com.example.project_machimo.productEnroll.dto.ProductDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface EnrollMapper {

    ArrayList<CategoryVO> getCategories();
    ArrayList<CategoryVO> getSubCategories(int cId2);

    void write(ProductDto dto);
}
