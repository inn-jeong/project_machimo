package com.example.project_machimo.productEnroll.dao;

import com.example.project_machimo.productEnroll.dto.CategoryVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface CategoryMapper {

    public ArrayList<CategoryVO> getCategories(int cId);
    public ArrayList<CategoryVO> getSubCategories(int cId2);

}
