package com.example.project_machimo.soldForm.dao;

import jdk.jfr.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface ProductDao {
//    public List<CategoryDto> category();
    void write(HashMap<String,String> param);
}
