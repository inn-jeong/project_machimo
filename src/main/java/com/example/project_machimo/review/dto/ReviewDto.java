package com.example.project_machimo.review.dto;

import com.example.project_machimo.AttachImageVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
//이미지 정보 전달받기위해
@ToString
public class ReviewDto {
    private int reviewId;
    private int productId;
    private String reviewWritter;
    private String reviewTitle;
    private String reviewContent;
    private int reviewStar;
    private Timestamp reviewDate;
    private int reviewHit;
    private int id;
    /* 이미지 정보 */
    private List<AttachImageVO> imageList;

}