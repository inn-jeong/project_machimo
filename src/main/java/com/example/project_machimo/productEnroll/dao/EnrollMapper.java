package com.example.project_machimo.productEnroll.dao;

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

    void write(ProductDto dto);
//
}
