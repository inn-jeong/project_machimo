package com.example.project_machimo.review.service;

import com.example.project_machimo.review.dto.ReviewDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;


public interface ReviewService {
    ArrayList<ReviewDto> list();
    void write(HashMap<String,String> param);

    ReviewDto contentView(HashMap<String,String> param);
    void modify(HashMap<String,String> param);
    void delete(HashMap<String,String> param);
}
