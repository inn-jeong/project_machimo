package com.example.project_machimo.review.service;

import com.example.project_machimo.review.dto.ReviewDto;

import java.util.ArrayList;
import java.util.HashMap;


public interface ReviewService {
    ArrayList<ReviewDto> list();
    void write(HashMap<String,String> param);
    ReviewDto contentView(HashMap<String,String> param);
    void modify(HashMap<String,String> param);
    void delete(String reviewId);
}
