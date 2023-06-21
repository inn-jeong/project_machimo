package com.example.project_machimo.review.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {
    private int reviewId;
    private int productsId;
    private String reviewWritter;
    private String reviewTitle;
    private String reviewContent;
    private int reviewStar;
    private String reviewImg;
    private Timestamp reviewDate;
    private String reviewHit;
}