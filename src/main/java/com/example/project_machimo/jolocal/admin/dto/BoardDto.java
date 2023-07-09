package com.example.project_machimo.jolocal.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDto {
    private Integer boardId;
    private Integer userId;
    private String uNickname;
    private String bCategory;
    private String inquiryCategory;
    private String bTitle;
    private String bWriter;
    private String bContent;
    private Timestamp bDate;
    private Integer bHit;
    private Integer bPwd;
}
