package com.example.project_machimo.review.dto;

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

//    private int fno;
//    private int bno;
//    private String fileName;     //저장할 파일
//    private String fileOriName;  //실제 파일
//    private String fileUrl;
}
