package com.example.project_machimo.review.service;

import com.example.project_machimo.review.dto.ReviewDto;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.HashMap;


public interface ReviewService {
    ArrayList<ReviewDto> list();
    void write(HashMap<String,String> param);
    ReviewDto contentView(HashMap<String,String> param);
    void modify(HashMap<String,String> param);
    ReviewDto modify_view(String reviewId);

//    void modify(HashMap<String, String> param, Model model);

//    void modify_view(String reviewId);

    void delete(String reviewId);

    int getTotalCount();

}
