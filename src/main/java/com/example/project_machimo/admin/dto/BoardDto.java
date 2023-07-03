package com.example.project_machimo.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDto {
    private int boardId;
    private int userId;
    private String bCategory;
    private String inquiryCategory;
    private String bTitle;
    private String bWriter;
    private String bContent;
    private Timestamp bDate;
    private int bHit;
    private Integer bPwd;
}
