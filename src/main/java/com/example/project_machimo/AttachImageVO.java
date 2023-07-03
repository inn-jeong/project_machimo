package com.example.project_machimo;

import lombok.Data;

@Data
public class AttachImageVO {

    /* uuid */
    private String uuid;

    private int reviewId;

    /* 파일 이름 */
    private String fileName;

    /* 경로 */
    private String uploadPath;

    /* 상품 id */
//    private int productId;

}
