package com.example.project_machimo.shop.Dao;

import com.example.project_machimo.shop.Dto.CategoryDto;
import com.example.project_machimo.shop.Dto.ProductDto;

import java.util.ArrayList;

public interface ShopDao {
    public ArrayList<ProductDto> userContect();
    public ArrayList<ProductDto> allItemview();
}
