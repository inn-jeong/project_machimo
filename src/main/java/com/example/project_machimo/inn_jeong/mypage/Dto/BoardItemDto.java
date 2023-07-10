package com.example.project_machimo.inn_jeong.mypage.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardItemDto {
    private Integer boardId;
    private Integer userId;
    private String uNickname;
    private String bCategory;
    private String inquiryCategory;
    private String bTitle;
    private String bWriter;
    private String bContent;
    private String bDate;
    private Integer bHit;
    private Integer bPwd;
}
