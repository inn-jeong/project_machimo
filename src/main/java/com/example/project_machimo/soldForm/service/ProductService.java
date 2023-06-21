package com.example.project_machimo.soldForm.service;

import com.example.project_machimo.review.dto.ReviewDto;

import java.util.ArrayList;
import java.util.HashMap;


public interface ProductService {
    ArrayList<ReviewDto> list();
    void write(HashMap<String,String> param);

    ReviewDto contentView(HashMap<String,String> param);
    void modify(HashMap<String,String> param);
    void delete(HashMap<String,String> param);
}
