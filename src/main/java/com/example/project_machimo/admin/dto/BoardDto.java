package com.example.project_machimo.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDto {
    private int board_id;
    private int user_id;
    private String b_category;
    private String inquiry_category;
    private String b_title;
    private String b_writer;
    private String b_content;
    private Date b_date;
    private int b_hit;
    private Integer b_pwd;
}
