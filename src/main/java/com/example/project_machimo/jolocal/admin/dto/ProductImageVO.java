package com.example.project_machimo.jolocal.admin.dto;

import lombok.Data;

@Data
public class ProductImageVO {

    /* uuid */
    private String uuid;

//    제품아이디
    private int productId;

    /* 메인파일 - 파일오리지널네임*/
    private String iImage;

    /* 서브파일 - 파일오리지널네임*/
    private String iSubImg;

    /* 이미지 날짜 경로 */
    private String uploadPath;

//    이미지 전체 경로
    private String url; // URL 추가

}
