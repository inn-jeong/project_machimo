package com.example.project_machimo.review.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {
    private int reviewId;
    private int productsId;
    private int usersId; // 이 필드가 추가되어야 할 수 있습니다.
    private String reviewTitle;
    private String reviewContent;
    private int reviewStar;
    private String reviewImg;
}