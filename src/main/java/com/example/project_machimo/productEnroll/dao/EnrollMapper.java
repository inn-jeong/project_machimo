package com.example.project_machimo.productEnroll.dao;

import com.example.project_machimo.productEnroll.dto.CategoryVO;
import com.example.project_machimo.productEnroll.dto.ProductDto;
import com.example.project_machimo.review.dto.ReviewDto;
import jdk.jfr.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Mapper
public interface EnrollMapper {
//    void write(HashMap<String,String> param);
//    void write(ProductDto dto);
    ArrayList<CategoryVO> getCategories();
    ArrayList<CategoryVO> getSubCategories(int cId2);

    void write(ProductDto dto);
//
}
