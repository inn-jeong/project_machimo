package com.example.project_machimo.review.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {
    private int review_id;
    private int products_id;
    private int users_id;
    private String review_title;
    private String review_content;
    private int review_star;
    private String review_img;
}